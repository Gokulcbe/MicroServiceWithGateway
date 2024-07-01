package com.quinbay.reporting.dto;

import lombok.Data;

@Data
public class OrderListDTO {
    private String orderName;
    private int orderQuantity;
    private int orderPrice;
    private String orderBy;
    private String custEmail;
    private String orderDate;

    public OrderListDTO(String orderName,
                        int orderQuantity,
                        int orderPrice,
                        String orderBy,
                        String custEmail,
                        String orderDate){
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderBy = orderBy;
        this.custEmail = custEmail;
        this.orderDate = orderDate;
    }
}
