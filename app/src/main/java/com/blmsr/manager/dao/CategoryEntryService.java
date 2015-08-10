package com.blmsr.manager.dao;

import com.blmsr.manager.models.CategoryEntry;


import java.util.List;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface CategoryEntryService {

    public long save(CategoryEntry theEntity);
    public long update(CategoryEntry theEntity);
    public long delete(CategoryEntry theEntity);
    public List<CategoryEntry> getCategoryByID(int theCategoryID);
    public List<CategoryEntry> getCategories();
}
