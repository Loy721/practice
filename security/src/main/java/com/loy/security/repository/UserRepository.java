package com.loy.security.repository;

import com.loy.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByEmail(String email);

    @Query(value = "SELECT nextval('users_seq')", nativeQuery =
            true)
    Integer getNextValFromSequence();

    Boolean existsByEmail(String mail);
}
