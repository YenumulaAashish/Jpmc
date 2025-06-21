package com.shg.ecommerce.repository;

import com.shg.ecommerce.model.Product;
import com.shg.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(Seller seller);
    List<Product> findBySellerId(Long sellerId);
}
