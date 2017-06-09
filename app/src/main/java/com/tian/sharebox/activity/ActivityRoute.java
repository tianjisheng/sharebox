package com.tian.sharebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.func.funcBorrow.BorrowActivity;
import com.tian.sharebox.func.funcDetailBox.BoxDetailActivity;
import com.tian.sharebox.func.funcDetailOrder.DetailOrderActivity;
import com.tian.sharebox.func.funcLogin.LoginActivity;
import com.tian.sharebox.func.funcMessage.MessageActivity;
import com.tian.sharebox.func.funcMessage.MessageDetailActivity;
import com.tian.sharebox.func.funcOrder.OrderActivity;
import com.tian.sharebox.func.funcScan.InputCodeActivity;
import com.tian.sharebox.func.funcScan.ScanActivity;
import com.tian.sharebox.func.funcSetting.SettingActivity;
import com.tian.sharebox.func.funcUser.UserCenterActivity;

import java.util.HashMap;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class ActivityRoute
{
    private static HashMap<String, Class<? extends Activity>> activityMap = new HashMap<>();

    public static final String UserCenterActivity = "activity/UserCenterActivity";
    public static final String SettingActivity = "activity/UserSettingActivity";
    public static final String MessageActivity = "activity/MessageActivity";
    public static final String LoginActivity = "activity/LoginActivity";
    public static final String OrderActivity = "activity/OrderActivity";
    public static final String DetailOrderActivity = "activity/DetailOrderActivity";
    public static final String BorrowActivity = "activity/UserBorrowActivity";
    public static final String MessageDetailActivity = "activity/MessageDetailActivity";
    public static final String ScanActivity = "activity/ScanActivity";
    public static final String InputCodeActivity = "activity/InputCodeActivity";
    public static final String BoxDetailActivity = "activity/BoxDetailActivity";

    static
    {
        activityMap.put(UserCenterActivity, UserCenterActivity.class);
        activityMap.put(SettingActivity, SettingActivity.class);
        activityMap.put(MessageActivity, MessageActivity.class);
        activityMap.put(LoginActivity, LoginActivity.class);
        activityMap.put(OrderActivity, OrderActivity.class);
        activityMap.put(DetailOrderActivity, DetailOrderActivity.class);
        activityMap.put(BorrowActivity, BorrowActivity.class);
        activityMap.put(MessageDetailActivity, MessageDetailActivity.class);
        activityMap.put(ScanActivity, ScanActivity.class);
        activityMap.put(InputCodeActivity, InputCodeActivity.class);
        activityMap.put(BoxDetailActivity, BoxDetailActivity.class);
    }

    public static final String ParamJsonKey = "activity_param_string";

    public static void dispatcherActivity(String activity, String paramJson)
    {
        Intent intent = new Intent(MyApplication.mApplication, activityMap.get(activity));
        intent.putExtra(ParamJsonKey, paramJson);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.mApplication.startActivity(intent);
    }

    public static final String ParamBundleKey = "activity_param_bundle";

    public static void dispatcherActivity(String activity, Bundle paramBundle)
    {
        Intent intent = new Intent(MyApplication.mApplication, activityMap.get(activity));
        intent.putExtra(ParamBundleKey, paramBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.mApplication.startActivity(intent);
    }
}
