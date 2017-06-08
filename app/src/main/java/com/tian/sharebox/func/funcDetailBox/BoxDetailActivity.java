package com.tian.sharebox.func.funcDetailBox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.data.BoxData;
import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.widget.LoadingToastView;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/2
 * @describe 展示一个box详细信息
 */

public class BoxDetailActivity extends BaseActivity implements DetailContract.View
{
    private RecyclerView recyclerView = null;
    private TextView name;
    private TextView address;
    private DetailPresenter presenter = null;
    private LoadingToastView loadingToastView = null;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_box_detail_toolbar;
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_box_detail;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_box_detail_toolbar_title_text;
    }

    private CategoryAdapter adapter = null;

    @Override
    protected void initSelfLayout()
    {
        presenter = new DetailPresenter(this);

        name = (TextView) findViewById(R.id.activity_box_detail_name_value);
        address = (TextView) findViewById(R.id.activity_box_detail_address_value);

        recyclerView = (RecyclerView) findViewById(R.id.activity_box_detail_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new CategoryAdapter(getApplicationContext());
        if (!parseParams())
        {
            initTestData();
        }
        recyclerView.setAdapter(adapter);
    }

    private boolean parseParams()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(ActivityRoute.ParamBundleKey);
        if (bundle == null)
        {
            return false;
        }
        initData(bundle);
        return true;
    }

    private BoxData boxData = null;
    private boolean loadData = false;

    private void initData(Bundle bundle)
    {
        BoxData data = (BoxData) bundle.get("data");

        String nameValue = data.getName();
        this.name.setText(nameValue);

        String addressValue = data.getAddress();
        this.address.setText(addressValue);

        boxData = data;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (boxData != null && !loadData)
        {
            presenter.loadData(boxData.getContainerId());
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initTestData()
    {
        ArrayList<CategoryData> list = new ArrayList<>();
        CategoryData data0 = new CategoryData();
        data0.setTitle("名称");
        data0.setAvailableTotal("当前可用数");
        list.add(data0);


        CategoryData data1 = new CategoryData();
        data1.setTitle("单车");
        data1.setAvailableTotal(23);
        list.add(data1);

        CategoryData data2 = new CategoryData();
        data2.setTitle("飞行器");
        data2.setAvailableTotal(12);
        list.add(data2);

        CategoryData data3 = new CategoryData();
        data3.setTitle("手机");
        data3.setAvailableTotal(99);
        list.add(data3);

        CategoryData data4 = new CategoryData();
        data4.setTitle("充电宝");
        data4.setAvailableTotal(3);
        list.add(data4);

        CategoryData data5 = new CategoryData();
        data5.setTitle("雨伞");
        data5.setAvailableTotal(213);
        list.add(data5);
        adapter.setCategoryDataList(list);
    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    @Override
    public void showLoading()
    {
        if (loadingToastView == null)
        {
            loadingToastView = (LoadingToastView) findViewById(R.id.activity_box_detail_loading);
        }
        loadingToastView.setLoadingText("正在加载");
        loadingToastView.show();
    }

    @Override
    public void hideLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                loadingToastView.hide();
            }
        });
    }

    @Override
    public void refreshUI(final ArrayList<CategoryData> list)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                adapter.setCategoryDataList(list);
                loadData = true;
            }
        });
    }
}
