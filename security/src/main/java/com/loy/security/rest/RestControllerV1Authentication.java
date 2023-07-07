package com.loy.security.rest;

import com.loy.security.details.AuthenticationRequest;
import com.loy.security.details.AuthenticationResponse;
import com.loy.security.exception.IncorrectUserOrPassword;
import com.loy.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class RestControllerV1Authentication {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/signin")
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

    @GetMapping("/test")
    public String getOk(){
        return "OK";
    }

}
