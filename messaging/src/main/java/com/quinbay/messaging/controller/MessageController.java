package com.quinbay.messaging.controller;

import com.quinbay.messaging.dao.entity.Cart;
import com.quinbay.messaging.dto.OrderDTO;
import com.quinbay.messaging.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody OrderDTO order){
        System.out.println("Mail Service Started!");
        String userEmail = order.getCustEmail();
        String subject = "Order Placed Successfully!!!";
        List<Cart> message = order.getList();

        service.sendMessage(userEmail, subject, message);

    }

}
