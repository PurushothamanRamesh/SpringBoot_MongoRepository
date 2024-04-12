package com.mongodbRepository.Repository;

import com.mongodbRepository.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductId(String productId);
}

