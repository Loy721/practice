package com.loy.security.service;

import com.loy.security.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getById(Integer id);

    User getByEmail(String email);
}
