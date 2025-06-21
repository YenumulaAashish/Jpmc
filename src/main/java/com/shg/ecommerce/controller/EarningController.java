package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Earning;
import com.shg.ecommerce.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earnings")
@CrossOrigin(origins = "http://localhost:5173")
public class EarningController {

    @Autowired
    private EarningService earningService;

    @PostMapping("/add")
    public Earning addEarning(@RequestBody Earning earning) {
        return earningService.addEarning(earning);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Earning> getEarningsBySeller(@PathVariable Long sellerId) {
        return earningService.getEarningsBySellerId(sellerId);
    }

    @GetMapping("/all")
    public List<Earning> getAllEarnings() {
        return earningService.getAllEarnings();
    }
}
