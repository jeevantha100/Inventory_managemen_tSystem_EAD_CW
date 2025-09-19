package com.inventory_service.service;

import com.inventory_service.data.Product;
import com.inventory_service.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setCategory(updatedProduct.getCategory());
        product.setUnitPrice(updatedProduct.getUnitPrice());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        product.setReorderLevel(updatedProduct.getReorderLevel());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findByStockQuantityLessThanEqual(10); // or dynamic
    }

    public boolean reduceStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);
        return true;
    }

}
