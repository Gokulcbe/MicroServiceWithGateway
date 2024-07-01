package com.quinbay.reporting.dao.repository;

import com.quinbay.reporting.dao.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
