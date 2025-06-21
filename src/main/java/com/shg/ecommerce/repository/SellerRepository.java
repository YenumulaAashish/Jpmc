package com.shg.ecommerce.repository;

import com.shg.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmailAndPassword(String email, String password);
    Seller findByEmail(String email);
}
