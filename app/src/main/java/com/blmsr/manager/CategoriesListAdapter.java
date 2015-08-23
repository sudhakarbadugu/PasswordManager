package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blmsr.manager.models.Category;
import com.blmsr.manager.util.IconUtilities;
import com.blmsr.manager.util.StringUtils;

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

        // set the icon
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        img.setImageResource(IconUtilities.getImageResourceID(aData[position].getCategoryIconName()));

        // Set the category name
        TextView t = (TextView) convertView.findViewById(R.id.categoryName);
        t.setText(aData[position].getCategoryName());

        return convertView;
    }
}