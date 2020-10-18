package com.example.demo.controller;

import com.example.demo.domain.JwtRequest;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.TokensListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "auth")
@AllArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationService authenticationService;
    private final TokensListService tokensListService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        HttpHeaders headers = authenticationService.createAuthenticationToken(authenticationRequest.getEmail(),
                authenticationRequest.getPassword(), false);
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        Enumeration<String> token = request.getHeaders("Authorization");
        while (token.hasMoreElements()) {
            tokensListService.removeTokensListByToken(token.nextElement());
        }
        return ResponseEntity.noContent().build();
    }
}

