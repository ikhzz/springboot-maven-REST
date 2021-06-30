package com.ikhz.models.repos;

import com.ikhz.models.entities.User;
import org.springframework.data.repository.CrudRepository;

// User Interface class extend basic crud class from spring boot with basic method
public interface UserRepo extends CrudRepository<User, Long> {
    // derived spring boot query method to get email by parameter
    User findByUserEmail(String email);
}
