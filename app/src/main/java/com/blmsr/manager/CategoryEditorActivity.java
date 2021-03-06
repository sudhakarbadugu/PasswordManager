package com.blmsr.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.Dialog;
import com.blmsr.manager.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryEditorActivity extends Activity implements CategoryConstants {
    private final String CLASSNAME = "CategoryEditorActivity";
    private static Set<String> itsCategoriesNames = new HashSet<String>();
    private int fieldCount = 0;
    LinearLayout itsLinearLayout;
    Category itsCategory;
    private boolean isUpdateRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_editor);
        itsLinearLayout = (LinearLayout) findViewById(R.id.linearLayout2);

        // Handle the edit category.
        Intent anIntent = getIntent();
        if (EDIT_CATEGORY.equals(anIntent.getStringExtra(EDIT_CATEGORY))) {
            itsCategory = (Category) anIntent.getSerializableExtra(CATEGORY);
            if (itsCategory == null) {
                Log.d(CLASSNAME, "category model is null.");
                return;
            }

            isUpdateRequest = true;
            EditText aCategoryNameField = (EditText) findViewById(R.id.categoryNameEditText);
            aCategoryNameField.setText(itsCategory.getCategoryName());
            createRequiredFields(itsCategory);
            Log.d(CLASSNAME, "successfully set the text to category name");

            setTitle(itsCategory.getCategoryName() + " Category");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteCategory();
                return true;
            case R.id.action_save:
                saveCategory();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

    /**
     * Adds a new row which contains the EditText and a delete button.
     *
     * @param theView
     */
    public void addField(View theView) {
        addField((String) null);
    }

    private void addField(String theEditTextContent) {
        if (fieldCount < 15) {
            itsLinearLayout.addView(tableLayout(theEditTextContent), itsLinearLayout.getChildCount() - 1);
            fieldCount++;
            Log.d(CLASSNAME, "filed count is: " + fieldCount);
        } else {
            Toast.makeText(getApplicationContext(), "Only 15 fields can be created", Toast.LENGTH_LONG).show();
        }
    }

    // Using a TableLayout as it provides you with a neat ordering structure

    private TableLayout tableLayout(String theFieldName) {
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.addView(createRowView(theFieldName));
        return tableLayout;
    }

    private TableRow createRowView(String theFieldName) {
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);

        EditText editText = new EditText(this);
        editText.setWidth(480);
        editText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

        // Array contains the field name and its visibility.
        // Set the name to edit text.
        if (StringUtils.isNotNull(theFieldName)) {
            editText.setText(theFieldName);
        }
        editText.requestFocus();
        tableRow.addView(editText);


        CheckBox aCheckBox = new CheckBox(this);
        tableRow.addView(aCheckBox);

        // Hide the check box for the 1st component.
        if (fieldCount == 0) {
            aCheckBox.setVisibility(View.INVISIBLE);
        }

        boolean isChecked = itsCategory != null ? itsCategory.getDefaultFieldVisibilities()[fieldCount] : false;

        // Retain the selection from db values.
        aCheckBox.setChecked(isChecked);

        ImageButton btnGreen = new ImageButton(this);
        btnGreen.setImageResource(R.drawable.ic_delete);
        btnGreen.setBackgroundColor(Color.TRANSPARENT);
        btnGreen.setOnClickListener(anImageButtonListener);
        tableRow.addView(btnGreen);

        return tableRow;
    }

    /**
     * Delete the row when clicked on the remove button.
     */
    private View.OnClickListener anImageButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TableRow anTableRow = (TableRow) v.getParent();
            TableLayout anTable = (TableLayout) anTableRow.getParent();
            itsLinearLayout.removeView(anTable);
            fieldCount--;

        }
    };

    /**
     * Save the values to db.
     */
    private void saveCategory() {
        CategoryService aCategoryService = DatabaseService.getInstance(this).getCategoryService();
        Category aCategory = getModel();
        if (aCategory != null) {

            String aMessage = "Category saved successfully";
            long aNumberOfRowsUpdated = 0;
            if (isUpdateRequest) {
                aNumberOfRowsUpdated = aCategoryService.update(aCategory);
                aMessage = "Category updated successfully";
            } else {
                aNumberOfRowsUpdated = aCategoryService.save(aCategory);
            }
            itsCategoriesNames.add(aCategory.getCategoryName());
            aMessage += "Rows: " + aNumberOfRowsUpdated;
            Log.d(CLASSNAME, aMessage);
            Toast.makeText(getApplicationContext(), aMessage, Toast.LENGTH_LONG).show();
            Intent anIntent = new Intent(this, CategoryHomeTabbedActivity.class);
            startActivity(anIntent);
        }
    }

    /**
     * performs the delete.
     */
    private void deleteCategory() {
        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();

        final Intent[] anIntent = {getIntent()};
        if (EDIT_CATEGORY.equals(anIntent[0].getStringExtra(EDIT_CATEGORY))) {
            final Category aCategory = (Category) anIntent[0].getSerializableExtra(CATEGORY);
            if (aCategory == null) {
                Log.d(CLASSNAME, "category model is null. Can't be deleted.");
                return;
            }

            boolean isCategoryEntriesExist = aCategoryEntryService.isCategoryEntriesExist(aCategory.getCategoryId());

            if (isCategoryEntriesExist) {
                new AlertDialog.Builder(this)
                        .setTitle(Dialog.WARNING_MESSAGE_TITLE)
                        .setMessage("This category is having entries. Do you want to delete " + aCategory.getCategoryName() + " entry and its entries? This can't be undone.")
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        })
                        .setPositiveButton(R.string.delete_Category, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                //delete the entry
                                performDelete();
                            }
                        }).create().show();

                return;
            }

            // delete the entry
            performDelete();
        }
    }

    /**
     * performs the actual delete. Delete the category_entries first then delete the category.
     */
    private void performDelete() {
        final CategoryService aCategoryService = DatabaseService.getInstance(this).getCategoryService();
        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();

        final Intent[] anIntent = {getIntent()};
        if (EDIT_CATEGORY.equals(anIntent[0].getStringExtra(EDIT_CATEGORY))) {
            final Category aCategory = (Category) anIntent[0].getSerializableExtra(CATEGORY);
            if (aCategory == null) {
                Log.d(CLASSNAME, "category model is null. Can't be deleted.");
                return;
            }

            // Delete all the category entries first then delete the category.
            aCategoryEntryService.delete(aCategory.getCategoryId());
            aCategoryService.delete(aCategory);
            itsCategoriesNames.remove(aCategory.getCategoryName());
            Toast.makeText(getApplicationContext(), "Category deleted successfully", Toast.LENGTH_LONG).show();
            anIntent[0] = new Intent(CategoryEditorActivity.this, CategoryHomeTabbedActivity.class);
            startActivity(anIntent[0]);
        }
    }


    /**
     * Returns the model object. It gets the values from the EditText views and sets to the model.
     *
     * @return
     */
    private Category getModel() {
        itsCategory = itsCategory == null ? new Category() : itsCategory;
        try {
            EditText anCategoryNameEditText = (EditText) findViewById(R.id.categoryNameEditText);
            String aCategoryName = anCategoryNameEditText.getText().toString();

            if (StringUtils.isNullOrEmpty(aCategoryName)) {
                Dialog.showValidationMessageDialog(this, "Category name can't empty");
                return null;
            }
            if (itsCategoriesNames.contains(aCategoryName)) {
                Dialog.showValidationMessageDialog(this, "Category name already exist");
                return null;
            }

            itsCategory.setCategoryName(aCategoryName);
            boolean[] aFieldsVisibility = itsCategory.getDefaultFieldVisibilities();
            for (int i = 0; i < itsLinearLayout.getChildCount(); i++) {
                View aTableLayOutView = itsLinearLayout.getChildAt(i);
                if (aTableLayOutView instanceof TableLayout) {
                    TableLayout aTableLayout = ((TableLayout) aTableLayOutView);
                    for (int j = 0; j < aTableLayout.getChildCount(); j++) {
                        TableRow anTableRow = (TableRow) aTableLayout.getChildAt(j);
                        if (anTableRow != null) {
                            EditText anEditText = (EditText) anTableRow.getChildAt(0);
                            if (anEditText == null || StringUtils.isNullOrEmpty(anEditText.getText().toString())) {

                                // show a validation message.
                                Dialog.showValidationMessageDialog(this, "Field name can't empty");
                                return null;
                            }
                            CheckBox aCheckBox = (CheckBox) anTableRow.getChildAt(1);
                            boolean isPasswordTypeField = aCheckBox != null ? aCheckBox.isChecked() : false;

                            setValuesToModel(itsCategory, i, anEditText.getText().toString(), isPasswordTypeField, aFieldsVisibility);
                        }
                    }
                }
            }

            // Validate the fields.
            if (StringUtils.isNullOrEmpty(itsCategory.getField1())) {
                itsCategory = null;
                Dialog.showValidationMessageDialog(this, "At least one Field should be created");
            }

            //set the default visibilities to model
            itsCategory.setDefaultFieldVisibilities(aFieldsVisibility);

        } catch (Exception anException) {
            Log.d(CLASSNAME, "Exception occurred" + anException);
        }

        return itsCategory;
    }

    /**
     * Sets the value to model.
     *  @param theModel
     * @param theFieldIndexNumber
     * @param theFieldValue
     * @param isPasswordTypeField
     * @param aFieldsVisibility
     */
    private void setValuesToModel(Category theModel, int theFieldIndexNumber, String theFieldValue, boolean isPasswordTypeField, boolean[] aFieldsVisibility) {
        switch (theFieldIndexNumber) {
            case 1:
                theModel.setField1(theFieldValue);
                aFieldsVisibility[0] = isPasswordTypeField;
                break;
            case 2:
                theModel.setField2(theFieldValue);
                aFieldsVisibility[1] = isPasswordTypeField;
                break;
            case 3:
                theModel.setField3(theFieldValue);
                aFieldsVisibility[2] = isPasswordTypeField;
                break;
            case 4:
                theModel.setField4(theFieldValue);
                aFieldsVisibility[3] = isPasswordTypeField;
                break;
            case 5:
                theModel.setField5(theFieldValue);
                aFieldsVisibility[4] = isPasswordTypeField;
                break;
            case 6:
                theModel.setField6(theFieldValue);
                aFieldsVisibility[5] = isPasswordTypeField;
                break;
            case 7:
                theModel.setField7(theFieldValue);
                aFieldsVisibility[6] = isPasswordTypeField;
                break;
            case 8:
                theModel.setField8(theFieldValue);
                aFieldsVisibility[7] = isPasswordTypeField;
                break;
            case 9:
                theModel.setField9(theFieldValue);
                aFieldsVisibility[8] = isPasswordTypeField;
                break;
            case 10:
                theModel.setField10(theFieldValue);
                aFieldsVisibility[9] = isPasswordTypeField;
                break;
            case 11:
                theModel.setField11(theFieldValue);
                aFieldsVisibility[10] = isPasswordTypeField;
                break;
            case 12:
                theModel.setField12(theFieldValue);
                aFieldsVisibility[11] = isPasswordTypeField;
                break;
            case 13:
                theModel.setField13(theFieldValue);
                aFieldsVisibility[12] = isPasswordTypeField;
                break;
            case 14:
                theModel.setField14(theFieldValue);
                aFieldsVisibility[13] = isPasswordTypeField;
                break;
            case 15:
                theModel.setField15(theFieldValue);
                aFieldsVisibility[14] = isPasswordTypeField;
                break;
        }
    }

    /**
     * Creates the required fields based on the model data.
     *
     * @param theCategory
     */
    private void createRequiredFields(Category theCategory) {
        if (!StringUtils.isNullOrEmpty(theCategory.getField1())) {
            addField(theCategory.getField1());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField2())) {
            addField(theCategory.getField2());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField3())) {
            addField(theCategory.getField3());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField4())) {
            addField(theCategory.getField4());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField5())) {
            addField(theCategory.getField5());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField6())) {
            addField(theCategory.getField6());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField7())) {
            addField(theCategory.getField7());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField8())) {
            addField(theCategory.getField8());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField9())) {
            addField(theCategory.getField9());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField10())) {
            addField(theCategory.getField10());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField11())) {
            addField(theCategory.getField11());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField12())) {
            addField(theCategory.getField12());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField13())) {
            addField(theCategory.getField13());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField14())) {
            addField(theCategory.getField14());
        }
        if (!StringUtils.isNullOrEmpty(theCategory.getField15())) {
            addField(theCategory.getField15());
        }
    }
}
