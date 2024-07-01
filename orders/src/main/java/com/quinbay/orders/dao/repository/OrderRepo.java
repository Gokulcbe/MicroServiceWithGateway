package com.quinbay.orders.dao.repository;

import com.quinbay.orders.dao.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {
    List<Order> findByCartId(int cartId);
}
