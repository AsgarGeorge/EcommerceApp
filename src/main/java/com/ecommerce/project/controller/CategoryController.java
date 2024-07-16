package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
            categoryService.createCategory(category);
            return new ResponseEntity<>("Category Added successfully",HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
       try{
           Category savedCategory = categoryService.updateCategory(category,categoryId);
           return new ResponseEntity<>("The category is updated with Id: " + categoryId , HttpStatus.OK);

       }catch(ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }


//    @PutMapping("/api/admin/categories")
//    public ResponseEntity<String> updateCategory(@RequestBody Category category){
//        String status = categoryService.updateCategory(category);
//        return new ResponseEntity<>(status,HttpStatus.OK);
//    }
}
