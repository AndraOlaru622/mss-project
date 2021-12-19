package com.example.demo.repository;


import com.example.demo.exception.DuplicateUserException;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.model.User;
import com.example.demo.queries.UserQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {

        LOGGER.info("Retrieving users ... ");

        return jdbcTemplate.query(UserQueries.GET_USERS, (resultSet, i) -> new User(
                resultSet.getInt("id"),
                resultSet.getString("name")
        ));


    }

    public List<User> getUserByName(String name) {

        LOGGER.info("Getting user... {}",name);

        List<User> list = jdbcTemplate.query(UserQueries.GET_USER_BY_NAME,  (resultSet, i) -> new User(
                resultSet.getInt("id"),
                resultSet.getString("name")
        ), name);

        if(list.isEmpty()) {

            throw new ObjectNotFoundException("User does not exist");
        }

        return list;

    }

    public Optional<User> checkUser(String name) {

        return jdbcTemplate.query(UserQueries.CHECK_USER,  (resultSet, i) -> new User(
                resultSet.getInt("id"),
                resultSet.getString("name")
        ), name).stream().findFirst();


    }

    public User addNewUser(User user) {

        LOGGER.info("Adding user... {}", user);

        Optional<User> existingUser = checkUser(user.getName());
        if(existingUser.isPresent()){

            throw new DuplicateUserException();

        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.ADD_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            return preparedStatement;
        }, keyHolder);

        user.setId(keyHolder.getKey().intValue());
        return user;

    }

}
