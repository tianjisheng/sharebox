package com.tian.sharebox.utils;

import android.util.Log;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public class LogUtil
{
    public static final String TAG = "ShareBox";

    /**
     * 拼接的时候会自动加“，”
     * @param log
     */
    public static void i(String... log)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String l : log)
        {
            stringBuilder.append(l);
            stringBuilder.append(",");
        }
        i(TAG, stringBuilder.toString());
    }

    public static void i(String tag, String log)
    {
        Log.i(tag, log);
    }

    public static void e(String log)
    {
        e(TAG, log);
    }

    public static void e(String tag, String log)
    {
        Log.e(tag, log);
    }

    public static void w(String log)
    {
        w(TAG, log);
    }

    public static void w(String tag, String log)
    {
        Log.w(tag, log);
    }
}
