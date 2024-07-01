package com.quinbay.reporting.dao.repository;

import com.quinbay.reporting.dto.OrderListDTO;
import com.quinbay.reporting.dao.entity.OrderListK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderListK, Integer> {
    List<OrderListDTO> findByOrderBy(String custName);

    List<OrderListDTO> findByCustEmail(String email);

    List<OrderListDTO> findByOrderDateBetween(String from, String to);

    List<OrderListDTO> findByOrderName(String prodName);
}
