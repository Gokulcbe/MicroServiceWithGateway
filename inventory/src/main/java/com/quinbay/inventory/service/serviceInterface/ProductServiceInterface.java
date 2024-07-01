package com.quinbay.inventory.service.serviceInterface;

import com.quinbay.inventory.dao.entity.Product;
import com.quinbay.inventory.dto.ProductDTO;

import java.util.List;

public interface ProductServiceInterface {

    public ProductDTO getProduct(long prodId);

    public Product getProductById(long prodId);

//    public void handleOrderEvent(String message);

    public List<ProductDTO> getAllProduct();

    public Product postProduct(Product product);

    public ProductDTO updateProduct(Product product);

    public ProductDTO updateStock(long prodId, int stock);

    public String deleteProduct(long prodId);

    public String updateProductHistory(Product product);
}
