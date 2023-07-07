package com.loy.security.repository;

import com.loy.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(Integer id);
    User getByEmail(String email);
}
