package com.inventory_service.controller;

import com.inventory_service.data.Product;
import com.inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStock() {
        return productService.getLowStockProducts();
    }

    @PutMapping("/reduce-stock/{id}")
    public ResponseEntity<?> reduceStock(@PathVariable Long id, @RequestParam int quantity) {
        try {
            productService.reduceStock(id, quantity);
            return ResponseEntity.ok("Stock reduced");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
