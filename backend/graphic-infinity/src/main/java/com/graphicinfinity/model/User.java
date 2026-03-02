package com.graphicinfinity.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
    @Column(unique = true, nullable = false, length = 100)
    private String username;
    
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(length = 100)
    private String fullName;
    
    @Column(length = 20)
    private String phone;
    
    @Column(length = 50)
    private String role;  // 'customer' or 'admin'
    
    @Column(length = 255)
    private String address;
    
    @Column(length = 100)
    private String city;
    
    @Column(length = 100)
    private String province;
    
    @Column(length = 20)
    private String postalCode;
    
    @Column
    private LocalDate birthdate;
    
    @Column(length = 255)
    private String profilePicture;
    
    @Column
    private Boolean isActive;
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
}