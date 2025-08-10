package com.redis_demo.redis_demo.controller;

import com.redis_demo.redis_demo.enity.User;
import com.redis_demo.redis_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }
}
