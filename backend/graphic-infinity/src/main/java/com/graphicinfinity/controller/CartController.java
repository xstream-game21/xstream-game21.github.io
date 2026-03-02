package com.graphicinfinity.controller;

import com.graphicinfinity.model.Cart;
import com.graphicinfinity.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    // CREATE - Add to cart
    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        Cart addedItem = cartService.addToCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedItem);
    }
    
    // READ - Get cart item by ID (NEW)
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer cartId) {
        Optional<Cart> cart = cartService.getCartById(cartId);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // READ - Get cart by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Integer userId) {
        List<Cart> cartItems = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }
    
    // UPDATE - Update cart item quantity
    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Integer cartId, @RequestParam Integer quantity) {
        try {
            Cart updatedItem = cartService.updateCartItem(cartId, quantity);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE - Remove from cart
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Integer cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.noContent().build();
    }
    
    // DELETE - Clear entire cart
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Integer userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}