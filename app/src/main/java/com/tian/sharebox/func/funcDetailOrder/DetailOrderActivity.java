package com.tian.sharebox.func.funcDetailOrder;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
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

    private Button cancelBtn = null;
    private Button payButton = null;
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
        cancelBtn = (Button) findViewById(R.id.order_detail_cancel_btn);
        payButton = (Button) findViewById(R.id.order_detail_pay_btn);
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

    private OrderDetailData detailData = null;

    @Override
    public void refreshUI(final OrderDetailData data)
    {
        detailData = data;
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
                cancelBtn.setEnabled(data.getStateCode()!=5);
                payButton.setEnabled(data.getStateCode()==4);
                isLoadData = true;
            }
        });
    }

    @Override
    public void cancel(boolean isSuccess,final String describe)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                cancelBtn.setEnabled(true);
                Snackbar.make(cancelBtn,describe,Snackbar.LENGTH_LONG).show(); 
            }
        });
    }

    @Override
    public void pay(boolean isSuccess,final String describe)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                payButton.setEnabled(true);
                Snackbar.make(payButton,describe,Snackbar.LENGTH_LONG).show();
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
                if (detailData==null || detailData.getStateCode()==5)
                {
                    Snackbar.make(view,"订单现在无法取消",Snackbar.LENGTH_LONG).show();
                }else
                {
                    cancelBtn.setEnabled(false);
                    presenter.cancelOrder(MyApplication.mApplication.getToken(),detailData.getOrderId());
                }
                break;
            case R.id.order_detail_pay_btn:
                if (detailData!=null&&detailData.getStateCode() == 4)
                {
                    payButton.setEnabled(false);
                    presenter.payOrder(MyApplication.mApplication.getToken(),detailData.getOrderId());
                }else
                {
                    Snackbar.make(view,"订单现在无法完成支付",Snackbar.LENGTH_LONG).show();
                }
                
                break;
            default:

        }
    }
}
