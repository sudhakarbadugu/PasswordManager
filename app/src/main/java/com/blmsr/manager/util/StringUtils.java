package com.blmsr.manager.util;

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



    public static boolean areBothSame(String theValue1, String theValue2)
    {
        if(isNotNull(theValue1) && theValue1.equals(theValue2))
        {
            return true;
        }
        else if (isNotNull(theValue2) && theValue2.equals(theValue1))
        {
            return true;
        }

        return false;
    }
}
