package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Admin;

public interface AdminService {
    Admin login(String email, String password);
}
