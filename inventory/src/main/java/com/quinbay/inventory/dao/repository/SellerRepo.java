package com.quinbay.inventory.dao.repository;

import com.quinbay.inventory.dao.entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<SellerDetails, Long> {

}
