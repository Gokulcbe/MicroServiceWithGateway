package com.quinbay.messaging.service;

import com.quinbay.messaging.dao.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private JavaMailSender emailSender;

    public static String generateOrderTable(List<Cart> orders) {
        StringBuilder html = new StringBuilder();
        html.append("<table border='1'>")
                .append("<tr>")
                .append("<th>orderId</th>")
                .append("<th>productId</th>")
                .append("<th>orderName</th>")
                .append("<th>cartId</th>")
                .append("<th>orderQuantity</th>")
                .append("<th>orderPrice</th>")
                .append("<th>orderBy</th>")
                .append("<th>custEmail</th>")
                .append("<th>orderDate</th>")
                .append("</tr>");

        for (Cart order : orders) {
            html.append("<tr>")
                    .append("<td>").append(order.getOrderId()).append("</td>")
                    .append("<td>").append(order.getProductId()).append("</td>")
                    .append("<td>").append(order.getOrderName()).append("</td>")
                    .append("<td>").append(order.getCartId()).append("</td>")
                    .append("<td>").append(order.getOrderQuantity()).append("</td>")
                    .append("<td>").append(order.getOrderPrice()).append("</td>")
                    .append("<td>").append(order.getOrderBy()).append("</td>")
                    .append("<td>").append(order.getCustEmail()).append("</td>")
                    .append("<td>").append(order.getOrderDate()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        return html.toString();
    }

    public void sendMessage(String userEmail, String subject, List<Cart> message){
        String mes = generateOrderTable(message);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(userEmail);
        mail.setFrom("gokugokul185@gmail.com");
        mail.setSubject(subject);
        mail.setText(mes);

        emailSender.send(mail);
        System.out.println("Mail Sended Successfully!!");
    }
}
