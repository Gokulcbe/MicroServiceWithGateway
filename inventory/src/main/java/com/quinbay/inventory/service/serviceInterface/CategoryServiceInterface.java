package com.quinbay.inventory.service.serviceInterface;

import com.quinbay.inventory.dao.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {

    public Category addCategory(Category category);

    public List<Category> getAllCategory();

    public Optional<Category> getCategory(long categoryId);
}
