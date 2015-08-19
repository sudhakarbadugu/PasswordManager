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
import android.widget.EditText;

import com.blmsr.manager.R;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.util.Dialog;

import java.util.ArrayList;

public class CategoryEntryViewActivity extends CategoryEntriesEditorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_category_entry_view);
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

    protected EditText getEditorText(String theText) {
        EditText aEditText = new EditText(this);
        aEditText.setText(theText);
        aEditText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        aEditText.setWidth(400);
        aEditText.setEnabled(false);

        return aEditText;
    }
}
