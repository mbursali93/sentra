package com.example.sentra.repository.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.UserModel;
import com.example.sentra.repository.interfaces.CustomUserRepository;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public UserModel findById(String id) {
        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("id").is(id));

       return mongoTemplate.findOne(query, UserModel.class);
    }

    public UserModel findByUsername(String username) {

        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, UserModel.class);
    }

    private Query createForNonDeleted() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return query;
    }
}
