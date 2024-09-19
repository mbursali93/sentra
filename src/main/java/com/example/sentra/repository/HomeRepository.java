package com.example.sentra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sentra.model.HomeModel;
import com.example.sentra.repository.impls.CustomHomeRepository;

public interface HomeRepository extends MongoRepository<HomeModel, String>, CustomHomeRepository{
    
}
