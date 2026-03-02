package com.graphicinfinity.repository;

import com.graphicinfinity.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer userId);
    Optional<Cart> findByUserIdAndProductId(Integer userId, Integer productId);
    void deleteByUserId(Integer userId);
}