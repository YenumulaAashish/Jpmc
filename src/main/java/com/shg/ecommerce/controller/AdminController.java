package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Admin;
import com.shg.ecommerce.model.Product;
import com.shg.ecommerce.model.Seller;
import com.shg.ecommerce.service.AdminService;
import com.shg.ecommerce.service.ProductService;
import com.shg.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminSrv;

    @Autowired
    private SellerService sellerSrv;

    @Autowired
    private ProductService productService;

    // ✅ Admin Login
    @PostMapping("/login")
    public String login(@RequestBody Admin a) {
        return adminSrv.login(a.getEmail(), a.getPassword()) != null ? "success" : "fail";
    }

    // ✅ View all Sellers
    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return sellerSrv.getAll();
    }

    // ✅ Search Seller by Email
    @GetMapping("/sellers/email/{mail}")
    public Seller getByEmail(@PathVariable("mail") String mail) {
        return sellerSrv.getAll().stream()
                .filter(s -> s.getEmail().equals(mail))
                .findFirst()
                .orElse(null);
    }

    // ✅ Update Seller by ID
    @PutMapping("/sellers/{id}")
    public Seller updateSeller(@PathVariable Long id, @RequestBody Seller s) {
        return sellerSrv.update(id, s);
    }

    // ✅ Delete Seller by ID
    @DeleteMapping("/sellers/{id}")
    public void deleteSeller(@PathVariable Long id) {
        sellerSrv.delete(id);
    }

    // ✅ View All Products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
