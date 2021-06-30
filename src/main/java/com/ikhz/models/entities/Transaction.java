package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

// Class to save all transaction
@Entity
@Table
public class Transaction implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "amount is required")
    @Getter
    @Setter
    @Column(nullable = false)
    private long amount;

    @NotNull(message = "amount is required")
    @Getter
    @Setter
    @Column(nullable = false)
    private long total;

    @Getter
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private final Timestamp transactionDate = new Timestamp(System.currentTimeMillis());

    @Setter
    @ManyToOne
    private Product product;

    @Setter
    @ManyToOne
    private User user;

    public HashMap<String, Object> getProduct() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("Product Name", product.getProductName());
        hashMap.put("Product Price", product.getProductPrice());
        hashMap.put("Product Stock", product.getProductStock());

        return hashMap;
    }

    public String getUser() {
        return this.user.getUserName();
    }
}
