package com.loy.security.service.imlp;

import com.loy.security.entity.User;
import com.loy.security.repository.UserRepository;
import com.loy.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    final private UserRepository userRepository;

    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Integer getNextValFromSequence() {
        return userRepository.getNextValFromSequence();
    }
}
