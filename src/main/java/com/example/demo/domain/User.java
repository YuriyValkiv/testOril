package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@Document(collection = "crudUser")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @MongoId
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date createdAt;
}
