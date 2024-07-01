package com.quinbay.reporting.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quinbay.reporting.dao.entity.Cart;
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


    public OrderDTO() {}

    @JsonCreator
    public OrderDTO(@JsonProperty("orderId") int orderId,
                    @JsonProperty("orderPrice") int orderPrice,
                    @JsonProperty("orderQuantity") int orderQuantity,
                    @JsonProperty("orderBy") String orderBy,
                    @JsonProperty("custEmail") String custEmail,
                    @JsonProperty("orderDate") String orderDate,
                    @JsonProperty("list") List<Cart> list
    ) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.orderBy = orderBy;
        this.custEmail = custEmail;
        this.orderDate = orderDate;
        this.list = list;
    }
}
