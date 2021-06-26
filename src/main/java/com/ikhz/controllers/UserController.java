package com.ikhz.controllers;

import com.ikhz.dto.SignUpResponse;
import com.ikhz.models.entities.User;
import com.ikhz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(){
        SignUpResponse signUpResponse = new SignUpResponse();

        return ResponseEntity.ok(signUpResponse);
    }
}
