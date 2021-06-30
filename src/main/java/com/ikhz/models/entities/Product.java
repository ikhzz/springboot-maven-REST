package com.ikhz.models.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

// Class to save all product
@Entity
@Table
public class Product implements Serializable{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(nullable = false)
    @NotEmpty(message = "product name is required")
    private String productName;

    @Getter
    @Setter
    @NotNull(message = "product stock is required")
    @Column(nullable = false)
    private long productStock;

    @Getter
    @Setter
    @Column(nullable = false)
    @NotNull(message = "product price is required")
    private long productPrice;

    @Getter
    @Column(nullable = false)
    private final Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());

    @Setter
    @ManyToOne
    private User suplier;
    // find a way to get only needed data not all of the object
    public String getSuplier() {
        return this.suplier.getUserName();
    }
}
