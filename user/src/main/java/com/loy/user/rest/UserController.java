package com.loy.user.rest;

import com.loy.user.dto.UserDto;
import com.loy.user.entity.User;
import com.loy.user.mapper.UserMapperService;
import com.loy.user.service.UserService;
import exception.UserAlreadyExistException;
import exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapperService userMapper;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getById(id).orElseThrow(() -> new UsernameNotFoundException("Id:"+id+" Not Found"));
    }

    @PostMapping("/signup")//TODO: стоит ли делать регистрацию в этом сервисе?
    public ResponseEntity<?> registration(@RequestBody UserDto userDto){
        if (userService.existByEmail(userDto.getEmail()))
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());

        userService.addUser(userMapper.UserDtoToNewUser(userDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
