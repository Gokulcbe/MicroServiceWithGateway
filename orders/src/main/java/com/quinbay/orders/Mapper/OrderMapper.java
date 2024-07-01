package com.quinbay.orders.Mapper;

import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.dao.entity.Order;

public class OrderMapper {
    public static OrderDTO mapToOrderDTO(Order order){
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderPrice(),
                order.getOrderQuantity(),
                order.getOrderBy(),
                order.getCustEmail(),
                order.getOrderDate(),
                order.getList()
        );
    }
}
