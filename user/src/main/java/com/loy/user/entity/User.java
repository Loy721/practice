package com.loy.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    private String surname;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
}

