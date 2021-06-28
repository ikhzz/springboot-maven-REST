package com.ikhz.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Slf4j
@Data
public class SignInValidator {

    @NotEmpty(message = "User email is required")
    @Email(message = "User Valid Email is required")
    private String userEmail;
    @NotEmpty(message = "User password is required")
    private String userPassword;

}
