package com.quinbay.inventory.mapper;

import com.quinbay.inventory.dto.ProductHistoryDTO;
import com.quinbay.inventory.dao.entity.ProductHistory;

public class ProductHistoryMapper {
    public static ProductHistoryDTO mapToProductHistoryDTO(ProductHistory productHistory){
        return new ProductHistoryDTO(
                productHistory.getOldValue(),
                productHistory.getNewValue(),
                productHistory.getColName(),
                productHistory.getProductId(),
                productHistory.getModifiedBy(),
                productHistory.getDate()
        );
    }
}
