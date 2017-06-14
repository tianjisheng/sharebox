package com.tian.sharebox.func.funcSetting;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.utils.ToastViewUtil;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class SettingActivity extends BaseActivity implements SettingContract.View
{
    private SettingContract.Presenter presenter = null;

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
        presenter = new SettingPresenter(this);
        PackageInfo packageInfo = null;
        try
        {
            packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);

            ((TextView) findViewById(R.id.activity_setting_version)).setText("versionCode=" + packageInfo.versionCode +
                    "\n versionName=" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void onClick(View view)
    {
//        presenter.logout(MyApplication.mApplication.getToken());
        MyApplication.mApplication.clearToken();
        ToastViewUtil.showToast("退出成功");

    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    @Override
    public void showLoading()
    {

    }

    @Override
    public void hideLoading()
    {

    }
}
