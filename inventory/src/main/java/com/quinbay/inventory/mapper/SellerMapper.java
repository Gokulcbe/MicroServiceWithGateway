package com.quinbay.inventory.mapper;

import com.quinbay.inventory.dto.SellerDTO;
import com.quinbay.inventory.dao.entity.SellerDetails;

public class SellerMapper {
    public static SellerDTO mapToSellerDTO(SellerDetails sellerDetails){
        return new SellerDTO(
                sellerDetails.getSellerName(),
                sellerDetails.getSellerAddress()
        );
    }
}
