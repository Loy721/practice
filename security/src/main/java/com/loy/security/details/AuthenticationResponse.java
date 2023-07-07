package com.loy.security.details;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {
    private final String accessToken;
}
