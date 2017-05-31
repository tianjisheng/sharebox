package com.tian.sharebox.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/27
 * @describe
 */

public class PhoneNumberUtil
{
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        boolean isValid = false;


        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;


        Pattern pattern = Pattern.compile(expression);


        Matcher matcher = pattern.matcher(inputStr);


        if (matcher.matches())
        {
            isValid = true;
        }


        return isValid;
    }
}
