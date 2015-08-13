package com.blmsr.manager;

import java.util.ArrayList;
import java.util.List;

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

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.service.DatabaseService;

public class CategoryEntriesListActivity extends ListActivity {
    private static final String CLASSNAME = "CategoryEntriesListActivity";
    protected List<CategoryEntry> itsCategoriesList = new ArrayList<CategoryEntry>();
    private ArrayAdapter itsCategoriesListAdapter;
    private Category itsParentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            // Handle the edit category.
            Intent anIntent = getIntent();
            if(CategoryEditorActivity.EDIT_CATEGORY.equals(anIntent.getStringExtra(CategoryEditorActivity.EDIT_CATEGORY)))
            {
                itsParentModel = (Category) anIntent.getSerializableExtra(CategoryEditorActivity.CATEGORY);
                if(itsParentModel == null)
                {
                    Log.d(CLASSNAME, "category model is null.");
                    return ;
                }
            }
            ArrayList<CategoryEntry> aSerializableExtra = fetchAllCategoryEntriesList(itsParentModel.getCategoryId());
            itsCategoriesList.addAll(aSerializableExtra);
            Log.d("CategoriesListActivity", "array size:" + itsCategoriesList.size());
        } catch (Exception an)
        {
            Log.e("CategoriesListActivity", "error while fetch the data");
        }

        itsCategoriesListAdapter = new CategoryEntriesListAdapter(this,itsCategoriesList.toArray(new CategoryEntry[] {}));
        setListAdapter(itsCategoriesListAdapter);
        setContentView(R.layout.activity_tweet_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent anIntent = new Intent(this, CategoryEntriesEditorActivity.class);
        anIntent.putExtra(CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY, CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentModel);
        anIntent.putExtra(CategoryEntriesEditorActivity.CATEGORY_ENTRY, itsCategoriesList.get(position));
        startActivity(anIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories_list, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addCategory(View theView) {
        Intent anIntent = new Intent(this, CategoryEntriesEditorActivity.class);
        anIntent.putExtra(CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY, CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentModel);
        startActivity(anIntent);
    }


    private ArrayList<CategoryEntry> fetchAllCategoryEntriesList(int theCategoryId){
        ArrayList<CategoryEntry> aCategoryList = new ArrayList<CategoryEntry>();

        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();
        aCategoryList.addAll(aCategoryEntryService.getCategoryByID(theCategoryId));

        return  aCategoryList;
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
