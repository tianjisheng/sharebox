package com.tian.sharebox.func.funcSetting;

import android.view.View;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.BaseActivity;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class SettingActivity extends BaseActivity
{
    @Override
    protected void setContent()
    {
         
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_setting_toolbar_title_text;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_setting_toolbar;
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_setting;
    }

    @Override
    protected void initSelfLayout()
    {
        
    }
    
    public void onClick(View view)
    {
        MyApplication.mApplication.clearToken();
    }
}
