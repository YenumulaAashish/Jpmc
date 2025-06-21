package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Seller;
import java.util.List;

public interface SellerService {
    Seller register(Seller s);
    Seller login(String email, String password);
    Seller update(Long id, Seller s);
    Seller getById(Long id);
    List<Seller> getAll();
    void delete(Long id);
}
