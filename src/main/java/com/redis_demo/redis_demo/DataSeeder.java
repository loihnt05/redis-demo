package com.redis_demo.redis_demo;

import com.github.javafaker.Faker;
import com.redis_demo.redis_demo.enity.User;
import com.redis_demo.redis_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository repo;
    private final Faker faker = new Faker();

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            Set<String> usernames = new HashSet<>();
            int count = 0;
            List<User> users = new ArrayList<>();
            while (count < 1000000) {
                String username = faker.name().username();
                if (usernames.add(username)) {
                    String fullname = faker.name().fullName();
                    users.add(new User(null, username, fullname));
                    count++;
                }
                if (users.stream().count() == 10000) {
                    repo.saveAll(users);
                    users.clear();
                }
            }
            System.out.println("Seeded 1 000 000 unique users!");
        }
    }
}
