package com.ikhz.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long amount;
    @Column(nullable = false)
    private double total;
    @Column(nullable = false)
    private final Timestamp transactionDate = new Timestamp(System.currentTimeMillis());

    // product id many to one

    // input id many to one

    public Timestamp getTransactionDate() {
        return transactionDate;
    }
}
