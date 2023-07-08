package com.loy.security.rest;

import com.loy.security.details.AuthenticationRequest;
import com.loy.security.details.AuthenticationResponse;
import com.loy.security.exception.IncorrectUserOrPassword;
import com.loy.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

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
