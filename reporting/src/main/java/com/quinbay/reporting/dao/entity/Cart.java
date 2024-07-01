package com.quinbay.reporting.dao.entity;


public class Cart {
    private int orderId;
    private int productId;
    private int cartId;
    private String orderName;
    private int orderQuantity;
    private int orderPrice;
    private String orderDate;
    private String orderBy;
    private String custEmail;

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getCartId(){
        return cartId;
    }

    public void setCartId(int cartId){
        this.cartId = cartId;
    }

    public int getProductId(){
        return productId;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public String getOrderName(){
        return orderName;
    }

    public void setOrderName(String orderName){
        this.orderName = orderName;
    }

    public String getOrderBy(){
        return orderBy;
    }

    public void setOrderBy(String orderBy){
        this.orderBy = orderBy;
    }

    public String getCustEmail(){
        return custEmail;
    }

    public void setCustEmail(String custEmail){
        this.custEmail = custEmail;
    }

    public String getOrderDate(){
        return orderDate;
    }

    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }

    public int getOrderQuantity(){
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity){
        this.orderQuantity = orderQuantity;
    }

    public int getOrderPrice(){
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice){
        this.orderPrice = orderPrice;
    }
}
