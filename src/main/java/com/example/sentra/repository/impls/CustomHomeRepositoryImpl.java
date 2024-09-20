package com.example.sentra.repository.impls;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    public HomeModel findById(String id, String userId) {
        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("_id").is(id));

       HomeModel home =  mongoTemplate.findOne(query, HomeModel.class);
        return home;
    }
    
    public HomeModel delete(String id) {
        // Query query = this.createForNonDeleted();

        // query.addCriteria(Criteria.where("_id").is(id));
        // Update update = new Update();
        // update.set("isDeleted", true);

        // mongoTemplate.updateFirst(query, update, HomeModel.class);

        HomeModel home = mongoTemplate.findById(id, HomeModel.class);
        home.setDeleted(true);
        home.setDeletedAt(Instant.now());

        return mongoTemplate.save(home);

    }

    public HomeModel updateHomeName(String id, String homeName) {
        HomeModel home = mongoTemplate.findById(id, HomeModel.class);

        home.setHomeName(homeName);
        return mongoTemplate.save(home);

    }

    private Query createForNonDeleted() {

        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return query;
    } 
     

}
