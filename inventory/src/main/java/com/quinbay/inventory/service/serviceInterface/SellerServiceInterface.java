package com.quinbay.inventory.service.serviceInterface;

import com.quinbay.inventory.dao.entity.SellerDetails;
import com.quinbay.inventory.dto.SellerDTO;

import java.util.List;

public interface SellerServiceInterface {

    public SellerDetails addSeller(SellerDetails seller);

    public List<SellerDTO> getAllSeller();

    public SellerDTO getSeller(long sellerId);
}
