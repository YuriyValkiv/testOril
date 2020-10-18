package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @MongoId
    private String id;
    private long seq;
}

