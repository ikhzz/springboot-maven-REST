package com.ikhz.models.repos;

import com.ikhz.models.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

// Transaction Interface class extend basic crud class from spring boot with basic method
public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
