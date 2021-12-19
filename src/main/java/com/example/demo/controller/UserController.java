package com.example.demo.controller;

import com.example.demo.exception.DuplicateUserException;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/all")
    public List<User> getUsers() {
        return service.getUsers();
    }


    @GetMapping("/getByName")
    public ResponseEntity<?>getUserByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok().body(service.getUserByName(name));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User u) {

        try {
            User user = service.addNewUser(u);
            return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
        } catch (DuplicateUserException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

}
