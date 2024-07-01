package com.quinbay.inventory.service.serviceImpl;

import com.quinbay.inventory.mapper.SellerMapper;
import com.quinbay.inventory.dto.SellerDTO;
import com.quinbay.inventory.dao.entity.SellerDetails;
import com.quinbay.inventory.dao.repository.SellerRepo;
import com.quinbay.inventory.service.serviceInterface.SellerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService implements SellerServiceInterface {
    @Autowired
    private SellerRepo repo;

    public SellerDetails addSeller(SellerDetails seller){
        return repo.save(seller);
    }

    public List<SellerDTO> getAllSeller(){
        List<SellerDetails> sellers =  repo.findAll();
        List<SellerDTO> sellerDTOs = new ArrayList<>();
        for(SellerDetails seller : sellers){
            sellerDTOs.add(SellerMapper.mapToSellerDTO(seller));
        }
        return sellerDTOs;
    }

    public SellerDTO getSeller(long sellerId){
        Optional<SellerDetails> seller =  repo.findById(sellerId);
        SellerDetails seller1 = seller.orElseThrow(() -> new RuntimeException("Product not found"));
        SellerDTO sellerDTO = SellerMapper.mapToSellerDTO(seller1);
        return sellerDTO;
    }
}
