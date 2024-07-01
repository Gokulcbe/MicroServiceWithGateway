package com.quinbay.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quinbay.inventory.mapper.SellerMapper;
import com.quinbay.inventory.dao.entity.Category;
import com.quinbay.inventory.dao.entity.SellerDetails;
import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private double productPrice;
    private int productQuantity;
    private Category category;
    private SellerDTO sellerDTO;

    public ProductDTO(
            @JsonProperty("productName") String productName,
            @JsonProperty("productPrice") double productPrice,
            @JsonProperty("productQuantity") int productQuantity,
            @JsonProperty("category") Category category,
            @JsonProperty("sellerDTO")SellerDetails sellerDetails) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.category = category;
        SellerDTO sellerDTO1 = SellerMapper.mapToSellerDTO(sellerDetails);
        this.sellerDTO = sellerDTO1;
    }
}
