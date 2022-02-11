package com.bookmarkstoreserver.service;

import com.bookmarkstoreserver.entity.Category;

import java.util.List;

/**
 * @Created 11/02/2022 - 11:30
 * @Package com.bookmarkstoreserver.service
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
public interface ICategoryService {

    public List<Category> findAll();

    public Category findByUid(String uid);

    public Category save(Category category);

    public Category update(String uid, Category category);

    public void delete(String uid);

    public Long count();
}
