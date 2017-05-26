package com.tian.sharebox.config;

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
            .toWhere("activity/UserBorrowActivity");

    public static UserItemConfig myMessageItem = new UserItemConfig()
            .groupIndex(0)
            .itemIndex(1)
            .itemId("message")
            .itemTitle("消息")
            .summaryTitle("有促销")
            .hasRed(true)
            .toWhere("activity/UserMessageActivity");

    public static UserItemConfig settingItem = new UserItemConfig()
            .groupIndex(1)
            .itemIndex(0)
            .itemId("setting")
            .itemTitle("设置")
            .summaryTitle("可以看看")
            .toWhere("activity/UserSettingActivity");

}
