package com.blmsr.manager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class CategoriesListActivity extends ListActivity implements CategoryConstants {
    private static final String CLASSNAME = "CategoriesListActivity";
    protected List<Category> itsCategoriesList = new ArrayList<Category>();
    private ArrayAdapter itsCategoriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ArrayList<Category> aSerializableExtra = fetchAllCategoriesList();
            itsCategoriesList.addAll(aSerializableExtra);
            Log.d("CategoriesListActivity", "array size:" + itsCategoriesList.size());
        } catch (Exception an)
        {
            Log.e("CategoriesListActivity", "error while fetch the data");
        }

        itsCategoriesListAdapter = new CategoriesListAdapter(this,itsCategoriesList.toArray(new Category[] {}));
        setListAdapter(itsCategoriesListAdapter);
        setContentView(R.layout.activity_tweet_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories_list, menu);
        return true;
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent anIntent = new Intent(this, CategoryEditorActivity.class);
        anIntent.putExtra(EDIT_CATEGORY, EDIT_CATEGORY);
        anIntent.putExtra(CATEGORY, itsCategoriesList.get(position));
        startActivity(anIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_home:
                goToHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO should do the proper way of returning to parent task.
    private void goToHome() {
        Intent anIntent = new Intent(this, CategoryHomeActivity.class);
        startActivity(anIntent);
        finish();
    }

    public ArrayList<Category> fetchAllCategoriesList(){
        ArrayList<Category> aCategoryList = new ArrayList<Category>();

        CategoryService aCategoryService = DatabaseService.getInstance(this).getCategoryService();
        aCategoryList.addAll(aCategoryService.getCategiries());

        return  aCategoryList;
    }


    public void addCategory(View theView)
    {
        Log.d(CLASSNAME, "Setting add category view");
        Intent anIntent = new Intent(this,
                CategoryEditorActivity.class);
        startActivity(anIntent);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
