package com.ikhz.models.entities;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@PropertySource("application.properties")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String userPassword;

    @Value("${simplcrud.seed}")
    private String seed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setUserPassword(String userPassword) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        userPassword = encryptor.encrypt(userPassword);
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
