package com.graphicinfinity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(unique = true, length = 50)
    private String orderNumber;
    
    @Column
    private BigDecimal subtotal;
    
    @Column
    private BigDecimal shippingFee;
    
    @Column
    private BigDecimal tax;
    
    @Column
    private BigDecimal totalAmount;
    
    @Column(length = 50)
    private String paymentMethod;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private OrderStatus orderStatus;
    
    @Column(columnDefinition = "TEXT")
    private String deliveryAddress;
    
    @Column(length = 100)
    private String deliveryCity;
    
    @Column(length = 100)
    private String deliveryProvince;
    
    @Column(length = 20)
    private String deliveryPostalCode;
    
    @Column(columnDefinition = "TEXT")
    private String deliveryNotes;
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
    
    // Relationship
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}