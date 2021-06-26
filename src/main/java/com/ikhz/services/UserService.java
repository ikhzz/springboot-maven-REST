package com.ikhz.services;

import com.ikhz.models.entities.User;
import com.ikhz.models.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User create(User user){
        return userRepo.save(user);
    }

    public  Iterable<User> findAll(){
        return userRepo.findAll();
    }
}
