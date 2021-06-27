package com.ikhz.services;

import com.ikhz.dto.TokenResponse;
import com.ikhz.models.entities.User;
import com.ikhz.models.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity create(User user){
        User createdUser = userRepo.save(user);
        // replace with token response
//        TokenResponse signUpResponse = new TokenResponse(true, createdUser.getUserName(), createdUser.getUserEmail());
        return ResponseEntity.ok("Sucess");
    }

    public  Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public User findByEmail(String email){
        return userRepo.findByUserEmail(email);
    }
}
