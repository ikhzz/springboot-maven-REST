package com.ikhz.controllers;

import com.ikhz.dto.ErrorResponse;
import com.ikhz.dto.SignInValidator;
import com.ikhz.models.entities.User;
import com.ikhz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    // get all user method for test
    @GetMapping
    public ResponseEntity<Object> findAll() { return userService.findAll(); }
    // method to signup user
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody User user, Errors errors){
        // check validation error
        if(errors.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse("Sign Up Failed");

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return userService.create(user);
    }
    // method to sign in user
    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@Valid @RequestBody SignInValidator user, Errors errors){
        // check validation error
        if(errors.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse("Sign In Failed");

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return userService.signIn(user);
    }
}
