package com.graphicinfinity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphicinfinity.model.OrderItem;
import com.graphicinfinity.repository.OrderItemRepository;

@Service
@SuppressWarnings("null") 
public class OrderItemService {
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    // CREATE - Add order item
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    
    // READ - Get all order items
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
    
    // READ - Get by order item ID
    public Optional<OrderItem> getOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }
    
    // READ - Get items by order ID
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
    
    // UPDATE - Update order item
    public OrderItem updateOrderItem(Integer id, OrderItem itemDetails) {
        return orderItemRepository.findById(id).map(item -> {
            if (itemDetails.getQuantity() != null) {
                item.setQuantity(itemDetails.getQuantity());
            }
            if (itemDetails.getPrice() != null) {
                item.setPrice(itemDetails.getPrice());
            }
            if (itemDetails.getItemTotal() != null) {
                item.setItemTotal(itemDetails.getItemTotal());
            }
            return orderItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Order item not found"));
    }
    
    // DELETE
    public void deleteOrderItem(Integer id) {
        orderItemRepository.deleteById(id);
    }
}