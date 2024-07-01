package com.quinbay.reporting.service.OrderServiceInterface;

import com.quinbay.reporting.dto.OrderListDTO;

import java.util.List;

public interface ReportServiceInterface {

    public List<OrderListDTO> getAllOrders();

    public OrderListDTO getOrderById(int orderId);

    public List<OrderListDTO> getOrderByCustName(String custName);

    public List<OrderListDTO> getOrderByEmail(String email);

    public List<OrderListDTO> getOrderByDate(String fromDate, String toDate);

    public String downloadReport();

    public List<OrderListDTO> getByProdName(String prodName);
}
