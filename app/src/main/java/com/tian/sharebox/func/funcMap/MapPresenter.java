package com.tian.sharebox.func.funcMap;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Circle;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.data.BoxData;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.CheckNonNullUtil;
import com.tian.sharebox.utils.LogUtil;

import static com.tian.sharebox.network.NetworkConfig.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/1
 * @describe
 */

public class MapPresenter implements MapContract.Presenter, BaiduMap.OnMapStatusChangeListener, BaiduMap.OnMarkerClickListener
{
    private MapContract.View view = null;

    public MapPresenter(@NonNull MapContract.View view)
    {
        this.view = CheckNonNullUtil.checkNotNull(view);
    }

    private static final int accuracyCircleFillColor = 0x75CCE5F5;
    private static final int accuracyCircleStrokeColor = 0xff1897e8;

    @Override
    public boolean onCreate()
    {
        view.getMap().setOnMapStatusChangeListener(this);
        view.getMap().setOnMarkerClickListener(this);
        return true;
    }

    @Override
    public boolean onResume()
    {
        locationCurrent();

        return true;
    }

    @Override
    public void onDestroy()
    {
        view.getMap().removeMarkerClickListener(this);
        view.getMap().clear();
        markers.clear();
        if (mlo!=null && listener !=null)
        {
            mlo.unRegisterLocationListener(listener);
        }
        mlo = null;
        listener = null;
        view = null;
        OkHttpApiImpl.getInstance().cancelAll();
    }


    private Circle in = null;
    private Circle out = null;

    public boolean drawCircle(LatLng latLng)
    {
        if (in != null)
        {
            in.remove();
        }
        if (out != null)
        {
            out.remove();
        }
        OverlayOptions outCircle = new CircleOptions().fillColor(accuracyCircleFillColor)
                .center(latLng).stroke(new Stroke(1, accuracyCircleStrokeColor))
                .radius(100);
        in = (Circle) view.getMap().addOverlay(outCircle);

        OverlayOptions inCircle = new CircleOptions().fillColor(accuracyCircleStrokeColor)
                .center(latLng).stroke(new Stroke(1, accuracyCircleStrokeColor))
                .radius(5);
        out = (Circle) view.getMap().addOverlay(inCircle);
        return true;
    }

    BitmapDescriptor icon = BitmapDescriptorFactory
            .fromResource(R.drawable.marker_box);
    private ArrayList<Marker> markers = new ArrayList<Marker>();

    private void drawMarkers(ArrayList<BoxData> dataList)
    {
        if (!markers.isEmpty())
        {
            for (Marker marker : markers)
            {
                marker.remove();
            }
        }
        for (int i = 0; i < dataList.size(); i++)
        {
            BoxData data = dataList.get(i);
            LatLng llA = new LatLng(data.getLatitude(), data.getLongitude());
            MarkerOptions ooA = new MarkerOptions().position(llA).icon(icon)
                    .zIndex(9).draggable(false);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            ooA.extraInfo(bundle);
            Marker mMarkerA = (Marker) view.getMap().addOverlay(ooA);
            markers.add(mMarkerA);
        }
    }

