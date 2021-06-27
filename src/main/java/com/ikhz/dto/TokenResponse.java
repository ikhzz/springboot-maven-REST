package com.ikhz.dto;

import lombok.Getter;

@Getter
public class TokenResponse {

    private final String message = "Sign Up Success";
    private String token;

    public TokenResponse(Long id){

    }
}
