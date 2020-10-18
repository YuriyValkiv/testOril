package com.example.demo.domain;

import lombok.*;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {

    private String email;
    private String password;
}
