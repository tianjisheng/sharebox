package com.tian.sharebox.func.funcDetailOrder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.data.OrderDetailData;
import com.tian.sharebox.widget.LoadingToastView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class DetailOrderActivity extends BaseActivity implements DetailOrderContract.View
{
    private LoadingToastView loadingToastView = null;
    private TextView orderIdText;
    private TextView orderStateText;
    private TextView goodsText;
    private TextView orderStartTimeText;
    private TextView orderEndTimeText;
    private TextView orderDistanceText;
    private TextView orderCostText;
    private TextView orderBorrowFromText;
    private TextView orderReturnToText;
    private DetailOrderContract.Presenter presenter = null;
    private boolean isLoadData = false;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_orders_detail;
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
        presenter = new DetailOrderPresenter(this);
        orderIdText = (TextView) findViewById(R.id.my_order_detail_item_body_order_id);
        orderStateText = (TextView) findViewById(R.id.my_order_detail_item_body_order_state);
        goodsText = (TextView) findViewById(R.id.my_order_detail_item_body_order_goods);
        orderStartTimeText = (TextView) findViewById(R.id.my_order_detail_item_body_start_time);
        orderEndTimeText = (TextView) findViewById(R.id.my_order_detail_item_body_end_time);
        orderDistanceText = (TextView) findViewById(R.id.my_order_detail_item_body_distance);
        orderCostText = (TextView) findViewById(R.id.my_order_detail_item_body_cost);
        orderBorrowFromText = (TextView) findViewById(R.id.my_order_detail_item_body_borrow_from);
        orderReturnToText = (TextView) findViewById(R.id.my_order_detail_item_body_return_to);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (!isLoadData)
        {
            Intent intent = getIntent();
            String orderId = intent.getStringExtra(ActivityRoute.ParamJsonKey);
            presenter.loadData(MyApplication.mApplication.getToken(), orderId);
        }
    }

    @Override
    public void showLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (loadingToastView == null)
                {
                    loadingToastView = (LoadingToastView) findViewById(R.id.activity_order_detail_loading);
                    loadingToastView.setLoadingText("正在加载...");
                }
                if (!loadingToastView.isShown())
                {
                    loadingToastView.show();
                }
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
                if (loadingToastView != null && loadingToastView.isShown())
                {
                    loadingToastView.hide();
                }
            }
        });

    }

    @Override
    public void refreshUI(final OrderDetailData data)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                orderIdText.setText(getString(R.string.activity_order_order_id, data.getOrderId()));
                orderStateText.setText(getString(R.string.activity_order_order_state, data.getOrderState()));
                goodsText.setText(getString(R.string.activity_order_order_goods, data.getGoodsName()));
                orderStartTimeText.setText(getString(R.string.activity_order_order_start_time, data.getStartTime()));
                orderEndTimeText.setText(getString(R.string.activity_order_order_end_time, data.getEndTime()));
                orderDistanceText.setText(getString(R.string.activity_order_distance, data.getDistance()));
                orderCostText.setText(getString(R.string.activity_order_cost, data.getCost()));
                orderBorrowFromText.setText(getString(R.string.activity_order_borrow_from, data.getBorrowContainerId()));
                orderReturnToText.setText(getString(R.string.activity_order_return_to, data.getReturnContainerId()));
                isLoadData = true;
            }
        });
    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.order_detail_cancel_btn:
                presenter.cancelOrder("", "");
                break;
            case R.id.order_detail_pay_btn:
                presenter.payOrder("", "");
                break;
            default:

        }
    }
}
