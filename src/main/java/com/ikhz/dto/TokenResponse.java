package com.ikhz.dto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

@Getter
public class TokenResponse {

    private String message = " Success";
    private String token;

    public TokenResponse(String type, Long id, String secret){
        this.message = type + message;
        this.token = Jwts.builder().setSubject(String.valueOf(id)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
