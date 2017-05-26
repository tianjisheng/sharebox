package com.tian.sharebox.config;

import android.support.annotation.NonNull;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class UserItemConfig
{
    public String itemId = "";//唯一标示item
    public int groupIndex = 0;//在第几个分组,默认在第一组内，如果出现前面只有1个组，此处配了3个组，会自动调整到第二个组
    public int itemIndex = 0;//在组内第几个，如果冲突，并列随机排列下来
    public String itemTitle = "";//标题
    public String summaryTitle = "";//右侧显示的文字
    public String toWhere = "";//跳转到哪个activity
    public boolean hasRed = false;//是否有红点
    public UserItemConfig()
    {
        
    }
    public UserItemConfig itemId(@NonNull String id)
    {
        this.itemId = id;
        return this;
    }
    
    public UserItemConfig groupIndex(int groupIndex)
    {
        this.groupIndex = groupIndex;
        return this;
    }
    public UserItemConfig itemIndex(int itemIndex)
    {
        this.itemIndex = itemIndex;
        return this;
    }
    public UserItemConfig itemTitle(@NonNull String title)
    {
        this.itemTitle = title;
        return this;
    }
    
    public UserItemConfig summaryTitle(@NonNull String summaryTitle)
    {
        this.summaryTitle = summaryTitle;
        return this;
    }
    
    public UserItemConfig toWhere(@NonNull String where)
    {
        this.toWhere = where;
        return this;
    }
    
    public UserItemConfig hasRed(boolean hasRed)
    {
        this.hasRed = hasRed;
        return this;
    }
}
