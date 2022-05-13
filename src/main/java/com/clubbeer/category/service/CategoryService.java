package com.clubbeer.category.service;

import com.clubbeer.category.entity.Category;
import com.clubbeer.category.repository.ICategoryRepository;
import com.clubbeer.category.resource.CategoryResource;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public CategoryResource getCategoryById(Long id) throws Exception {
        Category category = iCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception());
        return new CategoryResource(category);
    }

    public List<CategoryResource> getCategoryAll() {
        List<Category> categories = iCategoryRepository.findAll();
        return convertEntityToResource(categories);
    }

    public CategoryResource createCategory(CategoryResource categoryResource) {
        Category category = iCategoryRepository.save(categoryResource.toEntity());
        return new CategoryResource(category);
    }

    public CategoryResource updateCategory(Long id, CategoryResource categoryResource) {
        categoryResource.setId(id);
        Category category = iCategoryRepository.save(categoryResource.toEntity());
        return new CategoryResource(category);
    }

    public void deleteCategoryById(Long id) {
        iCategoryRepository.deleteById(id);
    }

    public List<CategoryResource> convertEntityToResource(List<Category> categories){
        return categories.stream()
                .filter(Objects::nonNull)
                .map(CategoryResource::new)
                .collect(Collectors.toList());
    }
}
