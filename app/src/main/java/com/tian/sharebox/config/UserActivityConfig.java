package com.tian.sharebox.config;

import com.tian.sharebox.activity.ActivityRoute;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class UserActivityConfig
{
    public static UserItemConfig myBorrowItem = new UserItemConfig()
            .groupIndex(0)
            .itemIndex(0)
            .itemId("my_borrow")
            .itemTitle("我的借阅")
            .toWhere(ActivityRoute.OrderActivity);

    public static UserItemConfig myMessageItem = new UserItemConfig()
            .groupIndex(0)
            .itemIndex(1)
            .itemId("message")
            .itemTitle("消息")
            .summaryTitle("暂无消息")
            .toWhere(ActivityRoute.MessageActivity);

    public static UserItemConfig settingItem = new UserItemConfig()
            .groupIndex(1)
            .itemIndex(0)
            .itemId("setting")
            .itemTitle("设置")
            .summaryTitle("")
            .toWhere(ActivityRoute.SettingActivity);

}
