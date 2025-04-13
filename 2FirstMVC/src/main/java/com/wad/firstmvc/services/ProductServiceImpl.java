package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product p) {
        productRepository.save(p);
    }

    @Override
    public List<Product> search(String category, Double minPrice, Double maxPrice) {
        return productRepository.searchProducts(category, minPrice, maxPrice);
    }
}
