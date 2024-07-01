package com.quinbay.reporting.mapper;

import com.quinbay.reporting.dto.OrderListDTO;
import com.quinbay.reporting.dao.entity.OrderListK;

public class OrderListMapper {
    public static OrderListDTO mapToOrderDTO(OrderListK order){
        return new OrderListDTO(
                order.getOrderName(),
                order.getOrderQuantity(),
                order.getOrderPrice(),
                order.getOrderBy(),
                order.getCustEmail(),
                order.getOrderDate()
        );
    }
}
