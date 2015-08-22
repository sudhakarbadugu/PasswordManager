package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blmsr.manager.models.Category;
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

        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        img.setImageResource(getImageResourceID(aData[position]));

        TextView t = (TextView) convertView.findViewById(R.id.categoryName);
        if (aData[position] != null) {
            if (t != null)
                t.setText(aData[position].getCategoryName());
        }

        return convertView;
    }

    private int getImageResourceID(Category theCategory) {
        int aDefaultIconID = R.drawable.card_icon;

        if ("ic_email_black".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_email_black_24dp;
        } else if ("ic_category_computer_logins".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_category_computer_logins;
        } else if ("ic_bw_bank".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_bw_bank;
        } else if ("ic_web_icon".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_language_black_24dp;
        } else if ("ic_mobile_icon".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_perm_device_information_black_24dp;
        } else if ("card_icon".equals(theCategory.getCategoryIconName())) {
            aDefaultIconID = R.drawable.ic_credit_card_black_24dp;
        }

        return aDefaultIconID;
    }
}