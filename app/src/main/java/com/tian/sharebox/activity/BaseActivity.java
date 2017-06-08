package com.tian.sharebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tian.sharebox.R;
/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */
public abstract class BaseActivity extends Activity
{
    protected TextView mTextView;
    private Toolbar mToolbar = null;
    private boolean isShown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mTextView = (TextView) findViewById(R.id.activity_user_base_content_text_view);
        ((TextView) findViewById(getTitleId())).setText(this.getTitle());
        mToolbar = (Toolbar) findViewById(getToolbarId());
        mToolbar.setNavigationIcon(R.drawable.up_arrow_style);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleBack();
            }
        });
        setContent();
        initSelfLayout();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        isShown = true;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        isShown = false;
    }

    private void handleBack()
    {
        super.onBackPressed();
    }
    protected  int getContentViewId()
    {
        return R.layout.activity_user_base;
    }
    protected int getToolbarId()
    {
        return R.id.activity_user_base_toolbar;
    }
    protected int getTitleId()
    {
        return R.id.activity_user_base_toolbar_title_text;
    }
    protected void initSelfLayout()
    {
        
    }
    protected abstract void setContent();
    
    protected boolean isShown()
    {
        return isShown;
    }
     
}
