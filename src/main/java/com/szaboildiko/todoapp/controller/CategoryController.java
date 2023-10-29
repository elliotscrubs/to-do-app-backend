package com.szaboildiko.todoapp.controller;


import com.szaboildiko.todoapp.persistence.Category;
import com.szaboildiko.todoapp.persistence.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/category")
public class CategoryController {
    @Autowired

    private CategoryRepository categoryRepository;

    @PostMapping()
    public @ResponseBody String addNewCategory (@RequestBody Category category) {
        categoryRepository.save(category);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable Integer id) {

        categoryRepository.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Category updateCategory(@RequestBody Category newCategory, @PathVariable Integer id) {

        return categoryRepository.findById(id)
                .map(category -> {
                    category.setTitle(newCategory.getTitle());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));

    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Category getCategory(@PathVariable Integer id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}

