package com.quinbay.orders.service.serviceInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.orders.dao.entity.Cart;
import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public interface OrderServiceInterface {

//    public String redisCache(String key, String value);

//    public void initialize();

    public List<OrderDTO> getAllOrders() throws JsonProcessingException;

    public OrderDTO getOrder(int orderId) throws JsonProcessingException;

    public ArrayList<Cart> addToCart(Cart cart);

    public List<OrderDTO> placeOrder() throws JsonProcessingException;

    public void updateStock(int prodId, int order_quantity);

    public String deleteCart(int orderId);

    public List<OrderDTO> getCart(int cartId) throws JsonProcessingException;

    public ProductDTO getProductDetails(long prodId);


}
