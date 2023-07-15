package com.loy.security.repository;

import com.loy.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByEmail(String email);
}
