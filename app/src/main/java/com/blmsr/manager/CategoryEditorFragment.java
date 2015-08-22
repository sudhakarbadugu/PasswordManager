package com.blmsr.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import android.support.v4.app.ListFragment;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.service.DatabaseService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * interface
 * to handle interaction events.
 * Use the {@link CategoryEditorFragment} factory method to
 * create an instance of this fragment.
 */
public class CategoryEditorFragment extends ListFragment implements CategoryConstants {
    private static final String CLASSNAME = "CategoryEditorFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tweet_list, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            ArrayList<Category> allCategoriesList = fetchAllCategoriesList();
            ArrayAdapter itsCategoriesListAdapter = new CategoriesListAdapter(getActivity(), allCategoriesList.toArray(new Category[]{}));
            setListAdapter(itsCategoriesListAdapter);
        } catch (Exception an) {
            Log.e("CategoriesListActivity", "error while fetch the data");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent anIntent = new Intent(getActivity(), CategoryEditorActivity.class);
        anIntent.putExtra(EDIT_CATEGORY, EDIT_CATEGORY);
        anIntent.putExtra(CATEGORY, (Category) l.getItemAtPosition(position));
        startActivity(anIntent);
    }

    public ArrayList<Category> fetchAllCategoriesList() {
        ArrayList<Category> aCategoryList = new ArrayList<Category>();

        CategoryService aCategoryService = DatabaseService.getInstance(getActivity()).getCategoryService();
        aCategoryList.addAll(aCategoryService.getCategiries());

        return aCategoryList;
    }
}

