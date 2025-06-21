package com.shg.ecommerce.service;

import com.shg.ecommerce.model.Product;
import com.shg.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(Product product) {
        productRepository.save(product);
        return "success";
    }
    
    
    @Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}
    

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, String title, String category, double price, byte[] imageBytes) throws Exception {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with ID: " + id));

        existingProduct.setTitle(title);
        existingProduct.setCategory(category);
        existingProduct.setPrice(price);

        if (imageBytes != null && imageBytes.length > 0) {
            Blob blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            existingProduct.setImage(blob);
        }

        return productRepository.save(existingProduct);
    }
    
    
    

    
    
    

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    


    @Override
    public List<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public byte[] getImage(String fname) throws IOException {
        return Files.readAllBytes(Paths.get("uploads/" + fname));
    }






	
}