package com.loy.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String surname;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
}
