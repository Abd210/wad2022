package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
} 