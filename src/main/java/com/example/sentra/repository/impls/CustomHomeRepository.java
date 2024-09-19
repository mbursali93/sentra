package com.example.sentra.repository.impls;

import java.util.Optional;

import com.example.sentra.model.HomeModel;
public interface CustomHomeRepository {
    public boolean homeNameExistsForOwner(String homeName, String ownerId); 
} 
