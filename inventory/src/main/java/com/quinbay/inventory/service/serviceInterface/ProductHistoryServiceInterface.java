package com.quinbay.inventory.service.serviceInterface;

import com.quinbay.inventory.dao.entity.ProductHistory;
import com.quinbay.inventory.dto.ProductHistoryDTO;

import java.util.List;

public interface ProductHistoryServiceInterface {

    public ProductHistory postProductHistory(ProductHistory productHistory);

    public ProductHistoryDTO getProductHistory(long histId);

    public List<ProductHistoryDTO> getAllProductHistory();
}
