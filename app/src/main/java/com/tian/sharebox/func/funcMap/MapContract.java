package com.tian.sharebox.func.funcMap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/1
 * @describe
 */

public interface MapContract
{
    interface View extends BaseView
    {
        BaiduMap getMap();
        MapView getMapView();
    }
    
    interface Presenter extends BasePresenter
    {
        boolean onCreate();
        
        boolean onResume();
        
        boolean refreshNearbyContainer(LatLng latLng);
        
        boolean locationCurrent();
    }
}
