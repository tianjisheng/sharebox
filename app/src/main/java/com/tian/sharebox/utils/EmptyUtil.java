package com.tian.sharebox.utils;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/4/11
 * @describe
 */

public class EmptyUtil
{
    public static boolean isNonNull(String str)
    {
        if (str!=null && str.length()>0)
        {
            return true;
        }
        return false;
    } 
    
    public static boolean isNull(String str)
    {
        if (str == null||str.length() == 0)
        {
            return true;
        }
        return false;
    }
}
