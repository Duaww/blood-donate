package com.bdj.blooddonateproject.jwt.service;

import com.bdj.blooddonateproject.jwt.model.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);

    Integer deleteToken(String token);
}
