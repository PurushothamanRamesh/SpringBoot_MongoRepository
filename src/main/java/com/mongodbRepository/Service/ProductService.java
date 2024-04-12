package com.mongodbRepository.Service;

import com.mongodbRepository.Entity.Product;
import com.mongodbRepository.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product newProduct) {
        newProduct.setProductId(id); // Ensure the ID is set for update
        return productRepository.save(newProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> createProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> getProductsByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }



}
