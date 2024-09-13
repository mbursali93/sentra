package com.example.sentra.service;




public class Dalga {

}


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductService {

//     @Autowired
//     private MongoTemplate mongoTemplate;

//     public List<Product> getAllProducts(ProductFilterOptions filterOptions) {
//         String search = filterOptions.getSearch();
//         List<String> filter = filterOptions.getFilter();
//         double minPrice = filterOptions.getMinPrice();
//         double maxPrice = filterOptions.getMaxPrice();
//         String priceSort = filterOptions.getPriceSort();

//         // Create the base query
//         Query query = new Query();

//         // Add search conditions with OR
//         Criteria searchCriteria = new Criteria().orOperator(
//             Criteria.where("title").regex(search, "i"),
//             Criteria.where("description").regex(search, "i"),
//             Criteria.where("artistName").regex(search, "i")
//         );

//         // Add filter conditions with AND
//         Criteria filterCriteria = new Criteria().andOperator(
//             Criteria.where("artType").in(filter),
//             Criteria.where("price").gte(minPrice).lte(maxPrice)
//         );

//         // Combine search and filter criteria
//         Criteria combinedCriteria = new Criteria().andOperator(searchCriteria, filterCriteria);
//         query.addCriteria(combinedCriteria);

//         // Add sorting
//         if (priceSort != null && !priceSort.isEmpty()) {
//             Sort.Direction direction = priceSort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//             query.with(Sort.by(direction, "price"));
//         }

//         // Execute the query
//         return mongoTemplate.find(query, Product.class);
//     }
// }
