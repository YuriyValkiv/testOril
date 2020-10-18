package com.example.demo.service;

import com.example.demo.domain.TokensList;
import com.example.demo.repository.TokensListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TokensListService {

    private final TokensListRepository tokensListRepository;

    public TokensList save(TokensList tokensList) {
        return tokensListRepository.save(tokensList);
    }

    public Optional<TokensList> getTokensListByToken(String token) {
        return Optional.ofNullable(tokensListRepository.getTokensListByToken(token));
    }

    public void removeTokensListByToken(String token) {
        tokensListRepository.removeTokensListByToken(token);
    }
}
