package com.example.mvcproducts.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private Map<Product, Integer> items;
    
    public Cart() {
        this.items = new HashMap<>();
    }
    
    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }
    
    public List<CartItem> getCartItems() {
        return items.entrySet().stream()
                .map(entry -> new CartItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int getItemCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    public void removeProduct(Long productId) {
        items.entrySet().removeIf(entry -> entry.getKey().getId().equals(productId));
    }
    
    public void updateQuantity(Long productId, int quantity) {
        items.entrySet().stream()
                .filter(entry -> entry.getKey().getId().equals(productId))
                .findFirst()
                .ifPresent(entry -> {
                    if (quantity <= 0) {
                        items.remove(entry.getKey());
                    } else {
                        items.put(entry.getKey(), quantity);
                    }
                });
    }
    
    public void clear() {
        items.clear();
    }
    
    public static class CartItem {
        private Product product;
        private int quantity;
        
        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        
        public Product getProduct() {
            return product;
        }
        
        public void setProduct(Product product) {
            this.product = product;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
} 