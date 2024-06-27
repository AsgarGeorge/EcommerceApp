package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final List<Category> categories = new ArrayList<>();
    private Long nextInt = 1L;


    @Override
    public List<Category> getAllCategories() {

        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextInt++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        categories.remove(category);
        return "category with " + categoryId + " deleted successfully";

    }
}
