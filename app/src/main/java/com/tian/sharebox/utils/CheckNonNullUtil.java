package com.tian.sharebox.utils;

import android.support.annotation.Nullable;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public class CheckNonNullUtil
{
    public static <T> T checkNotNull(T reference)
    {
        if (reference == null)
        {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage)
    {
        if (reference == null)
        {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
