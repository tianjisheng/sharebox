package com.tian.testleakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/13
 * @describe
 */

public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
