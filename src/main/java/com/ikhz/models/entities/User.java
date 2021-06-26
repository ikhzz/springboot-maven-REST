package com.ikhz.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@PropertySource("application.properties")
@Getter
public class User implements Serializable{

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Column(nullable = false)
    private String userName;
    @Setter
    @Column(nullable = false, unique = true)
    private String userEmail;
    @Column(nullable = false)
    private String userPassword;

    @Value("${simplcrud.seed}")
    private String seed;
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setUserPassword(String userPassword) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        userPassword = encryptor.encrypt(userPassword);
        this.userPassword = userPassword;
    }
}
