package com.quinbay.reporting.controller;

import com.quinbay.reporting.dto.OrderListDTO;
import com.quinbay.reporting.service.OrderServiceImpl.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Report")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/getAll")
    public List<OrderListDTO> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/getByOrderId")
    public OrderListDTO getByOrderId(@RequestParam int orderId){
        return service.getOrderById(orderId);
    }

    @GetMapping("/getByCustName")
    public List<OrderListDTO> getByCustName(@RequestParam String custName){
        return service.getOrderByCustName(custName);
    }

    @GetMapping("/getByEmail")
    public List<OrderListDTO> getByEmail(@RequestParam String email){
        return service.getOrderByEmail(email);
    }

    @GetMapping("/getOrdersByDate")
    public List<OrderListDTO> getByOrderDate(@RequestParam String fromDate, @RequestParam String toDate){
        return service.getOrderByDate(fromDate, toDate);
    }

    @GetMapping("/downloadReport")
    public String downloadReport(){
        return service.downloadReport();
    }

    @GetMapping("/getByProdName")
    public List<OrderListDTO> getByProdName(@RequestParam String prodName){
        return service.getByProdName(prodName);
    }
}
