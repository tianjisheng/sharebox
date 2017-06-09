package com.tian.sharebox.func.funcOrder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.data.OrderData;
import com.tian.sharebox.utils.LogUtil;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class OrderActivity extends BaseActivity implements OrderContract.View, SwipyRefreshLayout.OnRefreshListener
{
    private OrderAdapter orderAdapter = null;
    private RecyclerView recyclerView = null;
    private SwipyRefreshLayout swipyRefreshLayout = null;
    private TextView totalText = null;
    private OrderContract.Presenter presenter = null;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_orders;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_order_toolbar;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_order_toolbar_title_text;
    }

    @Override
    protected void initSelfLayout()
    {
        super.initSelfLayout();
        presenter = new OrderPresenter(this);
        totalText = (TextView) findViewById(R.id.activity_orders_total);

        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.my_orders_swipy_refresh_layout);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
        swipyRefreshLayout.setOnRefreshListener(this);

        orderAdapter = new OrderAdapter(getApplicationContext());
        orderAdapter.setCallback(new OrderItemCallback()
        {
            @Override
            public void onClick(OrderData data)
            {
                ActivityRoute.dispatcherActivity(ActivityRoute.DetailOrderActivity,data.getOrderId());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_orders_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(orderAdapter);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {//之所以放在此处，是为了保证swipyRefreshLayout已绘制，让loading可以正常显示。
            presenter.loadData(MyApplication.mApplication.getToken(),currentPage+1);
        }
    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction)
    {
        LogUtil.i("" + direction);
        presenter.loadData(MyApplication.mApplication.getToken(),currentPage+1);
        
    }

    @Override
    public void showLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                swipyRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }

    int currentPage = -1;
    @Override
    public void refreshUI(int total,int pageNum,final ArrayList<OrderData> list)
    {
        currentPage = pageNum;
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                orderAdapter.setOrderLists(list);
                totalText.setText(getString(R.string.activity_order_total, orderAdapter.getItemCount()));
            }
        });
    }
}
