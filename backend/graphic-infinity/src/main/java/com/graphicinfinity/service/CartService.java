package com.graphicinfinity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphicinfinity.model.Cart;
import com.graphicinfinity.repository.CartRepository;

@Service
@SuppressWarnings("null") 
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    // CREATE - Add to cart
    public Cart addToCart(Cart cart) {
        Optional<Cart> existingItem = cartRepository.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        if (existingItem.isPresent()) {
            Cart item = existingItem.get();
            item.setQuantity(item.getQuantity() + cart.getQuantity());
            return cartRepository.save(item);
        }
        return cartRepository.save(cart);
    }
    
    // READ - Get cart by user ID
    public List<Cart> getCartByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }
    
    // READ - Get cart by cart ID (NEW)
    public Optional<Cart> getCartById(Integer cartId) {
        return cartRepository.findById(cartId);
    }
    
    // UPDATE - Update quantity
    public Cart updateCartItem(Integer cartId, Integer quantity) {
        return cartRepository.findById(cartId).map(cart -> {
            cart.setQuantity(quantity);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new RuntimeException("Cart item not found"));
    }
    
    // DELETE - Remove single item from cart
    public void removeFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }
    
    // DELETE - Clear entire cart
    public void clearCart(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
}