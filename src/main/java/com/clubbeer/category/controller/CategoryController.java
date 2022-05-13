package com.clubbeer.category.controller;

import com.clubbeer.category.resource.CategoryResource;
import com.clubbeer.category.service.CategoryService;
import com.clubbeer.common.utils.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private static final String ENTITY_NAME = "category";
    private static final String BASE_URL_CATEGORIES = "/categories";

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(BASE_URL_CATEGORIES)
    public ResponseEntity<List<CategoryResource>> findAll() {
        log.debug("REST request to get all Categories");
        return ResponseEntity.ok(categoryService.getCategoryAll());
    }

    @GetMapping(BASE_URL_CATEGORIES + "/{id}")
    public ResponseEntity<CategoryResource> findAll(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Categories by id: ", id);
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping(BASE_URL_CATEGORIES)
    public ResponseEntity<CategoryResource> createCategory(@Valid @RequestBody CategoryResource category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);
        CategoryResource result = categoryService.createCategory(category);
        return ResponseEntity
                .created(new URI(String.format("%s/%s", BASE_URL_CATEGORIES, result.getId())))
                .headers(HeaderUtil.createEntityCreationAlert( true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping(BASE_URL_CATEGORIES + "/{id}")
    public ResponseEntity<CategoryResource> updateCategory(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody CategoryResource category){
        log.debug("REST request to update Category : {}, {}", id, category);
        CategoryResource result = categoryService.updateCategory(id, category);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, category.getId().toString()))
                .body(result);
    }

    @DeleteMapping(BASE_URL_CATEGORIES + "/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        log.debug("REST request to delete Category");
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
