package com.loy.user.service;

import com.loy.user.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getById(Integer id);

    Boolean existByEmail(String  email);

    Optional<User> getByEmail(String email);

    User addUser(User user);

    Integer getNextValFromSequence();
}
