package com.blmsr.manager;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.blmsr.manager.models.Category;


public class CategoriesFragment extends CategoryEditorFragment implements CategoryConstants {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.categories_fragment_list, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent anIntent = new Intent(getActivity(), CategoryEntriesListActivity.class);
        anIntent.putExtra(CategoryEditorActivity.EDIT_CATEGORY, CategoryEditorActivity.EDIT_CATEGORY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, (Category) l.getItemAtPosition(position));
        startActivity(anIntent);
    }
}