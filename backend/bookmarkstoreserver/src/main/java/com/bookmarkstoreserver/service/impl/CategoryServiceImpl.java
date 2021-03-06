package com.bookmarkstoreserver.service.impl;

import com.bookmarkstoreserver.entity.Category;
import com.bookmarkstoreserver.exception.custom.ResourceAlreadyExistsException;
import com.bookmarkstoreserver.exception.custom.ResourceNotFoundException;
import com.bookmarkstoreserver.repositoy.CategoryRepository;
import com.bookmarkstoreserver.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Created 11/02/2022 - 11:35
 * @Package com.bookmarkstoreserver.service.impl
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/

@Transactional
@Service
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByUid(String uid) {
        if (uid.isEmpty()) {
            throw new IllegalArgumentException("uid cannot be empty");
        }
        Category category = categoryRepository.findByUid(uid);
        if (category == null) {
            throw new ResourceNotFoundException("Cannot find category with uid: " + uid);
        }
        return category;
    }

    @Override
    public Category save(Category category) {
        Category categoryNameAlreadyExist = categoryRepository.findByNameContainingIgnoreCase(category.getName());
        if (categoryNameAlreadyExist != null) {
            throw new ResourceAlreadyExistsException(String.format("Category with provided name: %s, already exist" , category.getName()));
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(String uid, Category category) {
        Category getCategory = findByUid(uid);
        getCategory.setName(category.getName());
        getCategory.setDescription(category.getDescription());
        getCategory.setColor(category.getColor());
        getCategory.setIcon(category.getIcon());
        getCategory.setParent(category.getParent());
        Category updatedCategory = categoryRepository.save(getCategory);
        return updatedCategory;
    }

    @Override
    public void delete(String uid) {
        Category getCategory = findByUid(uid);
        categoryRepository.delete(getCategory);
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }
}
