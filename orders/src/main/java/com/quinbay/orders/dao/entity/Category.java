package com.quinbay.orders.dao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private long categoryId;
    private String categoryName;
    private List<Product> products;
}
