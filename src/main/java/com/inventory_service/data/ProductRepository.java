package com.inventory_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStockQuantityLessThanEqual(Integer level);
}
