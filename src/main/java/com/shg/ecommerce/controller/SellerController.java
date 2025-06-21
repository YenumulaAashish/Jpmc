package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Seller;
import com.shg.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "http://localhost:5173")
public class SellerController {

    @Autowired
    private SellerService srv;

    @PostMapping("/register")
    public Seller register(@RequestBody Seller s) {
        return srv.register(s);
    }

    @PostMapping("/login")
    public Seller login(@RequestBody Seller s) {
        return srv.login(s.getEmail(), s.getPassword());
    }

    @GetMapping("/{id}")
    public Seller getById(@PathVariable Long id) {
        return srv.getById(id);
    }

    @PutMapping("/{id}")
    public Seller update(@PathVariable Long id, @RequestBody Seller s) {
        return srv.update(id, s);
    }

    @GetMapping("/all")
    public List<Seller> getAll() {
        return srv.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        srv.delete(id);
    }
}
