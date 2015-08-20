package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntryRowData;

/**
 * Created by LakshmiMadhav on 8/20/2015.
 */
public class CategoryEntryViewListAdapter extends ArrayAdapter<CategoryEntryRowData> {

    private LayoutInflater inflater;
    private CategoryEntryRowData[] aData;

    public CategoryEntryViewListAdapter(Activity activity, CategoryEntryRowData[] items) {
        super(activity, R.layout.row_category_view_list, items);
        inflater = activity.getWindow().getLayoutInflater();
        aData = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // We must create a View:
            convertView = inflater.inflate(R.layout.row_category_view_list, parent, false);
        }

        TextView aTextViewFieldName = (TextView) convertView.findViewById(R.id.categoryEntryName);
        TextView aTextViewFieldValue = (TextView) convertView.findViewById(R.id.categoryEntryValue);
        if (aData[position] != null) {
            if (aTextViewFieldName != null)
                aTextViewFieldName.setText(aData[position].getFieldName());

            if (aTextViewFieldValue != null)
                aTextViewFieldValue.setText(aData[position].getFieldValue());

        }

        return convertView;
    }
}
