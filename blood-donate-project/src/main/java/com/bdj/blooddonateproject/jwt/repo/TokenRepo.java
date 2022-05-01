package com.bdj.blooddonateproject.jwt.repo;

import javax.transaction.Transactional;

import com.bdj.blooddonateproject.jwt.model.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM v1.t_token WHERE token = :token", nativeQuery = true)
    Integer deleteByToken(String token);

}
