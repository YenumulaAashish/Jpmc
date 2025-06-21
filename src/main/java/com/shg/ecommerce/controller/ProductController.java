package com.shg.ecommerce.controller;

import com.shg.ecommerce.model.Product;
import com.shg.ecommerce.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.sql.Blob;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	
	
    @Autowired
    private ProductService productService;
    
    
    @GetMapping("addproduct")
    public ModelAndView addproductdemo()
    {
 	   ModelAndView mv = new ModelAndView("addproduct");
 	   return mv;
    }
    
    @PostMapping(value = "/insertproduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String insertProduct(
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam("productimage") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

            Product p = new Product();
            p.setTitle(title);
            p.setCategory(category);
            p.setPrice(price);
            p.setImage(blob);

            String msg = productService.addProduct(p);
            return msg;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    @GetMapping("viewallprods")
    public ModelAndView viewallprodsdemo()
    {
 	   List<Product> productlist = productService.getAllProducts();
 	   
 	   ModelAndView mv = new ModelAndView("viewproducts");
 	   
 	   mv.addObject("productlist", productlist);
 	   
 	   return mv;
    }
    
    @GetMapping("/all")
    public List<Product> getAllProductsRest() {
        return productService.getAllProducts();
    }

    
    
 @GetMapping("displayprodimage")
 public ResponseEntity<byte[]> displayprodimagedemo(@RequestParam Long id) throws Exception
 {
   Product product =  productService.getProductById(id);
   byte [] imageBytes = null;
   imageBytes = product.getImage().getBytes(1,(int) product.getImage().length());

   return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
   
 // Response Body, HTTP Status Code, Headers
 }
 
 @PutMapping("/updateproduct/{id}")
 public ResponseEntity<String> updateProduct(
         @PathVariable Long id,
         @RequestParam String title,
         @RequestParam String category,
         @RequestParam double price,
         @RequestParam(value = "productimage", required = false) MultipartFile productimage
 ) {
     try {
         byte[] imageBytes = null;
         if (productimage != null && !productimage.isEmpty()) {
             imageBytes = productimage.getBytes();
         }

         productService.updateProduct(id, title, category, price, imageBytes);
         return ResponseEntity.ok("Product updated successfully.");
     } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product.");
     }
 }
 

    
    
    
    
    
    

    // Upload image and return filename (not full path)
    /*@PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile file) throws IOException {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // create uploads folder if it doesn't exist
        }

        String fileName = file.getOriginalFilename();
        String filePath = UPLOAD_DIR + File.separator + fileName;
        file.transferTo(new File(filePath));

        // Return just the filename to store in DB
        return fileName;
    }*/

    // Create product
   /* @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }*/

    // Update product
   /* @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Get all products by seller
    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySellerId(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Serve image directly
   /* @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String filename) throws IOException {
        File imageFile = new File(UPLOAD_DIR + File.separator + filename);
        return Files.readAllBytes(imageFile.toPath());*/
    //}
}

















