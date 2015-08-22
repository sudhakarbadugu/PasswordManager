package com.blmsr.manager;

import android.app.Activity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        final TextView aTextViewFieldValue = (TextView) convertView.findViewById(R.id.categoryEntryValue);
        CheckBox aCheckBoxField = (CheckBox) convertView.findViewById(R.id.isPasswordTypeCheckbox);

        if (aData[position] != null) {
            if (aTextViewFieldName != null)
                aTextViewFieldName.setText(aData[position].getFieldName());

            if (aCheckBoxField != null) {
                // add onCheckedListener on checkbox
                // when user clicks on this checkbox, this is the handler.
                aCheckBoxField.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // checkbox status is changed from uncheck to checked.
                        if (!isChecked) {
                            // show password
                            aTextViewFieldValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            // hide password
                            aTextViewFieldValue.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                });

                aTextViewFieldValue.setText(aData[position].getFieldValue());
                if (aData[position].isPasswordTypeField()) {
                    aTextViewFieldValue.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    aCheckBoxField.setVisibility(View.INVISIBLE);
                }
            }
        }

        return convertView;
    }
}
