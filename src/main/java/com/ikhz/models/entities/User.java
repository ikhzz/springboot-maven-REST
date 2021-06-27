package com.ikhz.models.entities;

import com.ikhz.utility.EncryptionHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table
@Getter
public class User implements Serializable{

    @Setter
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
