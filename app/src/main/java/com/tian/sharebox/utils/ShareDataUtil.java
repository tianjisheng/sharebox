package com.tian.sharebox.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/4/11
 * @describe
 */

public class ShareDataUtil
{
    private String defaultName = "box_share";
    private Context mContext = null;
    private String TAG = "ShareDataUtil";
    public ShareDataUtil(@NonNull Context mContext) throws NullPointerException
    {
        init(mContext,defaultName);
    }
    
    public ShareDataUtil(@NonNull Context mContext, @NonNull String name)throws NullPointerException
    {
        init(mContext,name);
    }
    
    private void init(Context mContext, String name) throws NullPointerException
    {
        if (mContext == null)
        {
            throw new NullPointerException("ShareDataUtil mContext == null");
        }

        if (name == null)
        {
            throw new NullPointerException("ShareDataUtil name == null");
        }
        
        this.mContext = mContext;
        this.defaultName = name;
    }

    /**
     * @param key 非空 or 非""
     * @return 如果不存在返回 0.
     */
    public int getIntData(@NonNull String key)
    {
         if (!EmptyUtil.isNonNull(key))
         {
             return 0;
         }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,0);
    }

    /**
     * @param key key 非空 or 非""
     * @return 默认false
     */
    public boolean getBooleanData(String key)
    {
        if (!EmptyUtil.isNonNull(key))
        {
            return false;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    /**
     * @param key key 非空 or 非""
     * @return 默认 ""
     */
    public String getStringData(String key)
    {
        if (!EmptyUtil.isNonNull(key))
        {
            return "";
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
    
    public void setStringData(String key, String value)
    {
        if (EmptyUtil.isNull(key))
        {
            LogUtil.w(TAG,"setStringData(String key,String value),key==null,value=="+value);
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).commit();
    }
    
    public void setIntData(String key, int value)
    {
        if (EmptyUtil.isNull(key))
        {
            LogUtil.w(TAG,"setIntData(String key,int value),key==null,value=="+value);
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key,value).commit();
    }
    
    public void setBooleanData(String key, boolean value)
    {
        if (EmptyUtil.isNull(key))
        {
            LogUtil.w(TAG,"setBooleanData(String key,boolean value),key==null,value=="+value);
            return;
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(defaultName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,value).commit();
    }
}
