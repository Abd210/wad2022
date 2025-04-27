package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Order;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public String viewOrders(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Order> orders = orderService.getOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "orders";
    }
} 