package com.quinbay.inventory.dao.repository;

import com.quinbay.inventory.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    void deleteByProductId(long prodId);
}
