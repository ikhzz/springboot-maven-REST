package com.ikhz.utility;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:application-dev.properties")
public class EncryptionHelper {

    @Value("${simplecrud.seed}")
    private String seed;
    // simple password encryption
    public String passwordEncryption(String password){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        log.info(seed);
        encryptor.setPassword(seed);
        password = encryptor.encrypt(password);
        return password;
    }

    public String passwordDecryption(String password){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);

        return encryptor.decrypt(password);
    }
}
