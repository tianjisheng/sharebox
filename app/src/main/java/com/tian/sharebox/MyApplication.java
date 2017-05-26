package com.tian.sharebox;

import android.app.Application;
import android.content.Context;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class MyApplication extends Application
{
    public static Context ApplicationContext = null;

    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationContext = this;
    }
}
