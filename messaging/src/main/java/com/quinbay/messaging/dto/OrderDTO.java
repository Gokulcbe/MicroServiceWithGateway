package com.quinbay.messaging.dto;


import com.quinbay.messaging.dao.entity.Cart;
import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {
    private int orderId;
    private int orderQuantity;
    private int orderPrice;
    private String orderBy;
    private String custEmail;
    private String orderDate;
    private List<Cart> list;

    public OrderDTO(int orderId, int orderPrice, int orderQuantity,String orderBy, String custEmail, String orderDate, List<Cart> list) {
        this.orderId = orderId;
        this.orderQuantity= orderQuantity;
        this.orderPrice = orderPrice;
        this.orderBy = orderBy;
        this.custEmail = custEmail;
        this.orderDate = orderDate;
        this.list = list;
    }
}

