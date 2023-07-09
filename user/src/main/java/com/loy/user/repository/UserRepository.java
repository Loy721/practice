package com.loy.user.repository;

import com.loy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> getUserById(Integer id);

    Optional<User> getUserByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT nextval('users_seq')", nativeQuery =
            true)
    Integer getNextSeriesId();
}
