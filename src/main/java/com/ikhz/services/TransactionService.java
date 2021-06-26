package com.ikhz.services;

import com.ikhz.models.entities.Transaction;
import com.ikhz.models.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Iterable<Transaction> findAll(){
        return transactionRepo.findAll();
    }
    public Transaction create(Transaction transaction){
        return transactionRepo.save(transaction);
    }
}
