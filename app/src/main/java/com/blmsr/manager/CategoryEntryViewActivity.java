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
import android.widget.CheckBox;
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
            Log.e(CLASSNAME, "error while set the row data", an);
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
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField1());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField1(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField2())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField2());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField2(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField3())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField3());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField3(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField4())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField4());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField4(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField5())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField5());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField5(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField6())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField6());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField6(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField7())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField7());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField7(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField8())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField8());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField8(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField9())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField9());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField9(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField10())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField10());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField10(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField11())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField11());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField11(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField12())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField12());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField12(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField13())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField13());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField13(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField14())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField14());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField14(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField15())) {
            String[] aFieldAndVisibilityArray = StringUtils.split(theCategory.getField15());
            CategoryEntryRowData aRowData = new CategoryEntryRowData(aFieldAndVisibilityArray[0], theCategoryEntry.getField15(), Boolean.parseBoolean(aFieldAndVisibilityArray[1]));
            anArrayList.add(aRowData);
        }

        return anArrayList;
    }
}
