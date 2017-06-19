package com.tian.sharebox.data;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public class CategoryData
{
    private String categoryId = "";
    private String categoryName = "";
    private String categoryCharging = "";
    private String iconPath = "";

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryCharging()
    {
        return categoryCharging;
    }

    public void setCategoryCharging(String categoryCharging)
    {
        this.categoryCharging = categoryCharging;
    }

    public String getIconPath()
    {
        return iconPath;
    }

    public void setIconPath(String iconPath)
    {
        this.iconPath = iconPath;
    }
}
