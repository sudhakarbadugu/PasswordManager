package com.blmsr.manager.models;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class CategoryEntry extends Category {
    public int getCategoryEntryId() {
        return categoryEntryId;
    }

    public void setCategoryEntryId(int categoryEntryId) {
        this.categoryEntryId = categoryEntryId;
    }

    private int categoryEntryId;
}
