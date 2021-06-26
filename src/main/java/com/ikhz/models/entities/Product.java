package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table
@Getter
public class Product implements Serializable{

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Column(nullable = false)
    private String productName;
    @Setter
    @Column(nullable = false)
    private long productStock;
    @Column(nullable = false)
    private final Timestamp lastUpdated = new Timestamp(System.currentTimeMillis());
    @Setter
    @ManyToOne
    private User suplier;
}
