package com.blmsr.manager.models;

import java.io.Serializable;

/**
 * Created by LakshmiMadhav on 8/20/2015.
 */
public class CategoryEntryRowData implements Serializable {
    private String fieldName;
    private String fieldValue;
    private boolean isPasswordTypeField;

    public CategoryEntryRowData(String fieldName, String fieldValue, boolean isPasswordTypeField) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.isPasswordTypeField = isPasswordTypeField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public boolean isPasswordTypeField() {
        return isPasswordTypeField;
    }
}
