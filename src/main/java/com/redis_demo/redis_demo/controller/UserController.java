package com.redis_demo.redis_demo.controller;

import com.redis_demo.redis_demo.enity.User;
import com.redis_demo.redis_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/user/id/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/user/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
