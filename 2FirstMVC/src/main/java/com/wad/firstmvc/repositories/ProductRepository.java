package com.wad.firstmvc.repositories;

import com.wad.firstmvc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:category IS NULL OR :category = '' OR p.category = :category) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> searchProducts(@Param("category") String category,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);
}
