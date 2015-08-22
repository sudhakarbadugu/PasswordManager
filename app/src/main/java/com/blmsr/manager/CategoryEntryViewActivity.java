package com.blmsr.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blmsr.manager.R;
import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.models.CategoryEntryRowData;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.Dialog;
import com.blmsr.manager.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntryViewActivity extends ListActivity implements CategoryConstants {
    private static final String CLASSNAME = "CategoryEntryViewActivity";
    protected List<CategoryEntryRowData> itsRowData = new ArrayList<CategoryEntryRowData>();
    private ArrayAdapter itsCategoryViewListAdapter;
    private Category itsParentModel;
    private CategoryEntry itsCategoryEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // Handle the edit category.
            Intent anIntent = getIntent();
            {
                itsCategoryEntry = (CategoryEntry) anIntent.getSerializableExtra(CATEGORY_ENTRY);
                itsParentModel = (Category) anIntent.getSerializableExtra(CategoryEditorActivity.CATEGORY);
                if (itsParentModel == null) {
                    Log.d(CLASSNAME, "category model is null.");
                    return;
                }
            }
            // add the data to row data object.
            itsRowData.addAll(convertToRowData(itsParentModel, itsCategoryEntry));

        } catch (Exception an) {
            Log.e(CLASSNAME, "error while set the row data");
        }

        setTitle(itsParentModel.getCategoryName());
        itsCategoryViewListAdapter = new CategoryEntryViewListAdapter(this, itsRowData.toArray(new CategoryEntryRowData[]{}));
        setListAdapter(itsCategoryViewListAdapter);
        setContentView(R.layout.activity_category_entry_view_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_entry_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteCategoryEntry();
                return true;
            case R.id.action_edit:
                editCategoryEntry(null);
                return true;
            case R.id.action_settings:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onBackPressed() {
        Intent anIntent = new Intent(this, CategoryEntriesListActivity.class);
        anIntent.putExtra(CategoryEditorActivity.EDIT_CATEGORY, CategoryEditorActivity.EDIT_CATEGORY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentModel);
        startActivity(anIntent);
        finish();
    }

    public void editCategoryEntry(View theView) {
        Intent anIntent = new Intent(this, CategoryEntriesEditorActivity.class);
        anIntent.putExtra(EDIT_CATEGORY_ENTRY, EDIT_CATEGORY_ENTRY);
        anIntent.putExtra(CATEGORY, itsParentModel);
        anIntent.putExtra(CATEGORY_ENTRY, itsCategoryEntry);
        startActivity(anIntent);
    }

    /**
     * performs the delete.
     */
    protected void deleteCategoryEntry() {
        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();
        Intent anIntent = getIntent();
        if (EDIT_CATEGORY_ENTRY.equals(anIntent.getStringExtra(EDIT_CATEGORY_ENTRY))) {
            CategoryEntry aCategoryEntry = (CategoryEntry) anIntent.getSerializableExtra(CATEGORY_ENTRY);
            if (aCategoryEntry == null) {
                Log.d(CLASSNAME, "category model is null. Can't be deleted.");
                return;
            }

            aCategoryEntryService.delete(aCategoryEntry);
        }

        Toast.makeText(getApplicationContext(), "Category deleted successfully", Toast.LENGTH_LONG).show();
        anIntent = new Intent(this, CategoryEntriesListActivity.class);
        anIntent.putExtra(CategoryEditorActivity.EDIT_CATEGORY, CategoryEditorActivity.EDIT_CATEGORY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentModel);
        startActivity(anIntent);
    }

    private ArrayList<CategoryEntryRowData> convertToRowData(Category theCategory, CategoryEntry theCategoryEntry) {
        ArrayList<CategoryEntryRowData> anArrayList = new ArrayList<CategoryEntryRowData>();
        if (theCategoryEntry == null || theCategory == null) {
            return anArrayList;
        }

        if (!StringUtils.isNullOrEmpty(theCategory.getCategoryName())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getCategoryName(), theCategoryEntry.getField1());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField2())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField2(), theCategoryEntry.getField2());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField3())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField3(), theCategoryEntry.getField3());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField4())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField4(), theCategoryEntry.getField4());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField5())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField5(), theCategoryEntry.getField5());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField6())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField6(), theCategoryEntry.getField6());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField7())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField7(), theCategoryEntry.getField7());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField8())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField8(), theCategoryEntry.getField8());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField9())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField9(), theCategoryEntry.getField9());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField10())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField10(), theCategoryEntry.getField10());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField11())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField11(), theCategoryEntry.getField11());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField12())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField12(), theCategoryEntry.getField12());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField13())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField13(), theCategoryEntry.getField13());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField14())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField14(), theCategoryEntry.getField14());
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField15())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField15(), theCategoryEntry.getField15());
            anArrayList.add(aRowData);
        }

        return anArrayList;
    }
}
