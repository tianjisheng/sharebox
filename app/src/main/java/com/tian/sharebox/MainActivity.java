package com.tian.sharebox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.funcScan.ScanActivity;

public class MainActivity extends Activity
{
    private Toolbar mToolbar;
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mToolbar = (Toolbar)findViewById(R.id.activity_main_toolbar);
        mToolbar.setNavigationIcon(R.drawable.actionbar_home);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ToUserCenter();
            }
        });
        ((TextView) findViewById(R.id.activity_main_toolbar_title_text)).setText(this.getTitle());
        
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMapView.onDestroy();
    }

    public void onStartScan(View view)
    {
        Intent intent = new Intent(this,ScanActivity.class);
        this.startActivity(intent);

    }
    
    public void onRefreshBtn(View view)
    {
        
    }
    
    public void onLocationBtn(View view)
    {
        
    }
    
    public void onMessageBtn(View view)
    {
        ToMyMessage();
    }
    
    private void ToUserCenter()
    {
        ActivityRoute.dispatcherActivity("activity/UserCenterActivity","");
    }

    private void ToMyMessage()
    {
        ActivityRoute.dispatcherActivity("activity/UserMessageActivity","");
    }

}
