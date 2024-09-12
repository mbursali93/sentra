package com.example.sentra.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sentra.model.UserModel;
import com.example.sentra.repository.interfaces.CustomUserRepository;

public interface UserRepository extends MongoRepository<UserModel, String>, CustomUserRepository {
    // Optional<UserModel> findById(String id);
    UserModel findByUsername(String username);

}
