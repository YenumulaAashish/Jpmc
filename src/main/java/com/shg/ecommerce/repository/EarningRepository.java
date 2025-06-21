package com.shg.ecommerce.repository;

import com.shg.ecommerce.model.Earning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarningRepository extends JpaRepository<Earning, Long> {
    List<Earning> findBySellerId(Long sellerId);
}
