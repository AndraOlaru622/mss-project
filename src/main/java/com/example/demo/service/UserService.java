package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getUsers() {
        return repository.getUsers();
    }

    public List<User> getUserByName(String name) {

        return repository.getUserByName(name);

    }

    public User addNewUser(User user) {

        return repository.addNewUser(user);
    }
}