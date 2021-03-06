package com.blmsr.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.models.User;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.EncryptionUtil;
import com.blmsr.manager.util.StringUtils;

import java.util.List;

/**
 * This activity is responsible to change the password.
 */
public class ChangePasswordActivity extends Activity implements CategoryConstants {
    private static final String LOG_NAME = "ChangePasswordActivity";
    private boolean isCurrentPasswordAvailable = true;
    private EditText itsCurrentPasswordTextField;
    private EditText itsNewPasswordTextField;
    private EditText itsConfirmPasswordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent anIntent = getIntent();
        itsCurrentPasswordTextField = (EditText) findViewById(R.id.currentPasswordField);
        itsNewPasswordTextField = (EditText) findViewById(R.id.newPasswordField);
        itsConfirmPasswordTextField = (EditText) findViewById(R.id.confirmPasswordField);

        // Check the password available or not.
        UserService anUserService = DatabaseService.getInstance(this).getUserService();
        if (anUserService.getAllPasswords().isEmpty()) {
            isCurrentPasswordAvailable = false;
            itsCurrentPasswordTextField.setVisibility(View.INVISIBLE);
        }

        // get the show/hide password Checkbox
        CheckBox itsCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        itsCbShowPwd.setOnCheckedChangeListener(itsOnCheckedChangedLisener);
    }

    // Create the button listener for the show Password.
    private CompoundButton.OnCheckedChangeListener itsOnCheckedChangedLisener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            try {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    itsCurrentPasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    itsNewPasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    itsConfirmPasswordTextField.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    itsCurrentPasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    itsNewPasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    itsConfirmPasswordTextField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            } catch (Exception anException) {
                Log.e("ChangePassword", "Error while changing the password", anException);
            }
        }
    };

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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void saveItem(View theView) {
        saveDetails();
        Log.d(LOG_NAME, "details saved");
    }

    private void saveDetails() {

        try {
            String aCurrentPasswordValue = itsCurrentPasswordTextField.getText().toString();
            if (isCurrentPasswordAvailable && StringUtils.isNullOrEmpty(aCurrentPasswordValue)) {
                itsCurrentPasswordTextField.setError("Current Password is required");
                return;
            }
            String aNewPassword = itsNewPasswordTextField.getText().toString();
            if (StringUtils.isNullOrEmpty(aNewPassword)) {
                itsNewPasswordTextField.setError("Password is required");
                return;
            }
            String aConfirmPassword = itsConfirmPasswordTextField.getText().toString();
            if (StringUtils.isNullOrEmpty(aConfirmPassword)) {
                itsConfirmPasswordTextField.setError("Confirm the password");
                return;
            }

            if (!StringUtils.areEqual(aNewPassword, aConfirmPassword)) {
                itsConfirmPasswordTextField.setError("Please enter correct confirm password");
                return;
            }

            UserService anUserService = DatabaseService.getInstance(this).getUserService();
            List<User> aList = anUserService.getAllPasswords();

            String aHashedPassword = EncryptionUtil.generateStrongPasswordHash(aConfirmPassword);
            boolean isUserUnAvailable = aList.isEmpty();
            if (isUserUnAvailable && !isCurrentPasswordAvailable) {
                User anUser = new User();
                anUser.setPassword(aHashedPassword);
                anUserService.save(anUser);
            } else {
                if (isUserUnAvailable) {
                    itsCurrentPasswordTextField.setError("Enter correct password");
                    return;
                }
                User anUser = aList.get(0);
                if (anUser == null || !EncryptionUtil.validatePassword(aCurrentPasswordValue, anUser.getPassword())) {
                    itsCurrentPasswordTextField.setError("Enter correct password");
                    return;
                }

                // update the password in the database.
                anUser.setPassword(aHashedPassword);
                anUserService.update(anUser);
            }
            Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_LONG).show();

        } catch (Exception aNException) {
            Log.e(LOG_NAME, "failed to save details" + aNException);
        }

        Class aClassName = CategoryHomeTabbedActivity.class;
        if (!isCurrentPasswordAvailable) {
            aClassName = MainActivity.class;
        }

        Intent anIntent = new Intent(this, aClassName);
        startActivity(anIntent);
    }
}
