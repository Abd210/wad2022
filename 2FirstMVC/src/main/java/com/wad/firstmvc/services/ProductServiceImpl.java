package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>(List.of(
            new Product(13L, "icecream", 5.0, "food"),
            new Product(25L, "car", 10000.0, "vehicle")
    ));

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product p) {
        if (p.getId() == null) {
            p.setId(new Random().nextLong());
        }
        products.add(p);
    }

    @Override
    public List<Product> search(String category, Double minPrice, Double maxPrice) {
        return products.stream()
                // if category is empty or null, don't filter by category
                .filter(p -> category == null || category.isBlank() || p.getCategory().equalsIgnoreCase(category))
                // if minPrice is null, ignore that filter
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                // if maxPrice is null, ignore that filter
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
