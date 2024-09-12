package com.example.sentra.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import com.example.sentra.model.UserModel;
import com.example.sentra.repository.impls.CustomUserRepository;

public interface UserRepository extends MongoRepository<UserModel, String>, CustomUserRepository {
    Optional<UserModel> findById(String id);
    
    // UserModel findByUsername(String username);

}
