package com.tian.sharebox.funcMessage;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.BaseActivity;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/27
 * @describe 消息详细界面
 */

public class MessageDetailActivity extends BaseActivity
{
    private WebView mWebView;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected void initSelfLayout()
    {
        mWebView = (WebView) findViewById(R.id.webview_message_container);
        mWebView.getSettings().setJavaScriptEnabled(true);//启用js 
        mWebView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        mWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                ActivityRoute.dispatcherActivity("activity/UserCenterActivity","");
                return true;
            }
        });
        mWebView.loadUrl("http://mp.weixin.qq.com/s/PEkswR_U2TBPwbqOF_cpUQ?uid=49851941460296861696652717&from=singlemessage\n");
    }


    @Override
    protected int getToolbarId()
    {
        return R.id.activity_message_detail_toolbar;
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_message_detail;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_message_detail_toolbar_title_text;
    }
}
