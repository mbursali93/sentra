package com.example.sentra.repository.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.HomeModel;

public class CustomHomeRepositoryImpl implements CustomHomeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean homeNameExistsForOwner(String name, String ownerId) {
        Query query = this.createForNonDeleted();

        query.addCriteria(Criteria.where("homeName").is(name).and("ownerId").is(ownerId));

        boolean homeNameExists = mongoTemplate.exists(query, HomeModel.class);
        return homeNameExists;
        
    }

    private Query createForNonDeleted() {

        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return query;
    } 
     

}
