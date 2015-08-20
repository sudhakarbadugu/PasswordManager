package com.blmsr.manager.models;

import java.io.Serializable;

/**
 * Created by LakshmiMadhav on 8/20/2015.
 */
public class CategoryEntryRowData implements Serializable {
    private String fieldName;
    private String fieldValue;

    public CategoryEntryRowData(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
