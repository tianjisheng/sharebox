package com.tian.sharebox.utils;

import android.widget.Toast;

import com.tian.sharebox.MyApplication;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public class ToastViewUtil
{
    public static void showToast(String content)
    {
        showToast(content,Toast.LENGTH_SHORT);
    }
    
    public static void showToast(String content,int time)
    {
        Toast.makeText(MyApplication.mApplication,content,time).show();
    }
    
}
