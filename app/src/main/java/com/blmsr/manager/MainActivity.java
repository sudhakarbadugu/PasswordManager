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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.models.User;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.StringUtils;

public class MainActivity extends Activity implements CategoryConstants {
	Button itsLoginButton;
    EditText itsPasswordField;
    CheckBox itsCbShowPwd;
    private boolean isPasswordAvailable = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // get the password EditText
        itsPasswordField = (EditText) findViewById(R.id.fld_pwd);
        // get the show/hide password Checkbox
        itsCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        itsCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// checkbox status is changed from uncheck to checked.
				if (!isChecked) {
					// show password
					itsPasswordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
				} else {
					// hide password
					itsPasswordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
			}
		});

        // Check the password available or not.
        UserService anUserService = DatabaseService.getInstance(this).getUserService();
        if (anUserService.getAllPasswords().isEmpty())
        {
            isPasswordAvailable = false;
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_change_password) {
            showChangePasswordDialog();
            return true;
        }
		return super.onOptionsItemSelected(item);
	}


    public void showChangePasswordDialog() {
        Intent anIntent = new Intent(this, ChangePasswordActivity.class);
        if (!isPasswordAvailable)
        {
            // No passwords has set for the user so invisible the current password field.
			anIntent.putExtra(CURRENT_PASSWORD_VISIBILITY, CURRENT_PASSWORD_VISIBILITY);
		}
        startActivity(anIntent);
    }
	public void performLogin(View v) {

		EditText aPwdField = (EditText) findViewById(R.id.fld_pwd);
		String apwd = aPwdField.getText().toString();
		Log.d("MainActivity", "PWD :" + apwd);

		if (StringUtils.isNullOrEmpty(apwd)) {
            aPwdField.setError("Enter Password");
            return;
        }

		UserService anUserService = DatabaseService.getInstance(this).getUserService();
		User anUser = anUserService.getPassword(apwd.trim());
        if (anUser == null)
        {
			aPwdField.setError("Set the correct password");
			Toast.makeText(getApplicationContext(), "Set the password first if not configured", Toast.LENGTH_LONG).show();
			return;
		}

		Intent anIntent = new Intent(MainActivity.this, CategoryHomeActivity.class);
		startActivity(anIntent);
}
}
