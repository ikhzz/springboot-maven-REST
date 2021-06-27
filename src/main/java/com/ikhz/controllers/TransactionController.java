package com.ikhz.controllers;

import com.ikhz.models.entities.Transaction;
import com.ikhz.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Iterable<Transaction> findAll(){
        return transactionService.findAll();
    }

//    @PostMapping
//    public Transaction create(){
//        re
//    }
}
