package com.loy.user.service.imlp;

import com.loy.user.entity.User;
import com.loy.user.repository.UserRepository;
import com.loy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public Boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Integer getNextValFromSequence() {
        return userRepository.getNextSeriesId();
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return userRepository.getUserById(id);
    }
}
