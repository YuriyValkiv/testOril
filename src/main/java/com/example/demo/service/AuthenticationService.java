package com.example.demo.service;

import com.example.demo.config.security.JwtTokenUtil;
import com.example.demo.domain.TokensList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final TokensListService tokensListService;

    public HttpHeaders createAuthenticationToken(String email, String password, boolean isNewUser) throws Exception {
        final UserDetails userDetails;
        if (isNewUser) {
            userDetails = new User(email, password, new ArrayList<>());
        } else {
            authenticate(email, password);
            userDetails = jwtUserDetailsService.loadUserByUsername(email);
        }

        final String token = jwtTokenUtil.generateToken(userDetails);
        tokensListService.save(new TokensList("Bearer " + token));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return headers;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
