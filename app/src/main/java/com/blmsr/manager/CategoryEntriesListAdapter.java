/*
 * Copyright 2015 Merge Healthcare Incorporated. All Rights Reserved.
 *
 * This software is property of Merge Healthcare Incorporated.
 * Unauthorized access to, copying or distribution of the software is
 * prohibited. The software and the intellectual property embodied herein
 * is protected by law, including without limitation the copyright laws
 * of the United States.
 */
package com.blmsr.manager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

		return convertView;
	}
}
