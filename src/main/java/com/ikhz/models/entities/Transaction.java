package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table

public class Transaction implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "amount is required")
    @Getter
    @Setter
    @Column(nullable = false)
    private long amount;

    @Getter
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

    public String getProduct() {
        return this.product.getProductName();
    }

    public String getUser() {
        return this.user.getUserName();
    }
}
