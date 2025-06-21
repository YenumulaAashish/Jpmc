package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Earning;

import java.util.List;

public interface EarningService {
    Earning addEarning(Earning earning);
    List<Earning> getEarningsBySellerId(Long sellerId);
    List<Earning> getAllEarnings();
}
