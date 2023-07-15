package com.loy.security.mapper;


import com.loy.security.dto.UserDto;
import com.loy.security.entity.User;
import com.loy.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperService {

    @Autowired
    private final UserService userService;

    public User UserDtoToNewUser(UserDto userDto){
        return new User(userService.getNextValFromSequence(), userDto.getFirstName(), userDto.getSurname(),
                userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), userDto.getBirthday(),
                false);
    }

    public User UserDtoToExistsUser(UserDto userDto){
        User user = userService.getByEmail(userDto.getEmail())
                .orElseThrow(()-> new RuntimeException("Email"+userDto.getEmail()+"not found"));

        user.setSurname(userDto.getSurname());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setBirthday(userDto.getBirthday());
        return  user;
    }
}

