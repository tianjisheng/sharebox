package com.tian.sharebox.func.funcUser;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.config.UserActivityConfig;
import com.tian.sharebox.config.UserItemConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/23
 * @describe
 */

public class UserCenterActivity extends Activity
{
    private HashMap<View, UserItemConfig> viewUserItemConfigHashMap = new HashMap<>();
    private Toolbar mToolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.up_arrow_style);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleBack();
            }
        });
        createItems();
    }

    private LinearLayout contentLayout = null;
    private int groupDivide = 0;

    private void createItems()
    {
        contentLayout = (LinearLayout) findViewById(R.id.view_user_center_content_main);
        HashMap<Integer, UserItemConfig> configs = new HashMap<>();
        putItemConfig(configs, UserActivityConfig.myBorrowItem);
        putItemConfig(configs, UserActivityConfig.myMessageItem);
        putItemConfig(configs, UserActivityConfig.settingItem);

        int size = configs.size();
        int last = 0;
        for (int i = 0; i < size; i++)
        {
            int temp = Integer.MAX_VALUE;
            Iterator<Map.Entry<Integer, UserItemConfig>> iterators = configs.entrySet().iterator();
            while (iterators.hasNext())
            {
                int temp2 = iterators.next().getKey();
                if (temp > temp2)
                {
                    temp = temp2;
                }
            }
            UserItemConfig userItemConfig = configs.remove(temp);
            if (last / 100 != temp / 100)
            {
                groupDivide = 20;//group 之间间隔 dp
            }
            createDivide();
            View view = createItem(userItemConfig);
            contentLayout.addView(view);
            if (groupDivide != 0)
            {
                ((LinearLayout.LayoutParams) view.getLayoutParams()).topMargin = groupDivide;
            }
            groupDivide = 0;
            viewUserItemConfigHashMap.put(view, userItemConfig);
            
            last = temp;
        }
        configs.clear();
    }


    private void putItemConfig(HashMap<Integer, UserItemConfig> maps, UserItemConfig item)
    {
        maps.put(item.groupIndex * 100 + item.itemIndex, item);
    }

    private View createItem(@NonNull UserItemConfig userItemConfig)
    {
        LinearLayout item = null;
        item = (LinearLayout) this.getLayoutInflater().inflate(R.layout.view_user_center_item_red, contentLayout, false);
        ((TextView) item.findViewById(R.id.view_user_center_item_red_title)).setText(userItemConfig.itemTitle);
        ((TextView) item.findViewById(R.id.view_user_center_item_red_right)).setText(userItemConfig.summaryTitle);
        if (userItemConfig.hasRed)
        {
            item.findViewById(R.id.view_user_center_item_red_tip).setVisibility(View.VISIBLE);
        }
        return item;
    }

    private void createDivide()
    {
        this.getLayoutInflater().inflate(R.layout.divider_user_center, contentLayout);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        return super.onMenuItemSelected(featureId, item);
    }


    @Override
    public void onBackPressed()
    {
        handleBack();
    }

    private void handleBack()
    {
        super.onBackPressed();
    }

    public void onItemClick(View view)
    {
        ActivityRoute.dispatcherActivity(viewUserItemConfigHashMap.get(view).toWhere,"");
    }
}
