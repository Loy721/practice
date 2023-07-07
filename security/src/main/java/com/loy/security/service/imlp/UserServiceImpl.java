package com.loy.security.service.imlp;

import com.loy.security.entity.User;
import com.loy.security.repository.UserRepository;
import com.loy.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User getByEmail(String email){
        return userRepository.getByEmail(email);
    }
}
