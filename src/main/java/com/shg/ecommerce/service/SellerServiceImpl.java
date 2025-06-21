package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Seller;
import com.shg.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository repo;

    @Override
    public Seller register(Seller s) {
        return repo.save(s);
    }

    @Override
    public Seller login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    @Override
    public Seller update(Long id, Seller s) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(s.getName());
                    existing.setEmail(s.getEmail());
                    existing.setPassword(s.getPassword());
                    existing.setPhone(s.getPhone());
                    existing.setAddress(s.getAddress());
                    return repo.save(existing);
                })
                .orElse(null);
    }

    @Override
    public Seller getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Seller> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
