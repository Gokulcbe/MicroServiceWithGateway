package com.quinbay.reporting.service.OrderServiceImpl;

import com.quinbay.reporting.dto.OrderListDTO;
import com.quinbay.reporting.dao.entity.OrderListK;
import com.quinbay.reporting.mapper.OrderListMapper;
import com.quinbay.reporting.dao.repository.OrderListRepository;
import com.quinbay.reporting.service.OrderServiceInterface.ReportServiceInterface;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

@Service
public class ReportService implements ReportServiceInterface {
    static Sheet sheet;

    @Autowired
    private OrderListRepository repo;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static LocalDateTime parseCustomDate(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public List<OrderListDTO> getAllOrders()
    {
        List<OrderListK> orderList = repo.findAll();
        List<OrderListDTO> orderListDTOs = new ArrayList<>();
        for(OrderListK orderl : orderList){
            orderListDTOs.add(OrderListMapper.mapToOrderDTO(orderl));
        }
        return orderListDTOs;
    }

    public OrderListDTO getOrderById(int orderId){
        Optional<OrderListK> order =  repo.findById(orderId);
        OrderListK order1 = order.get();
        return OrderListMapper.mapToOrderDTO(order1);
    }

    public List<OrderListDTO> getOrderByCustName(String custName){
        return repo.findByOrderBy(custName);
    }

    public List<OrderListDTO> getOrderByEmail(String email){
        return repo.findByCustEmail(email);
    }

    public List<OrderListDTO> getOrderByDate(String fromDate, String toDate){
        return repo.findByOrderDateBetween(fromDate, toDate);
    }

    public String downloadReport(){
        try(Workbook workbook = new XSSFWorkbook()){
            sheet = workbook.createSheet("MongoDBData");
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Order Name", "Order Quantity", "Order Price", "Order By", "email", "date"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }
            List<OrderListDTO> orders = getAllOrders();
            int rowNum = 1;
            for (OrderListDTO order : orders) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrderName());
                row.createCell(1).setCellValue(order.getOrderQuantity());
                row.createCell(2).setCellValue(order.getOrderPrice());
                row.createCell(3).setCellValue(order.getOrderBy());
                row.createCell(4).setCellValue(order.getCustEmail());
                row.createCell(5).setCellValue(order.getOrderDate());
            }
            try (FileOutputStream fileOut = new FileOutputStream("MongoDBData.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Excel file written successfully.");
            } catch (IOException e) {
                System.err.println("Error while writing Excel file: " + e.getMessage());
            }
        } catch (Exception e){
            System.out.println("Couldn't create Excel file!!! ");
        }
        return "Excel File Downloaded";
    }

    public List<OrderListDTO> getByProdName(String prodName){
        return repo.findByOrderName(prodName);
    }
}
