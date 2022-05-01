package com.bdj.blooddonateproject.jwt.model;

import java.util.Date;

import com.bdj.blooddonateproject.config.UserPrincipal;
import com.bdj.blooddonateproject.jwt.dto.InfoAccountDTO;
import com.bdj.blooddonateproject.jwt.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.minidev.json.JSONObject;

@Component
public class JwtUtil {

    private static final String USER = "TINH";
    private static final String SECRET = "THISISSIGNATUREdaylachukivakhongduocdelorangoai";

    @Autowired
    private TokenService tokenService;

    public String generateToken(UserPrincipal user) {
        String token = null;

        try {
            InfoAccountDTO accountDTO = new InfoAccountDTO(user);
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            // set payload for token
            builder.claim(USER, accountDTO);
            // set time expiration for token
            builder.expirationTime(generateExpirationDate());
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            JWSSigner jwsSigner = new MACSigner(SECRET.getBytes());
            // set signature for token
            signedJWT.sign(jwsSigner);
            token = signedJWT.serialize();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return token;
    }

    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + 864000000);
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return claims;
    }

    public UserPrincipal getUserFromToken(String token) {
        UserPrincipal user = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            if (claims != null && isTokenExpired(claims)) {
                JSONObject jsonObject = (JSONObject) claims.getClaim(USER);
                InfoAccountDTO accountDTO = new ObjectMapper().readValue(jsonObject.toJSONString(),
                        InfoAccountDTO.class);
                user = new UserPrincipal(accountDTO);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    private Date getExpirationDateFromToken(JWTClaimsSet claims) {
        return claims != null ? claims.getExpirationTime() : new Date();
    }

    private boolean isTokenExpired(JWTClaimsSet claims) {
        return getExpirationDateFromToken(claims).after(new Date());
    }

}
