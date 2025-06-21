package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Admin;
import com.shg.ecommerce.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired private AdminRepository repo;
    public Admin login(String e, String p) {
        return repo.findByEmailAndPassword(e, p);
    }
}
