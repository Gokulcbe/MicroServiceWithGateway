package com.quinbay.inventory.service.serviceImpl;

import com.quinbay.inventory.mapper.ProductMapper;
import com.quinbay.inventory.dto.ProductDTO;
import com.quinbay.inventory.dao.entity.Product;
import com.quinbay.inventory.dao.entity.ProductHistory;
import com.quinbay.inventory.dao.repository.ProductHistoryRepo;
import com.quinbay.inventory.dao.repository.ProductRepo;
import com.quinbay.inventory.service.serviceInterface.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepo repository;

    @Autowired
    private ProductHistoryRepo Historyrepo;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String TOPIC = "my_topic";

//    @Cacheable(value = "ProductDetails", key="#prodId")
    public ProductDTO getProduct(long prodId) {
        Optional<Product> product =  repository.findById(prodId);
        Product product1 = product.orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDTO productDTO = ProductMapper.mapToProductDTO(product1);
        return productDTO;
    }

    public Product getProductById(long prodId){
        Optional<Product> prod = repository.findById(prodId);
        Product product = prod.get();
        return product;
    }

    public List<ProductDTO> getAllProduct(){
        List<Product> products =  repository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products){
            productDTOs.add(ProductMapper.mapToProductDTO(product));
        }
//        kafkaTemplate.send("GetAll", "Products got");
        return productDTOs;
    }

    public Product postProduct(Product product){
        repository.save(product);
        return product;
    }

    @CachePut(value = "ProductDetails", key="#product.productId")
    public ProductDTO updateProduct(Product product){
        updateProductHistory(product);
        Product prod = repository.saveAndFlush(product);
        return ProductMapper.mapToProductDTO(prod);
    }

    @CachePut(value = "ProductDetails", key="#prodId")
    public ProductDTO updateStock(long prodId, int stock){
        Optional<Product> prod = repository.findById(prodId);
        Product product = prod.get();
        product.setProductQuantity(stock);
        Product updatedProduct = repository.saveAndFlush(product);
        return ProductMapper.mapToProductDTO(updatedProduct);
    }

    @CacheEvict(value = "ProductDetails", key="#prodId")
    public String deleteProduct(long prodId){
        repository.deleteById(prodId);
        return "Product deleted : " + prodId;
    }

    @CacheEvict(value = "ProductDetails", key="#prodId")
    public String deleteCache(long prodId){
        return "Cache Deleted";
    }

    public String updateProductHistory(Product product)
    {
            if (product.getProductName() != null  && product.getProductPrice() > 0 && product.getProductQuantity() > 0) {
                Product oldProduct=getProductById(product.getProductId());
                if(!oldProduct.getProductName().equals(product.getProductName()))
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product name");
                    productHistory.setOldValue(oldProduct.getProductName());
                    productHistory.setNewValue(product.getProductName());
                    Historyrepo.save(productHistory);
                }
                if(oldProduct.getProductQuantity()!=product.getProductQuantity())
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product quantity");
                    productHistory.setOldValue(oldProduct.getProductQuantity()+"");
                    productHistory.setNewValue(product.getProductQuantity()+"");
//                    productHistory.setDate(currentDate+"");
                    Historyrepo.save(productHistory);
                }
                if(oldProduct.getProductPrice()!=product.getProductPrice())
                {
                    ProductHistory productHistory=new ProductHistory();
                    productHistory.setProductId(oldProduct.getProductId());
                    productHistory.setColName("product cost");
                    productHistory.setOldValue(oldProduct.getProductPrice()+"");
                    productHistory.setNewValue(product.getProductPrice()+"");
//                    productHistory.setDate(currentDate+"");
                    Historyrepo.save(productHistory);
                }
            } else {
                return "invalid input";
            }
            return "History Updated";
        }
}
