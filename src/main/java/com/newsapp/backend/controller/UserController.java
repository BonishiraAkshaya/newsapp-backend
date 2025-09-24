package com.newsapp.backend.controller;

import com.newsapp.backend.model.User;
import com.newsapp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Your Spring Boot backend will reject requests from a different origin unless you enable CORS.

//Add this to your controller (or a global config):

@CrossOrigin(origins = "*") // allow all origins, or replace "*" with your frontend URL
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean success = userService.login(user.getUsername(), user.getPassword());
        return success ? "Login Successful" : "Invalid credentials";
    }
}
