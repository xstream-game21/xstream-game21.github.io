package com.graphicinfinity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.graphicinfinity.model.User;
import com.graphicinfinity.repository.UserRepository;

@Service
@SuppressWarnings("null")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setIsActive(true);

        return userRepository.save(user);
    }

    // READ - Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ - Get by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // READ - Get by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // UPDATE
    public User updateUser(Integer id, User userDetails) {
        return userRepository.findById(id).map(user -> {

            if (userDetails.getFullName() != null) {
                user.setFullName(userDetails.getFullName());
            }
            if (userDetails.getPhone() != null) {
                user.setPhone(userDetails.getPhone());
            }
            if (userDetails.getAddress() != null) {
                user.setAddress(userDetails.getAddress());
            }
            if (userDetails.getCity() != null) {
                user.setCity(userDetails.getCity());
            }
            if (userDetails.getProvince() != null) {
                user.setProvince(userDetails.getProvince());
            }
            if (userDetails.getPostalCode() != null) {
                user.setPostalCode(userDetails.getPostalCode());
            }
            if (userDetails.getBirthdate() != null) {
                user.setBirthdate(userDetails.getBirthdate());
            }

            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);

        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ RESET PASSWORD (Temporary)
    public void resetPassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    // DELETE
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}