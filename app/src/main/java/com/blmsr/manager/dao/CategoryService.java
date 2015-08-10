package com.blmsr.manager.dao;

import com.blmsr.manager.models.Category;

import java.util.List;

/**
 * Created by LakshmiMadhav on 8/4/2015.
 */
public interface CategoryService {
    public long save(Category theEntity);
    public long update(Category theEntity);
    public long delete(Category theEntity);
    public Category getCategiryByID(int theId);
    public List<Category> getCategiries();
}
