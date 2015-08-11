package com.blmsr.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Toast;

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.StringUtils;


public class CategoryEntriesEditorActivity extends Activity {
    private static final String CLASSNAME = "CategoryEntriesEditorActivity";
    private static final String LOGGER_NAME = "CategoryEntriesEditorActivity";
    public static final String CATEGORY_ENTRY = "CATEGORY_ENTRY";
    public static final String EDIT_CATEGORY_ENTRY = "EDIT_CATEGORY_ENTRY";
    TextView itsField1Name;
    EditText itsField1Value;
    private int fieldCount = 0;
    Category itsParentCategory;
    CategoryEntry itsCategoryEntry;
    private boolean isUpdateRequest = true;
    LinearLayout itsRootRelativeLayout;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_entries_editor);
        itsRootRelativeLayout = (LinearLayout) getWindow().getDecorView().findViewById(R.id.rootLinearLayout);
        Intent anIntent = getIntent();
         {
             itsCategoryEntry = (CategoryEntry) anIntent.getSerializableExtra(CATEGORY_ENTRY);
            itsParentCategory = (Category) anIntent.getSerializableExtra(CategoryEditorActivity.CATEGORY);

            if (itsParentCategory == null) {
                Log.d(CLASSNAME, "category model is null.");
                return;
            }

            if (itsCategoryEntry == null) {
                itsCategoryEntry = new CategoryEntry();
                isUpdateRequest = false;
            }

            try {
                createRequiredFields(itsParentCategory, itsCategoryEntry);
            } catch (Exception anException) {
                Log.e(CLASSNAME, "exception" + anException);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_entries_editor, menu);
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
            case R.id.action_save:
                saveCategoryEntry();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Save the values to db.
     */
    private void saveCategoryEntry() {
        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();
        CategoryEntry aCategoryModel = getModel();
        if (aCategoryModel != null) {
            String aMessage = "Category entry saved successfully";
            long aNumberOfRowsUpdated = 0;
            if(isUpdateRequest)
            {
                aNumberOfRowsUpdated = aCategoryEntryService.update(aCategoryModel);
                aMessage = "Category entry updated successfully";
            }
            else
            {
                aNumberOfRowsUpdated = aCategoryEntryService.save(aCategoryModel);
            }
            aMessage += "Rows: "+ aNumberOfRowsUpdated;
            Log.d(CLASSNAME, aMessage);
            Toast.makeText(getApplicationContext(), aMessage, Toast.LENGTH_LONG).show();
            Intent anIntent = new Intent(this, CategoryEntriesListActivity.class);
            anIntent.putExtra(CategoryEditorActivity.EDIT_CATEGORY, CategoryEditorActivity.EDIT_CATEGORY);
            anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentCategory);
            startActivity(anIntent);
        }
    }

    /**
     * Returns the model object. It gets the values from the EditText views and sets to the model.
     *
     * @return
     */
    private CategoryEntry getModel() {
        try {
            EditText aField1Value = (EditText) findViewById(R.id.field1Value);
            String aCategoryEntryName = aField1Value.getText().toString();

            // TODO should do with alert dialog.
            if (StringUtils.isNullOrEmpty(aCategoryEntryName)) {
                Toast.makeText(getApplicationContext(), "Category name can't empty", Toast.LENGTH_LONG).show();
                return null;
            }
            itsCategoryEntry.setCategoryName(aCategoryEntryName);
            itsCategoryEntry.setCategoryId(itsParentCategory.getCategoryId());
            for (int i = 0; i < itsRootRelativeLayout.getChildCount(); i++) {
                View aLinearLayoutView = itsRootRelativeLayout.getChildAt(i);
                if (aLinearLayoutView instanceof LinearLayout) {
                    LinearLayout aLinearLayout = (LinearLayout) aLinearLayoutView;
                    EditText anEditText = (EditText) aLinearLayout.getChildAt(1);
                    setValuesToModel(itsCategoryEntry, i+1, anEditText.getText().toString());
                }
            }

            // Validate the fields.
            if (StringUtils.isNullOrEmpty(itsCategoryEntry.getField1())) {
                // TODO should a proper validation message.
                Toast.makeText(getApplicationContext(), "Atleast one Field should be created", Toast.LENGTH_LONG).show();
                return null;
            }
        } catch (Exception anException) {
            Log.d(CLASSNAME, "Exception occurred" + anException);
        }

        return itsCategoryEntry;
    }

    /**
     * Sets the value to model.
     * @param theModel
     * @param theFieldIndexNumber
     * @param theFieldValue
     */
    private void setValuesToModel(CategoryEntry theModel, int theFieldIndexNumber, String theFieldValue)
    {
        switch (theFieldIndexNumber)
        {
            case 1 :
                theModel.setCategoryName(theFieldValue);
                theModel.setField1(theFieldValue);
                break;
            case 2 :
                theModel.setField2(theFieldValue);
                break;
            case 3 :
                theModel.setField3(theFieldValue);
                break;
            case 4 :
                theModel.setField4(theFieldValue);
                break;
            case 5 :
                theModel.setField5(theFieldValue);
                break;
            case 6 :
                theModel.setField6(theFieldValue);
                break;
            case 7 :
                theModel.setField7(theFieldValue);
                break;
            case 8 :
                theModel.setField8(theFieldValue);
                break;
            case 9 :
                theModel.setField9(theFieldValue);
                break;
            case 10 :
                theModel.setField10(theFieldValue);
                break;
            case 11 :
                theModel.setField11(theFieldValue);
                break;
            case 12 :
                theModel.setField12(theFieldValue);
                break;
            case 13 :
                theModel.setField13(theFieldValue);
                break;
            case 14 :
                theModel.setField14(theFieldValue);
                break;
            case 15 :
                theModel.setField15(theFieldValue);
                break;
        }
    }

    /**
     * performs the delete.
     */
    private void deleteCategoryEntry() {
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
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsParentCategory);
        startActivity(anIntent);
    }


    /**
     * Creates the required fields based on the model data.
     *
     * @param theCategory
     */
    private void createRequiredFields(Category theCategory, CategoryEntry theCategoryEntry) {
        if (!StringUtils.isNullOrEmpty(theCategory.getCategoryName())) {
            itsField1Name = (TextView) findViewById(R.id.field1Name);
            itsField1Value = (EditText) findViewById(R.id.field1Value);
            itsField1Name.setText(theCategory.getField1());
            itsField1Value.setText(theCategoryEntry.getCategoryName());
            fieldCount++;
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

    private LinearLayout createLinearLayout() {
        LinearLayout aLinearLayout = new LinearLayout(this);
        aLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return aLinearLayout;
    }

    private TextView getTextView(String theText) {
        TextView aTextView = new TextView(this);
        aTextView.setWidth(200);
        aTextView.setText(theText);

        return aTextView;
    }

    private EditText getEditorText(String theText) {
        EditText aEditText = new EditText(this);
        aEditText.setText(theText);
        aEditText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        aEditText.setHint("Enter ");
        aEditText.setWidth(400);

        return aEditText;
    }

    private LinearLayout getLayout(String theTextViewText, String theEditViewHint) {
        LinearLayout aLinearLayout = createLinearLayout();
        aLinearLayout.addView(getTextView(theTextViewText));
        aLinearLayout.addView(getEditorText(theEditViewHint));

        return aLinearLayout;
    }

    private void addField(String theTextViewText, String theEditViewText) {
        if (fieldCount < 15) {
            itsRootRelativeLayout.addView(getLayout(theTextViewText, theEditViewText), fieldCount);
            fieldCount++;
            Log.d(CLASSNAME, "filed count is: " + fieldCount);
        } else {
            Toast.makeText(getApplicationContext(), "Only 15 fields can be created", Toast.LENGTH_LONG).show();
        }
    }
}
