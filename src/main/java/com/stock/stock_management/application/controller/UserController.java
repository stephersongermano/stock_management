package com.stock.stock_management.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock_management.application.dto.UserRequest;
import com.stock.stock_management.domain.entity.User;
import com.stock.stock_management.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        User userCreated = this.userService.createUser(userRequest.getUsername(), userRequest.getPassword());
        return userCreated;
    }
}
