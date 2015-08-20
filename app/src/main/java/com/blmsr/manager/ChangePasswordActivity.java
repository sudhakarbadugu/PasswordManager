package com.blmsr.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.models.User;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.StringUtils;

public class ChangePasswordActivity extends Activity implements CategoryConstants {
    private static final String LOG_NAME = "ChangePasswordActivity";
    private boolean isCurrentPasswordAvailable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent anIntent = getIntent();
        if(CURRENT_PASSWORD_VISIBILITY.equals(anIntent.getStringExtra(CURRENT_PASSWORD_VISIBILITY)))
        {
            isCurrentPasswordAvailable = false;
            findViewById(R.id.currentPasswordField).setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveItem(View theView) {
        saveDetails(theView);
        Log.d(LOG_NAME, "details saved");
    }

    private void saveDetails(View theView) {

        try {
            EditText aPasswordField = (EditText) findViewById(R.id.itemNameField);
            EditText aCurrentPasswordField = (EditText) findViewById(R.id.currentPasswordField);
            EditText aConfirmPasswordField = (EditText) findViewById(R.id.itemPasswordField);
            String anItemNameValue = aPasswordField.getText().toString();
            String aCurrentPasswordValue = aCurrentPasswordField.getText().toString();
            if (isCurrentPasswordAvailable && StringUtils.isNullOrEmpty(aCurrentPasswordValue)) {
                aCurrentPasswordField.setError("Current Password is required");
                return;
            }
            if (StringUtils.isNullOrEmpty(anItemNameValue)) {
                aPasswordField.setError("Password is required");
                return;
            }
            String anItemValue = aConfirmPasswordField.getText().toString();
            if (StringUtils.isNullOrEmpty(anItemValue)) {
                aConfirmPasswordField.setError("Confirm the password");
                return;
            }

            if (!StringUtils.areEqual(anItemNameValue, anItemValue)) {
                aConfirmPasswordField.setError("Please enter correct confirm password");
                return;
            }

            UserService anUserService = DatabaseService.getInstance(this).getUserService();
            User anUser = anUserService.getPassword(aCurrentPasswordValue);

            boolean isUserAvailable = anUser == null ? false: true;
            if(!isCurrentPasswordAvailable && !isUserAvailable)
            {

                anUser = new User();
                anUser.setPassword(anItemNameValue.trim());
                anUserService.save(anUser);
            }
            else
            {
                if(!isUserAvailable)
                {
                    aCurrentPasswordField.setError("enter correct password");
                    return;
                }

                // update the password in the database.
                anUser.setPassword(anItemNameValue.trim());
                anUserService.update(anUser);
            }
            Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_LONG).show();

        } catch (Exception aNException) {
            Log.e(LOG_NAME, "failed to save details" + aNException);
        }

        Class aClassName = CategoryHomeActivity.class;
        if(!isCurrentPasswordAvailable)
        {
            aClassName = MainActivity.class;
        }

        Intent anIntent = new Intent(this, aClassName);
        startActivity(anIntent);
    }
}
