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

import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.models.CategoryEntryRowData;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntryViewActivity extends ListActivity implements CategoryConstants {
    private static final String CLASSNAME = "CategoryEntryViewActivity";
    protected List<CategoryEntryRowData> itsRowData = new ArrayList<CategoryEntryRowData>();
    private ArrayAdapter itsCategoryViewListAdapter;
    private Category itsParentModel;
    private CategoryEntry itsCategoryEntry;

    /**
     * Share the content using content provider
     */
    private ShareActionProvider itsShareActionProvider;

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

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        itsShareActionProvider = (ShareActionProvider) item.getActionProvider();
        createIntent();
        return true;
    }

    /**
     * Call to update the share intent
     */
    private void setShareIntent(Intent shareIntent) {
        if (itsShareActionProvider != null) {
            itsShareActionProvider.setShareIntent(shareIntent);
        }
    }

    /**
     * Create an Intent to share the data.
     */
    private void createIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getContentForSharing());
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);
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
            case R.id.menu_item_share:
                createIntent();
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


    /**
     * Return the content for sharing.
     *
     * @return
     */
    private String getContentForSharing() {
        StringBuilder aContent = new StringBuilder();

        for (CategoryEntryRowData aData : itsRowData) {
            aContent.append(aData.getFieldName()).append(": ").append(aData.getFieldValue()).append("\n");
        }
        return aContent.toString();
    }

    private ArrayList<CategoryEntryRowData> convertToRowData(Category theCategory, CategoryEntry theCategoryEntry) {
        ArrayList<CategoryEntryRowData> anArrayList = new ArrayList<CategoryEntryRowData>();
        if (theCategoryEntry == null || theCategory == null) {
            return anArrayList;
        }

        if (!StringUtils.isNullOrEmpty(theCategory.getCategoryName())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField1(), theCategoryEntry.getField1(), theCategory.getDefaultFieldVisibilities()[0]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField2())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField2(), theCategoryEntry.getField2(), theCategory.getDefaultFieldVisibilities()[1]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField3())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField3(), theCategoryEntry.getField3(), theCategory.getDefaultFieldVisibilities()[2]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField4())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField4(), theCategoryEntry.getField4(), theCategory.getDefaultFieldVisibilities()[3]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField5())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField5(), theCategoryEntry.getField5(), theCategory.getDefaultFieldVisibilities()[4]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField6())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField6(), theCategoryEntry.getField6(), theCategory.getDefaultFieldVisibilities()[5]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField7())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField7(), theCategoryEntry.getField7(), theCategory.getDefaultFieldVisibilities()[6]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField8())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField8(), theCategoryEntry.getField8(), theCategory.getDefaultFieldVisibilities()[7]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField9())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField9(), theCategoryEntry.getField9(), theCategory.getDefaultFieldVisibilities()[8]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField10())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField10(), theCategoryEntry.getField10(), theCategory.getDefaultFieldVisibilities()[9]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField11())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField11(), theCategoryEntry.getField11(), theCategory.getDefaultFieldVisibilities()[10]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField12())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField12(), theCategoryEntry.getField12(), theCategory.getDefaultFieldVisibilities()[11]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField13())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField13(), theCategoryEntry.getField13(), theCategory.getDefaultFieldVisibilities()[12]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField14())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField14(), theCategoryEntry.getField14(), theCategory.getDefaultFieldVisibilities()[13]);
            anArrayList.add(aRowData);
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField15())) {
            CategoryEntryRowData aRowData = new CategoryEntryRowData(theCategory.getField15(), theCategoryEntry.getField15(), theCategory.getDefaultFieldVisibilities()[14]);
            anArrayList.add(aRowData);
        }

        return anArrayList;
    }
}
