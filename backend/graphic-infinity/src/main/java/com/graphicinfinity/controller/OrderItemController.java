package com.graphicinfinity.controller;

import com.graphicinfinity.model.OrderItem;
import com.graphicinfinity.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin(origins = "*")
public class OrderItemController {
    
    @Autowired
    private OrderItemService orderItemService;
    
    // CREATE - POST
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }
    
    // READ - GET all order items
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> items = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(items);
    }
    
    // READ - GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Integer id) {
        Optional<OrderItem> item = orderItemService.getOrderItemById(id);
        return item.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // READ - GET items by order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Integer orderId) {
        List<OrderItem> items = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }
    
    // UPDATE - PUT
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem itemDetails) {
        try {
            OrderItem updatedItem = orderItemService.updateOrderItem(id, itemDetails);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}