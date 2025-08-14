package com.redis_demo.redis_demo.service;

import com.redis_demo.redis_demo.enity.User;
import com.redis_demo.redis_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users_all")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Cacheable(value = "users", key = "#id")
    public User findUserById(String id) {
        log.info("Searching for user with id: {}", id);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("User not found with id: {}", id);
        } else {
            log.info("Found user: {}", user.getUsername());
        }
        return user;
    }

    @Cacheable(value = "users_by_username", key = "#username")
    public User findUserByUsername(String username) {
        log.info("Searching for user with username: {}", username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            log.warn("User not found with username: {}", username);
        } else {
            log.info("Found user: {}", user.getId());
        }
        return user;
    }

    @CacheEvict(value = {"users", "users_by_username", "users_all"}, allEntries = true)
    public boolean checkUsername(String username) {
        return findUserByUsername(username) != null;
    }
}
