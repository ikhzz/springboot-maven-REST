package com.ikhz.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignUpValidator {

    @NotEmpty(message = "User name is required")
    private String userName;
    @NotEmpty(message = "User email is required")
    @Email(message = "User Valid Email is required")
    private String userEmail;
    @NotEmpty(message = "User password is required")
    private String userPassword;

}
