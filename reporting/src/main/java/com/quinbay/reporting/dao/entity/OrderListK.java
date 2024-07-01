package com.quinbay.reporting.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="orderListK")
@Entity
public class OrderListK {
    @Id
    private int orderListId;
    private int productId;
    private String orderName;
    private int orderQuantity;
    private int orderPrice;
    private String orderBy;
    private String custEmail;
    private String orderDate;
//    private int cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
//    @JsonIgnoreProperties("category")
    @JsonBackReference
    private Orders orders;
}
