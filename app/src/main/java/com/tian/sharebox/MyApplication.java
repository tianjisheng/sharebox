package com.tian.sharebox;

import android.app.Application;
import android.content.Context;

import com.tian.sharebox.utils.ShareDataUtil;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class MyApplication extends Application
{
    public static MyApplication mApplication = null;
    private String token = "";
    private ShareDataUtil shareUtil = null;
    private final String TOKEN_KEY = "token";

    @Override
    public void onCreate()
    {
        super.onCreate();
        mApplication = this;
        shareUtil = new ShareDataUtil(getApplicationContext());
        getToken();
    }

    public String getToken()
    {
        if (token == null || token.length() == 0)
        {
            token = shareUtil.getStringData(TOKEN_KEY);
        }
        return token;
    }

    public boolean hasToken()
    {
        if (token != null && token.length() > 0)
        {
            return true;
        }
        return false;
    }

    public void setToken(String token)
    {
        if (token != null && token.length() > 0)
        {
            this.token = token;
            shareUtil.setStringData(TOKEN_KEY, token);
        }
    }
}
