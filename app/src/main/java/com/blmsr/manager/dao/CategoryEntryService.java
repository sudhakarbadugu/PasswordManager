package com.blmsr.manager.dao;

import com.blmsr.manager.models.CategoryEntry;


import java.util.List;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public interface CategoryEntryService {

    long save(CategoryEntry theEntity);

    long update(CategoryEntry theEntity);

    /**
     * Deletes only selected Category entry.
     *
     * @param theEntity
     * @return
     */
    long delete(CategoryEntry theEntity);

    List<CategoryEntry> getCategoryByID(int theCategoryID);

    List<CategoryEntry> getCategories();

    /**
     * Returns true if atleast one entry is exist with the category ID.
     *
     * @param theCategoryID Category ID.
     * @return true if atleast one entry is exist with the category ID.
     */
    boolean isCategoryEntriesExist(int theCategoryID);

    /**
     * Delete the category entries based on the parent table, category_id.
     *
     * @param theCategoryID Category_Id parent table id.
     * @return no of rows deleted.
     * Note: It will remove all the entries with the category_id.
     */
    long delete(int theCategoryID);
}
