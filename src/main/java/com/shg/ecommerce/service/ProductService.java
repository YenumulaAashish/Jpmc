package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
   public String addProduct(Product product);
   public List<Product> getAllProducts();
   public Product getProductById(Long id);
   
   Product updateProduct(Long id, String title, String category, double price, byte[] imageBytes) throws Exception;

   
   
   
   
   
   
    //Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> getProductsBySellerId(Long sellerId);
   

    List<Product> all(); // optional
    byte[] getImage(String fname) throws IOException;
}