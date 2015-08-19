package com.blmsr.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blmsr.manager.R;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.util.Dialog;
import com.blmsr.manager.util.StringUtils;

import java.util.ArrayList;

public class CategoryEntryViewActivity extends CategoryEntriesEditorActivity {
    private static final String CLASSNAME = "CategoryEntryViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fieldCount = 0;
        setContentView(R.layout.activity_category_entry_view);


        itsRootRelativeLayout = (LinearLayout) getWindow().getDecorView().findViewById(R.id.rootLinearLayout);
        Intent anIntent = getIntent();
        {
            itsCategoryEntry = (CategoryEntry) anIntent.getSerializableExtra(CATEGORY_ENTRY);
            itsParentCategory = (Category) anIntent.getSerializableExtra(CategoryEditorActivity.CATEGORY);

            if (itsParentCategory == null) {
                Log.d(CLASSNAME, "category model is null.");
                return;
            }

            try {
                createRequiredFields(itsParentCategory, itsCategoryEntry);
            } catch (Exception anException) {
                Log.e(CLASSNAME, "exception" + anException);
            }

            setTitle(itsParentCategory.getCategoryName());
        }
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
                editCategoryEntry();
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
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentCategory);
        startActivity(anIntent);
        finish();
    }

    private void editCategoryEntry() {
        Intent anIntent = new Intent(this, CategoryEntriesEditorActivity.class);
        anIntent.putExtra(CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY, CategoryEntriesEditorActivity.EDIT_CATEGORY_ENTRY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentCategory);
        anIntent.putExtra(CategoryEntriesEditorActivity.CATEGORY_ENTRY, itsCategoryEntry);
        startActivityForResult(anIntent, RESULT_ID);
        startActivity(anIntent);
    }

    @Override
    protected View getEditorText(String theText) {
        TextView aEditText = new TextView(this);
        aEditText.setText(theText);
        aEditText.setWidth(400);
        aEditText.setTextSize(20);
        aEditText.setEnabled(false);

        return aEditText;
    }

    /**
     * Creates the required fields based on the model data.
     *
     * @param theCategory
     */
    private void createRequiredFields(Category theCategory, CategoryEntry theCategoryEntry) {
        if (!StringUtils.isNullOrEmpty(theCategory.getCategoryName())) {
            addField(theCategory.getCategoryName(), theCategoryEntry.getCategoryName());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField2())) {
            addField(theCategory.getField2(), theCategoryEntry.getField2());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField3())) {
            addField(theCategory.getField3(), theCategoryEntry.getField3());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField4())) {
            addField(theCategory.getField4(), theCategoryEntry.getField4());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField5())) {
            addField(theCategory.getField5(), theCategoryEntry.getField5());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField6())) {
            addField(theCategory.getField6(), theCategoryEntry.getField6());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField7())) {
            addField(theCategory.getField7(), theCategoryEntry.getField7());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField8())) {
            addField(theCategory.getField8(), theCategoryEntry.getField7());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField9())) {
            addField(theCategory.getField9(), theCategoryEntry.getField9());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField10())) {
            addField(theCategory.getField10(), theCategoryEntry.getField10());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField11())) {
            addField(theCategory.getField11(), theCategoryEntry.getField11());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField12())) {
            addField(theCategory.getField12(), theCategoryEntry.getField12());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField13())) {
            addField(theCategory.getField13(), theCategoryEntry.getField13());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField14())) {
            addField(theCategory.getField14(), theCategoryEntry.getField14());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField15())) {
            addField(theCategory.getField15(), theCategoryEntry.getField15());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_ID == requestCode) {
            if (resultCode == RESULT_OK) {
                itsParentCategory = (Category) data.getSerializableExtra(CATEGORY_DATA);
                Log.d("test", "onactivity result");
            }
        }
    }
}
