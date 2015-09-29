package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blmsr.manager.models.CategoryEntry;

/**
 * Created by LakshmiMadhav on 7/21/2015.
 */
public class CategoryEntriesListAdapter extends ArrayAdapter<CategoryEntry> {
	private LayoutInflater inflater;
	private CategoryEntry[] aData;

	public CategoryEntriesListAdapter(Activity activity, CategoryEntry[] items) {
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

        ImageView anImageView = (ImageView) convertView.findViewById(R.id.imageView);
        if (aData[position] != null) {
            if (anImageView != null)
                anImageView.setImageResource(R.drawable.ic_email_black_36dp);
        }

		return convertView;
	}
}
