package com.quinbay.inventory.dao.repository;

import com.quinbay.inventory.dao.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepo extends JpaRepository<ProductHistory, Long> {
}
