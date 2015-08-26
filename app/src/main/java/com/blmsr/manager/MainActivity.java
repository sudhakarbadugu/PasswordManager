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

import com.blmsr.manager.dao.UserService;
import com.blmsr.manager.models.User;
import com.blmsr.manager.service.DatabaseService;
import com.blmsr.manager.util.EncryptionUtil;
import com.blmsr.manager.util.StringUtils;

import java.util.List;

public class MainActivity extends Activity implements CategoryConstants {
    private EditText itsPasswordField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // get the password EditText
        itsPasswordField = (EditText) findViewById(R.id.fld_pwd);
        // get the show/hide password Checkbox
        CheckBox itsCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        // add onCheckedListener on checkbox
        itsCbShowPwd.setOnCheckedChangeListener(itsOnCheckedChangeListener);
	}

    private CompoundButton.OnCheckedChangeListener itsOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        // when user clicks on this checkbox, this is the handler.
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
    };

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
			showChangePasswordDialog(null);
			return true;
        }
		return super.onOptionsItemSelected(item);
	}


	public void showChangePasswordDialog(View tView) {
		Intent anIntent = new Intent(this, ChangePasswordActivity.class);
        startActivity(anIntent);
    }
	public void performLogin(View v) {

		EditText aPwdField = (EditText) findViewById(R.id.fld_pwd);
		String apwd = aPwdField.getText().toString();

		if (StringUtils.isNullOrEmpty(apwd)) {
            aPwdField.setError("Enter Password");
            return;
        }

		UserService anUserService = DatabaseService.getInstance(this).getUserService();
        List<User> aList = anUserService.getAllPasswords();

        if (aList.isEmpty() || aList.get(0) == null || !EncryptionUtil.validatePassword(apwd.trim(), aList.get(0).getPassword()))
        {
            aPwdField.setError("Enter the correct password");
            return;
		}

		Intent anIntent = new Intent(MainActivity.this, CategoryHomeTabbedActivity.class);
		startActivity(anIntent);
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

}
