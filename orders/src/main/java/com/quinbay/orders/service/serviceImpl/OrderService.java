package com.quinbay.orders.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quinbay.orders.Mapper.OrderMapper;
import com.quinbay.orders.dto.OrderDTO;
import com.quinbay.orders.dto.ProductDTO;
import com.quinbay.orders.dao.entity.Cart;
import com.quinbay.orders.dao.entity.Order;
import com.quinbay.orders.dao.entity.Product;
import com.quinbay.orders.dao.repository.OrderRepo;
import com.quinbay.orders.service.serviceInterface.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

@Service
public class OrderService implements OrderServiceInterface {

    ArrayList<Cart> cartList = new ArrayList<Cart>();
    int cartId = 1;
    Random random = new Random();

    private static final String TOPIC = "order_details";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo repo;

    public List<OrderDTO> getAllOrders() throws JsonProcessingException {
        List<Order> orders =  repo.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(Order order : orders){
            orderDTOs.add(OrderMapper.mapToOrderDTO(order));
        }
        return orderDTOs;
    }

    public OrderDTO getOrder(int orderId) throws JsonProcessingException {
        Optional<Order> order =  repo.findById(orderId);
        Order order1 = order.orElseThrow(() -> new RuntimeException("Product not found"));
        OrderDTO orderDTO = OrderMapper.mapToOrderDTO(order1);
        String message = "Order Got for ID : " + orderId;
//        kafkaTemplate.send("order_topic", message);
        return orderDTO;
    }

    public ArrayList<Cart> addToCart(Cart cart){
        cartList.add(cart);
        String message = "Order added to cart : " + cart.getOrderId();
//        kafkaTemplate.send("order_topic", message);
        return cartList;
    }

    public List<OrderDTO> placeOrder() throws JsonProcessingException {
        int totalPrice = 0;
        int totalQuantity = cartList.size();
        int randomNumber = random.nextInt();
        String orderBy = "null";
        String custEmail = "null";
        String orderDate = "null";
        ArrayList<Cart> updatedCart = new ArrayList<Cart>();
        for(Cart cart : cartList){
            ProductDTO product = getProductDetails(cart.getProductId());
            int randomOrderNumber = random.nextInt();
            orderBy = cart.getOrderBy();
            custEmail = cart.getCustEmail();
            orderDate = cart.getOrderDate();
            cart.setOrderId(randomOrderNumber);
            cart.setOrderName(product.getProductName());
            cart.setOrderPrice((int)product.getProductPrice()*cart.getOrderQuantity());
            cart.setCartId(randomNumber);
            totalPrice = totalPrice+((int)product.getProductPrice()*cart.getOrderQuantity());
            updateStock(cart.getProductId(),cart.getOrderQuantity());
            updatedCart.add(cart);
        }
            Order order = new Order();
            order.setOrderId(randomNumber);
            order.setOrderPrice(totalPrice);
            order.setOrderQuantity(totalQuantity);
            order.setOrderBy(orderBy);
            order.setCustEmail(custEmail);
            order.setOrderDate(orderDate);
            order.setList(updatedCart);
            repo.save(order);
            OrderDTO orderDTO = OrderMapper.mapToOrderDTO(order);
            kafkaTemplate.send(TOPIC, orderDTO);
            System.out.println(orderDTO);

            cartList = new ArrayList<>();
            cartId++;
            return getAllOrders();

    }

    public void updateStock(int prodId, int order_quantity){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.put("Authorization",  new ArrayList<>());
//        HttpEntity<String> entity = new HttpEntity<>(headers);
        Product product = new Product();
        product.setProductId(prodId);
        product.setProductQuantity(order_quantity);
        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1001);
//        ProductDTO response = restTemplate.
        restTemplate.
                exchange("http://localhost:8765/product/updateStock" , HttpMethod.PUT, entity, ProductDTO.class, paramMap);
    }

    public String deleteCart(int orderId){
        repo.deleteById(orderId);
        return "Deleted from Cart";
    }

    public List<OrderDTO> getCart(int cartId) throws JsonProcessingException {
        List<Order> cart = repo.findByCartId(cartId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for(Order order : cart){
            orderDTOs.add(OrderMapper.mapToOrderDTO(order));
        }
        return orderDTOs;
    }

    public ProductDTO getProductDetails(long prodId){
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.put("Authorization",  new ArrayList<>());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1001);
        ProductDTO response = restTemplate.
                exchange("http://localhost:8765/product/getProduct/" + prodId, HttpMethod.GET, entity, ProductDTO.class, paramMap).getBody();
        return response;
    }


}
