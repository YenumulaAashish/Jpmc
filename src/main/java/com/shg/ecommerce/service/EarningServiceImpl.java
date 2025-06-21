package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Earning;
import com.shg.ecommerce.repository.EarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarningServiceImpl implements EarningService {

    @Autowired
    private EarningRepository earningRepository;

    @Override
    public Earning addEarning(Earning earning) {
        return earningRepository.save(earning);
    }

    @Override
    public List<Earning> getEarningsBySellerId(Long sellerId) {
        return earningRepository.findBySellerId(sellerId);
    }

    @Override
    public List<Earning> getAllEarnings() {
        return earningRepository.findAll();
    }
}
