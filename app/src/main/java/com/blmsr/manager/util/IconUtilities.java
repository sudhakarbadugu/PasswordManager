package com.blmsr.manager.util;

import com.blmsr.manager.R;

/**
 * Created by LakshmiMadhav on 8/23/2015.
 * This is an utilities class. It has utility methods to load the icons and choose icons.
 */
public class IconUtilities {

    public static int getImageResourceID(String theIconName) {
        int anIconID = 0;

        if ("ic_email_black".equals(theIconName)) {
            anIconID = R.drawable.ic_email_black_24dp;
        } else if ("ic_category_computer_logins".equals(theIconName)) {
            anIconID = R.drawable.ic_category_computer_logins;
        } else if ("ic_bw_bank".equals(theIconName)) {
            anIconID = R.drawable.ic_bw_bank;
        } else if ("ic_web_icon".equals(theIconName)) {
            anIconID = R.drawable.ic_language_black_24dp;
        } else if ("ic_mobile_icon".equals(theIconName)) {
            anIconID = R.drawable.ic_perm_device_information_black_24dp;
        } else if ("card_icon".equals(theIconName)) {
            anIconID = R.drawable.ic_credit_card_black_24dp;
        } else {
            anIconID = R.drawable.ic_home_black_24dp;
        }

        return anIconID;
    }
}
