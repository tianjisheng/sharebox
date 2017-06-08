package com.tian.sharebox.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/5
 * @describe
 */

public class CategoryData implements Parcelable
{
    /**
     * 名称
     */
    private String title = "";
    /**
     * 可用数目
     */

    private String availableTotal = "0";
    
    public CategoryData()
    {
        
    }

    protected CategoryData(Parcel in)
    {
        title = in.readString();
        availableTotal = in.readString();
    }

    public static final Creator<CategoryData> CREATOR = new Creator<CategoryData>()
    {
        @Override
        public CategoryData createFromParcel(Parcel in)
        {
            return new CategoryData(in);
        }

        @Override
        public CategoryData[] newArray(int size)
        {
            return new CategoryData[size];
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
        this.availableTotal = ""+availableTotal;
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
