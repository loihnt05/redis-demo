package com.redis_demo.redis_demo.repository;

import com.redis_demo.redis_demo.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
