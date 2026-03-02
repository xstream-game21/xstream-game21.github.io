package com.graphicinfinity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    
    @Column(nullable = false, length = 255)
    private String productName;
    
    @Column(length = 100)
    private String category;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 255)
    private String imageUrl;
    
    @Column
    private Integer quantityInStock;
    
    @Column(length = 50)
    private String status;
    
    @Column
    private Integer createdBy;
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
}