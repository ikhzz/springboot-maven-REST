package com.ikhz.services;

import com.ikhz.dto.ErrorResponse;
import com.ikhz.dto.GetAllUserResponse;
import com.ikhz.dto.SignInValidator;
import com.ikhz.dto.TokenResponse;
import com.ikhz.models.entities.User;
import com.ikhz.models.repos.UserRepo;
import com.ikhz.utility.EncryptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EncryptionHelper encryptionHelper;

    public ResponseEntity<TokenResponse> create(User user){
        user.setUserPassword(encryptionHelper.passwordEncryption(user.getUserPassword()));
        User createdUser = userRepo.save(user);
        // add validation if user is saved
        TokenResponse tokenResponse = new TokenResponse("Sign Up", createdUser.getId(), encryptionHelper.getTokenSecret());
        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity signIn(SignInValidator signInValidator){
        Optional<User> user = Optional.ofNullable(userRepo.findByUserEmail(signInValidator.getUserEmail()));
        ErrorResponse errorResponse = new ErrorResponse("Sign in Failed");

        if(user.isEmpty() ){
            errorResponse.getMessage().add("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        String pass = encryptionHelper.passwordDecryption(user.get().getUserPassword());

        if(!pass.equals(signInValidator.getUserPassword())){
            errorResponse.getMessage().add("Wrong Password");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        TokenResponse tokenResponse = new TokenResponse("Sign In", user.get().getId(), encryptionHelper.getTokenSecret());
        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<Object> findAll(){
        List<User> users = (List<User>) userRepo.findAll();
        List<Object> lists = new ArrayList<>();
        if(users.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponse("No data Found");
            errorResponse.getMessage().add("No Data in Database");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        for (User user: users){
            GetAllUserResponse response = new GetAllUserResponse();
            response.setUserName(user.getUserName());
            response.setUserEmail(user.getUserEmail());
            lists.add(response);
        }
        return ResponseEntity.ok(lists);
    }

    public User findByEmail(String email){
        return userRepo.findByUserEmail(email);
    }
}
