package com.mongodbRepository.Controller;
import com.mongodbRepository.Entity.Product;
import com.mongodbRepository.Service.ProductService;
import com.mongodbRepository.Service.ProductTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    ProductTemplateService productTemplateService;

    @GetMapping
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable String id) {
        logger.info("Fetching product with id: {}", id);
        Product product = productService.getProductById(id).orElse(null);
        if (product != null) {
            return product;
        } else {
            return "Product not found with id: " + id;
        }
    }

    @GetMapping("/getbyproductid/{productid}")
    public Object getProductByProductId(@PathVariable String productid) {
        logger.info("Fetching products with productId: {}", productid);
        List<Product> products = productService.getProductsByProductId(productid);
        if (!products.isEmpty()) {
            return products;
        } else {
            return "No products found with productId: " + productid;
        }
    }

    @GetMapping("/getbydynamicfield")
    public Object getProductByProductId(@RequestParam ("key") String key,@RequestParam("value") String value) {
        logger.info("Fetching products with this : ", value);
        List<Product> products = productTemplateService.productTemplateService(key,value);
        if (!products.isEmpty()) {
            return products;
        } else {
            return "No products found with this : " + value;
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        logger.info("Creating new product: {}", product);
        return productService.createProduct(product);
    }
    @PostMapping("/bulkupload")
    public List<Product> createProducts(@RequestBody List<Product> products) {
        logger.info("Creating new products: {}", products);
        return productService.createProducts(products);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product newProduct) {
        logger.info("Updating product with id {}: {}", id, newProduct);
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        logger.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
    }
}
