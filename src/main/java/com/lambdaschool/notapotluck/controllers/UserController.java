package com.lambdaschool.notapotluck.controllers;

import com.lambdaschool.notapotluck.models.User;
import com.lambdaschool.notapotluck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The entry point for clients to access user data
 */
@RestController
@RequestMapping("/users")
public class UserController
{
    /**
     * Using the User service to process user data
     */
    @Autowired
    private UserService userService;

    // GET http://localhost:2019/users/users
    @GetMapping(value = "/users",
        produces = "application/json")
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // GET http://localhost:2019/users/users/1
    @GetMapping(value = "/user/{userId}",
        produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
