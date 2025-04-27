package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.Order;
import com.example.mvcproducts.domain.User;

import java.util.List;

public interface OrderService {
    Order createOrderFromCart(Cart cart, User user);
    List<Order> getOrdersByUser(User user);
} 