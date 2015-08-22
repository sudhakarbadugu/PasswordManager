package com.blmsr.manager.util;

import com.blmsr.manager.CategoryConstants;

/**
 * Created by LakshmiMadhav on 7/30/2015.
 */
public class StringUtils {

    /**
     * This method returns true if given string is null or empty.
     * @param theString
     * @return
     */
    public static boolean isNullOrEmpty(String theString)
    {
        return (theString == null || theString.trim().isEmpty());
    }

    /**
     * This method returns true if given string is not null and empty.
     * @param theString
     * @return
     */
    public static boolean isNotNull(String theString)
    {
        return  !isNullOrEmpty(theString);
    }


    public static boolean areNotEqual(String theValue1, String theValue2) {
        return !areEqual(theValue1, theValue2);
    }

    public static boolean areEqual(String theValue1, String theValue2)
    {
        if(isNotNull(theValue1) && theValue1.equals(theValue2))
        {
            return true;
        }
        else if (isNotNull(theValue2) && theValue2.equals(theValue1))
        {
            return true;
        } else if (isNullOrEmpty(theValue1) && isNullOrEmpty(theValue2)) {
            return true;
        }

        return false;
    }

    /**
     * Split the given name based on the separator. If separator is unavailable, it returns given as element of String[].
     *
     * @param theName
     * @return
     */
    public static String[] split(String theName) {
        return split(theName, CategoryConstants.SEPARATOR);
    }

    /**
     * Split the given name based on the separator. If separator is unavailable, it returns given as element of String[].
     *
     * @param theName
     * @param theSeparator
     * @return
     */
    public static String[] split(String theName, String theSeparator) {
        if (isNullOrEmpty(theSeparator)) {
            theSeparator = CategoryConstants.SEPARATOR;
        }
        String[] aFieldAndVisibilityArray = new String[1];
        if (StringUtils.isNotNull(theName) && theName.contains(theSeparator)) {
            aFieldAndVisibilityArray = theName.split(theSeparator);
        } else {
            aFieldAndVisibilityArray[0] = theName;
        }

        return aFieldAndVisibilityArray;
    }
}
