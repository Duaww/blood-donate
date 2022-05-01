package com.bdj.blooddonateproject.jwt.repo;

import com.bdj.blooddonateproject.jwt.model.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
