package com.example.sentra.repository.impls;

import com.example.sentra.model.UserModel;
import java.util.Optional;

public interface CustomUserRepository {
     Optional<UserModel> findById(String id);
    // UserModel findByUsername(String username);
}
