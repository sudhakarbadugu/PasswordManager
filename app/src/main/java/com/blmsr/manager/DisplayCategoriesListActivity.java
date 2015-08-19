package com.blmsr.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.blmsr.manager.R;

public class DisplayCategoriesListActivity extends CategoriesListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // disable only + button for the view.
        findViewById(R.id.fab).setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_categories_list, menu);
        return true;
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent anIntent = new Intent(this, CategoryEntriesListActivity.class);
        anIntent.putExtra(CategoryEditorActivity.EDIT_CATEGORY, CategoryEditorActivity.EDIT_CATEGORY);
        anIntent.putExtra(CategoryEditorActivity.CATEGORY, itsCategoriesList.get(position));
        startActivity(anIntent);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

}
