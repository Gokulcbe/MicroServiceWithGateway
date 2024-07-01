package com.quinbay.orders.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection= "Order")
public class Order {
    @Id
    private int orderId;
    private int orderQuantity;
    private int orderPrice;
    private String orderBy;
    private String custEmail;
    private String orderDate;
    private List<Cart> list;
    private int cartId;
}
