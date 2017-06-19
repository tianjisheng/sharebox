package com.tian.sharebox.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/5
 * @describe
 */

public class AvailableGoodsData implements Parcelable
{
    private String categoryId = "";
    /**
     * 名称
     */
    private String title = "";
    /**
     * 可用数目
     */

    private String availableTotal = "0";

    public AvailableGoodsData()
    {

    }

    protected AvailableGoodsData(Parcel in)
    {
        title = in.readString();
        availableTotal = in.readString();
    }

    public static final Creator<AvailableGoodsData> CREATOR = new Creator<AvailableGoodsData>()
    {
        @Override
        public AvailableGoodsData createFromParcel(Parcel in)
        {
            return new AvailableGoodsData(in);
        }

        @Override
        public AvailableGoodsData[] newArray(int size)
        {
            return new AvailableGoodsData[size];
        }
    };

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAvailableTotal()
    {
        return availableTotal;
    }

    public void setAvailableTotal(String availableTotal)
    {
        this.availableTotal = availableTotal;
    }

    public void setAvailableTotal(int availableTotal)
    {
        this.availableTotal = "" + availableTotal;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(title);
        dest.writeString(availableTotal);
    }
}
