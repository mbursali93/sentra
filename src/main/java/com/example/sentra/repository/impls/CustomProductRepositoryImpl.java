package com.example.sentra.repository.impls;

import java.time.Instant;
import java.util.Date;
// import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.ProductModel;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<ProductModel> getProductById(String id) {

        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("id").is(id));

        ProductModel product = mongoTemplate.findOne(query, ProductModel.class);
        return Optional.ofNullable(product);
    };

    public ProductModel deleteProduct(String id) {

        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("id").is(id));

        ProductModel product = mongoTemplate.findOne(query, ProductModel.class);
        product.setDeleted(true);
        product.setDeletedAt(Instant.now());
        return mongoTemplate.save(product);
    }

    private Query createForNonDeleted() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));

        return query;
    }
}
