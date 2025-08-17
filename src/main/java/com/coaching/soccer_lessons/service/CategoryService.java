package com.coaching.soccer_lessons.service;

import com.coaching.soccer_lessons.dto.CategoryDTO;
import com.coaching.soccer_lessons.model.Category;
import com.coaching.soccer_lessons.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    //Does not need to bring in LessonRepository because it does not touch Lessons
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //CREATE
    public CategoryDTO createCategory (CategoryDTO cDTO){
        Category category = new Category();
        category.setName(cDTO.getName());
        category.setDescription(cDTO.getDescription());

        Category saved = categoryRepository.save(category);
        return mapToCategoryDTO(saved);
    }

    //READ ALL
    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream()
                .map(this::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    //READ ONE -> Could make 404 Not found error instead
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return mapToCategoryDTO(category);
    }

    //UPDATE
    public CategoryDTO updateCategory(Long id, CategoryDTO cDTO){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(cDTO.getName());
        category.setDescription(cDTO.getDescription());

        Category updated = categoryRepository.save(category);
        return mapToCategoryDTO(updated);
    }

    //DELETE
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }





    //Mapping Utility
    private CategoryDTO mapToCategoryDTO(Category category){
        CategoryDTO cDTO = new CategoryDTO();
        cDTO.setId(category.getId());
        cDTO.setName(category.getName());
        cDTO.setDescription(category.getDescription());
        return cDTO;
    }


}
