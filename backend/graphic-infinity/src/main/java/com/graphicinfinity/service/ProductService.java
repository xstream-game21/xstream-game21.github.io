package com.graphicinfinity.service;

import com.graphicinfinity.model.Product;
import com.graphicinfinity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("null") 
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // CREATE
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    // READ - Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // READ - Get by ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
    
    // READ - Filter by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    // READ - Search by name
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }
    
    // READ - Check stock availability (NEW)
    public boolean checkStockAvailability(Integer productId, Integer quantity) {
        return productRepository.findById(productId)
            .map(product -> product.getQuantityInStock() >= quantity)
            .orElse(false);
    }
    
    // UPDATE
    public Product updateProduct(Integer id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            if (productDetails.getProductName() != null) {
                product.setProductName(productDetails.getProductName());
            }
            if (productDetails.getPrice() != null) {
                product.setPrice(productDetails.getPrice());
            }
            if (productDetails.getCategory() != null) {
                product.setCategory(productDetails.getCategory());
            }
            if (productDetails.getDescription() != null) {
                product.setDescription(productDetails.getDescription());
            }
            if (productDetails.getQuantityInStock() != null) {
                product.setQuantityInStock(productDetails.getQuantityInStock());
            }
            if (productDetails.getStatus() != null) {
                product.setStatus(productDetails.getStatus());
            }
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    // DELETE
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}