package com.tian.sharebox.network;

import com.tian.sharebox.data.BoxData;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/19
 * @describe 全套接口
 */

public class ApiCollection
{
    public interface ILogin
    {
        
    }
    
    public interface IGroupByBox
    {
        /**
         * 
         * @param x 经度
         * @param y 纬度
         * @return 周边盒子列表?有可能是id列表
         */
        ArrayList<BoxData> getGroupByBoxes(long x, long y, int range);
            
    }
    
    public interface IDetailInfo
    {
        BoxData getDetailInfo(String id);
    }
}
