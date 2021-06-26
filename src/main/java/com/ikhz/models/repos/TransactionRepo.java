package com.ikhz.models.repos;

import com.ikhz.models.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
