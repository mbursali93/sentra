package com.example.sentra.repository.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.UserModel;

import java.util.Optional;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<UserModel> findById(String id) {
        System.out.println("\n \n ananın ta amını siksinler");
        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("id").is(id));

        UserModel user = mongoTemplate.findOne(query, UserModel.class);
        return Optional.ofNullable(user);
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
