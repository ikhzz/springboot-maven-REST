package com.ikhz.controllers;

import com.ikhz.dto.ErrorResponse;
import com.ikhz.models.entities.Transaction;
import com.ikhz.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trans")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Iterable<Transaction> findAll(){
        return transactionService.findAll();
    }
    // move create transaction to product
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Transaction transaction, Errors errors, @RequestHeader HttpHeaders httpHeaders){
        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if(token == null){
            ErrorResponse errorResponse = new ErrorResponse("Cannot create transaction");
            errorResponse.getMessage().add("Authorization token is required");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        String[] split = token.split(" ", 2);
        if(errors.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse("Cannot create product");

            for(ObjectError error: errors.getAllErrors()){
                errorResponse.getMessage().add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.ok("ok");
    }
}
