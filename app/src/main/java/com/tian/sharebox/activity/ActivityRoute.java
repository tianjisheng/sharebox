package com.tian.sharebox.activity;

import android.app.Activity;
import android.content.Intent;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.config.UserItemConfig;

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
        activityMap.put("activity/UserSettingActivity",UserSettingActivity.class);
        activityMap.put("activity/UserMessageActivity",UserMessageActivity.class);
        activityMap.put("activity/UserBorrowActivity",UserBorrowActivity.class);
    }
    public static void dispatcherActivity(UserItemConfig activity, String paramJson)
    {
        Intent intent = new Intent(MyApplication.ApplicationContext,activityMap.get(activity.toWhere));
        intent.putExtra("activity_param",paramJson);
        MyApplication.ApplicationContext.startActivity(intent);
    }
}
