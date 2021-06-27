package com.ikhz.controllers;

import com.ikhz.dto.ErrorResponse;
import com.ikhz.models.entities.User;
import com.ikhz.services.UserService;
import com.ikhz.utility.EncryptionHelper;
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

    @Autowired
    private EncryptionHelper encryptionHelper;

    @GetMapping
    public Iterable<User> findAll() { return userService.findAll(); }


    @PostMapping
    public ResponseEntity signUp(@Valid @RequestBody User user, Errors errors){
        // check user email
        User registered = userService.findByEmail(user.getUserEmail());
        // set error if email is registered
        if(registered != null) errors.reject("a", "Email is already registered");
        // check validation error
        if(errors.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse("Sign Up Failed");

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        // set encryped password
        user.setUserPassword(encryptionHelper.passwordEncryption(user.getUserPassword()));
        return userService.create(user);
    }
}
