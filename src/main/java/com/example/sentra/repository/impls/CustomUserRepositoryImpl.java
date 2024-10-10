package com.example.sentra.repository.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.And;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.WhenBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.UserModel;

import java.util.List;
import java.util.Optional;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<UserModel> findById(String id) {
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

    public Optional<UserModel> findByEmail(String email) {

        Query query = this.createForNonDeleted();

        query.addCriteria(Criteria.where("email").is(email));
        UserModel user = mongoTemplate.findOne(query, UserModel.class);

        return Optional.ofNullable(user);
    }

   public List<UserModel> findAll(Object criteria) {
    // Initialize the query object
    Query query = new Query();

    
    Criteria andCriteria = new Criteria().andOperator(
        Criteria.where("isDeleted").is(false)
        // Criteria.where("active").is(true)
    );

  
    Criteria orCriteria = new Criteria().orOperator(
        Criteria.where("username").regex("", "i"),
        Criteria.where("email").regex("", "i")
    );

    Criteria combinedCriteria = new Criteria().andOperator(
        andCriteria,
        orCriteria
    );

    query.addCriteria(combinedCriteria);

    return mongoTemplate.find(query, UserModel.class);
}
 
    private Query createForNonDeleted() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return query;
    }
}
