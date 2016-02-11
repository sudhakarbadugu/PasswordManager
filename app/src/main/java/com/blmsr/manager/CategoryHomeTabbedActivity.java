package com.blmsr.manager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.blmsr.manager.adapter.TabsPagerAdapter;
import com.blmsr.manager.dao.CategoryEntryService;
import com.blmsr.manager.dao.CategoryService;
import com.blmsr.manager.models.Category;
import com.blmsr.manager.models.CategoryEntry;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.Dialog;
import com.blmsr.manager.util.ExportUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * This TabbedActivity is responsible to show the Configure categories and its categories entries.
 */
// TODO should re write this class using the latest tab activity.
public class CategoryHomeTabbedActivity extends FragmentActivity implements ActionBar.TabListener, CategoryConstants {
    private ViewPager viewPager;
    private ActionBar actionBar;

    /**
     * Only supported format.
     */
    private static final String FILE_EXTENSION_NAME = ".csv";

    // Tab titles
    private String[] tabs = {CATEGORIES, CATEGORY_EDITOR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_home_fragment);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        TabsPagerAdapter mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_home, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_about:
                showAbout();
                return true;
            case R.id.action_search:
                showSearch();
                return true;
            case R.id.action_lock:
                lockApp();
                return true;
            case R.id.action_change_password:
                showChangePasswordDialog();
                return true;
            case R.id.action_settings:
                showSearch();
                return true;
            case R.id.action_export_csv:
                exportDataToCSV();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSearch() {
        Intent anIntent = new Intent(this, SearchActivity.class);
        startActivity(anIntent);
    }

    private void exportDataToCSV() {
        CategoryService aCategoryService = DatabaseService.getInstance(this).getCategoryService();
        CategoryEntryService aCategoryEntryService = DatabaseService.getInstance(this).getCategoryEntryService();
        List<Category> aCategories = aCategoryService.getCategiries();
        boolean isExported = true;

        String filename = "csv";
        File aFile = null;
        FileOutputStream outputStream;
        String fileName = "MyFile";
        String content = "hello world";

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            aFile = getDir("csv", Context.MODE_PRIVATE);
            for (Category aCategory : aCategories) {
                filename = aCategory.getCategoryName() + FILE_EXTENSION_NAME;
                List<CategoryEntry> aCategoryEntries = aCategoryEntryService.getCategoryByID(aCategory.getCategoryId());
                aFile = new File(aFile, filename);
                if (!aFile.exists() && aFile.mkdirs()) {
                    Log.e("HomePage", "failed create directories");
                }
//                ExportUtil.exportToCSV(aFile, ExportUtil.toStringArray(aCategoryEntries));
            }
        } catch (Exception anException) {
            Dialog.showValidationMessageDialog(this, Dialog.WARNING_MESSAGE_TITLE, "Failed to export the files");
            anException.printStackTrace();
            Log.e("HomePage", "An exception occurred ", anException);
            isExported = false;
        }

        if (isExported) {
            Dialog.showValidationMessageDialog(this, Dialog.INFO_MESSAGE_TITLE, "Files exported successfully." +
                    "Please find files in the following path: " + aFile.getPath());
        }
    }

    private void lockApp() {
        Intent anIntent = new Intent(this, MainActivity.class);
        startActivity(anIntent);

    }
    public void showChangePasswordDialog() {
        Intent anIntent = new Intent(this, ChangePasswordActivity.class);
        startActivity(anIntent);
    }

    protected void showAbout() {
        // Inflate the about message contents
        View messageView = getLayoutInflater().inflate(R.layout.about, null, false);
        TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
        textView.setText("My Password Manager is the best app for helping you remember " +
                "your personal data" +
                "This helps a user store and organize passwords. " +
                "Password managers usually store passwords encrypted, requiring the user to create a master password;" +
                "a single, ideally very strong password which grants the user access to their entire password database. " +
                "Password manager is created by Sudhakar.B \n Version: 1.77 \n" +
                "This was to keep your passwords in an encrypted vault");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setView(messageView);
        builder.create();
        builder.show();
    }


    public void addCategory(View theView) {
        Intent anIntent = new Intent(this, CategoryEditorActivity.class);
        startActivity(anIntent);
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}