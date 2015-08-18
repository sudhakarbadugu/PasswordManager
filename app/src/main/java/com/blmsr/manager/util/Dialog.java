package com.blmsr.manager.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by LakshmiMadhav on 8/13/2015.
 */
public class Dialog {
    public static final String VALIDATION_MESSAGE_TITLE = "Validation";
    public static final String CONFIRM_MESSAGE_TITLE = "Save Entry?";

    public static AlertDialog showAlertDialog(Context theContext, String theTitle) {
        theTitle = StringUtils.isNullOrEmpty(theTitle) ? CONFIRM_MESSAGE_TITLE : theTitle;
        AlertDialog alertDialog = new AlertDialog.Builder(theContext)
                .setTitle(theTitle)
//                .setMessage(theMessage)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, null)
                .create();
        alertDialog.show();
        return alertDialog;
    }

    public static AlertDialog showValidationMessageDialog(Context theContext, String theMessage) {
        return showValidationMessageDialog(theContext, VALIDATION_MESSAGE_TITLE, theMessage);
    }

    public static AlertDialog showValidationMessageDialog(Context theContext, String theTitle, String theMessage) {
        AlertDialog alertDialog = new AlertDialog.Builder(theContext)
                .setTitle(theTitle)
                .setMessage(theMessage)
                .setPositiveButton(android.R.string.yes, null)
                .create();
        alertDialog.show();
        return alertDialog;
    }
}
