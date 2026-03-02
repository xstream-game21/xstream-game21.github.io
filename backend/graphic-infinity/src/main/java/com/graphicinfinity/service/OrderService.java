package com.graphicinfinity.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphicinfinity.model.Order;
import com.graphicinfinity.model.OrderStatus;
import com.graphicinfinity.repository.OrderRepository;

@Service
@SuppressWarnings("null") 
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    // CREATE - Create order with auto-generated order number
    public Order createOrder(Order order) {
        // Auto-generate order number if not provided
        if (order.getOrderNumber() == null || order.getOrderNumber().isEmpty()) {
            String orderNumber = "ORD-" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            order.setOrderNumber(orderNumber);
        }
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
    
    // READ - Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    // READ - Get order by ID
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }
    
    // READ - Get orders by user ID
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
    
    // UPDATE - Update order status
    public Order updateOrderStatus(Integer id, OrderStatus status) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderStatus(status);
            order.setUpdatedAt(LocalDateTime.now());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    // DELETE
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}