package com.example.sentra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.sentra.model.MemberModel;
import com.example.sentra.repository.impls.CustomMemberRepository;

public interface MemberRepository extends MongoRepository<MemberModel, String>, CustomMemberRepository {
    
}
