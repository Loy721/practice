package com.loy.user.mapper;

import com.loy.user.dto.UserDto;
import com.loy.user.entity.User;
import com.loy.user.service.UserService;
import exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperService {

    @Autowired
    private final UserService userService;

    public User UserDtoToNewUser(UserDto userDto){
         return User.builder().id(userService.getNextValFromSequence())
                 .email(userDto.getEmail()).firstName(userDto.getFirstName())
                 .lastName(userDto.getLastName()).surname(userDto.getSurname())
                 .birthday(userDto.getBirthday()).isBlocked(false).build();
    }

    public User UserDtoToExistsUser(UserDto userDto){
       User user = userService.getByEmail(userDto.getEmail())
               .orElseThrow(()-> new UsernameNotFoundException("Email"+userDto.getEmail()+"not found"));

       user.setSurname(userDto.getSurname());
       user.setLastName(userDto.getLastName());
       user.setFirstName(userDto.getFirstName());
       user.setBirthday(userDto.getBirthday());
       return  user;
    }
}
