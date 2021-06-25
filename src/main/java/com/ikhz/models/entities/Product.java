package com.ikhz.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private long productStock;
    @Column(nullable = false)
    private long suplierId;
    @Column(nullable = false)
    private Timestamp lastUpdated;
}
