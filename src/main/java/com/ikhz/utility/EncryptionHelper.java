package com.ikhz.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class EncryptionHelper {

    @Value("${simplecrud.password_secret}")
    private String seed;
    @Getter
    @Value("${simplecrud.token_secret}")
    private String tokenSecret;
    // password encryption method
    public String passwordEncryption(String password){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        password = encryptor.encrypt(password);
        return password;
    }
    // password decryptopn method
    public String passwordDecryption(String password){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);

        return encryptor.decrypt(password);
    }
    // password encryption moved to response for simplify
    // token decryption method
    public String tokenDecryption(String jwt){
        Jws<Claims> claim = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt);
        jwt = claim.getBody().getSubject();
        return jwt;
    }
}
