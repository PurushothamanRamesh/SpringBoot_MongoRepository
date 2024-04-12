package com.mongodbRepository.Service;
import com.mongodbRepository.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTemplateService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductTemplateService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Product> getAllProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Product.class));
    }

    public Product createProduct(Product product) {
        return mongoTemplate.insert(product);
    }

    public Product updateProduct(String id, Product newProduct) {
        newProduct.setProductId(id); // Ensure the ID is set for update
        return mongoTemplate.save(newProduct);
    }

    public void deleteProduct(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Product.class);
    }

    public List<Product> createProducts(List<Product> products) {
        return (List<Product>) mongoTemplate.insertAll(products);
    }

    public List<Product> getProductsByProductId(String productId) {
        Query query = new Query(Criteria.where("productId").is(productId));
        return mongoTemplate.find(query, Product.class);
    }

    public List<Product> productTemplateService(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        return mongoTemplate.find(query, Product.class);
    }
}
