package com.quinbay.reporting.service.OrderServiceImpl;

import com.quinbay.reporting.dto.OrderDTO;
import com.quinbay.reporting.dao.entity.Cart;
import com.quinbay.reporting.dao.entity.OrderListK;
import com.quinbay.reporting.dao.entity.Orders;
import com.quinbay.reporting.dao.repository.OrderListRepository;
import com.quinbay.reporting.dao.repository.OrderRepository;
import com.quinbay.reporting.service.OrderServiceInterface.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderServiceInterface {

    private static final String TOPIC = "order_details";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "order_details", groupId = "Group_Order", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderEvent(OrderDTO order) {
        System.out.println("Received order: " + order.getOrderId() + " " + order.getList().size());
        Orders orders = new Orders();
        orders.setOrderId(order.getOrderId());
        orders.setOrderPrice(order.getOrderPrice());
        orders.setOrderQuantity(order.getOrderQuantity());
        orders.setOrderBy(order.getOrderBy());
        orders.setCustEmail(order.getCustEmail());
        orders.setOrderDate(order.getOrderDate());
        List<OrderListK> orderCart = new ArrayList<>();
        for(Cart listDTO : order.getList()){
            OrderListK tempCart = new OrderListK();
            tempCart.setProductId(listDTO.getProductId());
            tempCart.setOrderListId(listDTO.getOrderId());
            tempCart.setOrderName(listDTO.getOrderName());
            tempCart.setOrderQuantity(listDTO.getOrderQuantity());
            tempCart.setOrderPrice(listDTO.getOrderPrice());
            tempCart.setOrderBy(listDTO.getOrderBy());
            tempCart.setOrderDate(listDTO.getOrderDate());
            tempCart.setCustEmail(listDTO.getCustEmail());
            tempCart.setOrders(orders);
            orderCart.add(tempCart);
        }
        orders.setOrderListk(orderCart);
        orderRepository.save(orders);
        System.out.println("Orders Saved!!!");
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.put("Authorization",  new ArrayList<>());
        HttpEntity<OrderDTO> entity = new HttpEntity<>(order, headers);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1001);
        restTemplate.
                exchange("http://localhost:8083/message/sendMessage" , HttpMethod.POST, entity, OrderDTO.class, paramMap);

        System.out.println("Message sended!");
    }
}
