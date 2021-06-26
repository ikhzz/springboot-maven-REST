package com.ikhz.models.repos;

import com.ikhz.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
