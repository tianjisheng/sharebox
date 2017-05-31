package com.tian.sharebox.activity;

import android.app.Activity;
import android.content.Intent;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.funcLogin.LoginActivity;
import com.tian.sharebox.funcMessage.MessageDetailActivity;
import com.tian.sharebox.funcUser.UserCenterActivity;

import java.util.HashMap;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class ActivityRoute
{
    private static HashMap<String,Class<? extends Activity>> activityMap = new HashMap<>();
    static
    {
        activityMap.put("activity/UserCenterActivity",UserCenterActivity.class);
        activityMap.put("activity/UserSettingActivity",SettingActivity.class);
        activityMap.put("activity/UserMessageActivity",LoginActivity.class);
        activityMap.put("activity/UserBorrowActivity",BorrowActivity.class);
        activityMap.put("activity/MessageDetailActivity",MessageDetailActivity.class);
    }
    public static void dispatcherActivity(String activity, String paramJson)
    {
        Intent intent = new Intent(MyApplication.ApplicationContext,activityMap.get(activity));
        intent.putExtra("activity_param",paramJson);
        MyApplication.ApplicationContext.startActivity(intent);
    }
}