    @Override
    public boolean refreshNearbyContainer(LatLng latLng)
    {
        if (latLng == null)
        {
            latLng = targetLatlng;
            if (latLng == null)
            {
                return false;
            }
        }
        try
        {
            view.showLoading();
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("latitude", "" + latLng.latitude);
            param.put("longitude", "" + latLng.longitude);
            param.put("radius", "500");
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", containerNearby), new ShareBoxCallback()
            {
                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    JSONArray data = Json.getJSONArray("data");
                    if (data!=null && data.size()>0)
                    {
                        ArrayList<BoxData> list = new ArrayList<BoxData>();
                        for (Object object:data)
                        {
                            JSONObject o = (JSONObject) object;
                            BoxData box = new BoxData();
                            box.setContainerId(o.getString("container_id"));
                            box.setName(o.getString("name"));
                            box.setAddress(o.getString("address"));
                            box.setLatitude(o.getDouble("latitude"));
                            box.setLongitude(o.getDouble("longitude"));
                            list.add(box);
                        }
                        drawMarkers(list);
                    }
                    view.hideLoading();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    BoxData data1 = new BoxData();
                    data1.setName("data1");
                    data1.setLatitude(22.646461);
                    data1.setLongitude(113.927319);

                    BoxData data2 = new BoxData();
                    data2.setName("data2");
                    data2.setLatitude(22.646461);
                    data2.setLongitude(113.927419);

                    BoxData data3 = new BoxData();
                    data3.setName("data3");
                    data3.setLatitude(22.646461);
                    data3.setLongitude(113.927519);

                    BoxData data4 = new BoxData();
                    data4.setName("data4");
                    data4.setLatitude(22.646461);
                    data4.setLongitude(113.927619);

                    BoxData data5 = new BoxData();
                    data5.setName("data5");
                    data5.setLatitude(22.647461);
                    data5.setLongitude(113.927619);

                    BoxData data6 = new BoxData();
                    data6.setName("data6");
                    data6.setLatitude(22.645461);
                    data6.setLongitude(113.927619);

                    ArrayList<BoxData> list = new ArrayList<BoxData>();
                    list.add(data1);
                    list.add(data2);
                    list.add(data3);
                    list.add(data4);
                    list.add(data5);
                    list.add(data6);
                    drawMarkers(list);
                    view.hideLoading();
                }

                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                    LogUtil.i("onFailure:", e.getMessage());
                }
            }, param);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    private LocationClient mlo = null;

    private void getCurrentLocation()
    {
        if (mlo == null)
        {
            mlo = new LocationClient(MyApplication.mApplication);
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

            option.setCoorType("bd09ll");
            //可选，默认gcj02，设置返回的定位结果坐标系

            int span = 1000;
            //option.setScanSpan(span);
            //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

            option.setIsNeedAddress(true);
            //可选，设置是否需要地址信息，默认不需要

            option.setOpenGps(true);
            //可选，默认false,设置是否使用gps

            option.setLocationNotify(true);
            //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

            option.setIsNeedLocationDescribe(true);
            //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

            option.setIsNeedLocationPoiList(true);
            //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

            option.setIgnoreKillProcess(false);
            //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死  

            option.SetIgnoreCacheException(false);
            //可选，默认false，设置是否收集CRASH信息，默认收集

            option.setEnableSimulateGps(false);
            //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

            mlo.setLocOption(option);
            mlo.registerLocationListener(listener);
        }
        mlo.start();
        int res = mlo.requestLocation();
        LogUtil.i("requestLocation,==", "" + res);
    }

    private BDLocationListener listener = null;
    private LatLng targetLatlng = null;

    @Override
    public boolean locationCurrent()
    {
        view.showLoading();
        if (listener == null)
        {
            listener = new BDLocationListener()
            {
                @Override
                public void onReceiveLocation(BDLocation bdLocation)
                {
                    LogUtil.i("onReceiveLocation", "" + bdLocation.getLatitude(), "" + bdLocation.getLongitude(), "hascode==" + this.hashCode());
                    LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                    targetLatlng = latLng;
                    MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(latLng, 18.0f);
                    view.getMap().animateMapStatus(update);
                    drawCircle(latLng);
                    view.hideLoading();
                    refreshNearbyContainer(targetLatlng);
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i)
                {
                    LogUtil.i("onConnectHotSpotMessage", s, "" + i);
                }
            };
        }

        getCurrentLocation();
        return true;
    }


    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus)
    {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus)
    {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus)
    {
        targetLatlng = mapStatus.target;
        LogUtil.i("onMapStatusChangeFinish", "" + targetLatlng.latitude, "" + targetLatlng.longitude);
        refreshNearbyContainer(targetLatlng);
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        LogUtil.i("onMarkerClick");
        ActivityRoute.dispatcherActivity(ActivityRoute.BoxDetailActivity, marker.getExtraInfo());
        return true;
    }
}
