package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tokensList")
@AllArgsConstructor
public class TokensList {

    private String token;
}
