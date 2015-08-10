package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blmsr.manager.models.Category;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class CategoriesListAdapter extends ArrayAdapter<Category> {

    private LayoutInflater inflater;
    private Category[] aData;

    public CategoriesListAdapter(Activity activity, Category[] items) {
        super(activity, R.layout.row_categories_list, items);
        inflater = activity.getWindow().getLayoutInflater();
        aData = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // We must create a View:
            convertView = inflater.inflate(R.layout.row_categories_list, parent, false);
        }

        TextView t = (TextView) convertView.findViewById(R.id.categoryName);
        if (aData[position] != null) {
            if (t != null)
                t.setText(aData[position].getCategoryName());
        }

        return convertView;
    }
}
