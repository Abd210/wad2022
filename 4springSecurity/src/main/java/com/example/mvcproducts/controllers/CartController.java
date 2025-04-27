package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.OrderService;
import com.example.mvcproducts.services.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;
    private final OrderService orderService;

    public CartController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping("/add")
    public String addToCart(@RequestParam Long pid, @ModelAttribute("cart") Cart cart) {
        Optional<Product> productOpt = productService.findById(pid);
        
        productOpt.ifPresent(cart::addProduct);
        
        return "redirect:/products";
    }

    @GetMapping
    public String viewCart(@ModelAttribute("cart") Cart cart, Model model) {
        model.addAttribute("cartItems", cart.getCartItems());
        return "cart";
    }
    
    @PostMapping("/update")
    public String updateCartItemQuantity(
            @RequestParam Long pid,
            @RequestParam int quantity,
            @ModelAttribute("cart") Cart cart) {
        
        cart.updateQuantity(pid, quantity);
        return "redirect:/cart";
    }
    
    @GetMapping("/remove")
    public String removeFromCart(@RequestParam Long pid, @ModelAttribute("cart") Cart cart) {
        cart.removeProduct(pid);
        return "redirect:/cart";
    }
    
    @GetMapping("/clear")
    public String clearCart(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return "redirect:/cart";
    }
    
    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("cart") Cart cart, Authentication authentication) {
        if (!cart.isEmpty()) {
            User user = (User) authentication.getPrincipal();
            orderService.createOrderFromCart(cart, user);
            cart.clear();
            return "redirect:/orders";
        }
        return "redirect:/cart";
    }
} 