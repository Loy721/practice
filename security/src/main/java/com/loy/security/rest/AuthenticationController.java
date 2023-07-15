package com.loy.security.rest;

import com.loy.security.details.AuthenticationRequest;
import com.loy.security.details.AuthenticationResponse;
import com.loy.security.dto.UserDto;
import com.loy.security.exception.IncorrectUserOrPassword;
import com.loy.security.mapper.UserMapperService;
import com.loy.security.service.UserService;
import com.loy.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;
    private final UserMapperService userMapper;


    @GetMapping("/{jwt}")
    public Boolean authenticate(@PathVariable String jwt) {
        String username = jwtTokenUtil.extractUsername(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.validateToken(jwt, userDetails))
            return true;
        return false;
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String jwt = jwtTokenUtil.generateToken(userDetails);

            return new AuthenticationResponse(jwt);
        } catch (Exception e) {
            throw new IncorrectUserOrPassword("Incorrect username or password", e);
        }
    }

    @PostMapping("/signup")//TODO: стоит ли делать регистрацию в этом сервисе?
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        if (userService.existByEmail(userDto.getEmail()))
            throw new RuntimeException("There is an account with that email address: "
                    + userDto.getEmail());

        userService.addUser(userMapper.UserDtoToNewUser(userDto));

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
