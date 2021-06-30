package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

// User class implement 3 type of user of ADMIN, SUPLIER and GUEST
@Entity
@Table
@Getter
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "user Name is required")
    @Setter
    @Column(nullable = false)
    private String userName;

    @Email(message = "Valid email is required")
    @NotEmpty(message = "User email is required")
    @Setter
    @Column(nullable = false, unique = true)
    private String userEmail;


    @NotEmpty(message = "User password is required")
    @Column(nullable = false)
    @Setter
    private String userPassword;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final Role role = Role.GUEST;
}
