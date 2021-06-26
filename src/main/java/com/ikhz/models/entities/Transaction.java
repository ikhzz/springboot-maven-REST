package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table
@Getter
public class Transaction implements Serializable {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Column(nullable = false)
    private long amount;
    @Setter
    @Column(nullable = false)
    private double total;
    @Column(nullable = false)
    private final Timestamp transactionDate = new Timestamp(System.currentTimeMillis());
    @Setter
    @ManyToOne
    private Product product;
    @Setter
    @ManyToOne
    private User user;
}
