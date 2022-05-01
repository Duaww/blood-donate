package com.bdj.blooddonateproject.jwt.service;

import com.bdj.blooddonateproject.jwt.model.Token;
import com.bdj.blooddonateproject.jwt.repo.TokenRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public TokenServiceImpl(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public Token createToken(Token token) {
        // TODO Auto-generated method stub
        return tokenRepo.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        // TODO Auto-generated method stub
        return tokenRepo.findByToken(token);
    }

}
