package com.example.mvcproducts.services;


import com.example.mvcproducts.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    void save(Product p);
    Optional<Product> findById(Long id);
}
