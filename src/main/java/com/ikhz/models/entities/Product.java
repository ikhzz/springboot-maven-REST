package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table
@Getter
public class Product implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotEmpty(message = "product name is required")
    @Column(nullable = false)
    private String productName;

    @NotNull(message = "product stock is required")
    @Setter
    @Column(nullable = false)
    private long productStock;

    @Column(nullable = false)
    private final Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());

    @Setter
    @ManyToOne
    private User suplier;
}
