package com.quinbay.inventory.service.serviceImpl;

import com.quinbay.inventory.dao.entity.Category;
import com.quinbay.inventory.dao.repository.CategoryRepo;
import com.quinbay.inventory.service.serviceInterface.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private CategoryRepo repo;

    public Category addCategory(Category category){
        return repo.save(category);
    }

    public List<Category> getAllCategory(){
        return repo.findAll();
    }

    public Optional<Category> getCategory(long categoryId){
        return repo.findById(categoryId);
    }
}
