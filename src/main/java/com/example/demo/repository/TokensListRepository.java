package com.example.demo.repository;

import com.example.demo.domain.TokensList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokensListRepository extends MongoRepository<TokensList, Long> {

    TokensList getTokensListByToken(String token);
    void removeTokensListByToken(String token);
}
