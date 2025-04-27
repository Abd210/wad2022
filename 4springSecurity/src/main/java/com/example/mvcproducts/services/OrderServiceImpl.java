package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.Order;
import com.example.mvcproducts.domain.OrderItem;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @Override
    @Transactional
    public Order createOrderFromCart(Cart cart, User user) {
        if (cart.isEmpty()) {
            return null;
        }
        
        Order order = new Order(user);
        
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            order.addOrderItem(orderItem);
        });
        
        return orderRepository.save(order);
    }
    
    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
} 