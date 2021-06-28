package com.ikhz.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
@PropertySource("classpath:application-dev.properties")
public class EncryptionHelper {

    @Value("${simplecrud.seed}")
    private String seed;
    @Getter
    @Value("${simplecrud.token_secret}")
    private String tokenSecret;
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

    public String tokenDecryption(String jwt){
        Jws<Claims> claim = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt);
        log.info(String.valueOf(claim));
        jwt = claim.getBody().getSubject();
        log.info(jwt);
        return jwt;
    }
}
