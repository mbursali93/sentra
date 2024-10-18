package com.example.sentra.repository.impls;

import java.time.Instant;
import java.util.Date;
// import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation.ExecutableUpdate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import com.example.sentra.dto.UpdateProductDto;
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

    public ProductModel updateProduct(String id, ProductModel model) {
        
        Query query = this.createForNonDeleted();
        query.addCriteria(Criteria.where("id").is(id));
        
        Update update = new Update();

        if (model.getBrand() != null) {
            update.set("brand", model.getBrand());
        }
        
        if (model.getDescription() != null) {
            update.set("description", model.getDescription());
        }

        if (model.getModel() != null) {
            update.set("model", model.getModel());
        }
        
        if (model.getBrand() != null) {
            update.set("brand", model.getBrand());
        }
        
        if (model.getPrice() != null) {
            update.set("price", model.getPrice());
        }
        
        if (model.getTitle() != null) {
            update.set("title", model.getTitle());
        }
        
        update.set("updatedAt", Instant.now());

        mongoTemplate.updateFirst(query, update, ProductModel.class);
        return mongoTemplate.findOne(query, ProductModel.class);
        
    }
 
    private Query createForNonDeleted() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));

        return query;
    }
}
