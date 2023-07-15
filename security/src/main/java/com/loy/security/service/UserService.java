package com.loy.security.service;

import com.loy.security.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getByEmail(String email);

    Boolean existByEmail(String email);

    User addUser(User user);

    Integer getNextValFromSequence();
}
