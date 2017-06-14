package com.tian.sharebox.func.funcDetailOrder;

import static com.tian.sharebox.network.NetworkConfig.*;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.data.OrderDetailData;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class DetailOrderPresenter implements DetailOrderContract.Presenter
{
    private DetailOrderContract.View view = null;

    public DetailOrderPresenter(DetailOrderContract.View view)
    {
        this.view = view;
    }

    @Override
    public void loadData(String token, final String orderId)
    {
        view.showLoading();
        try
        {// 
            OkHttpApiImpl.getInstance().getString(buildUrl(baseUrl, "/", orderDetail,
                    "/",orderId), new ShareBoxCallback()
            {
                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    OrderDetailData data = new OrderDetailData();
                    data.setOrderId(orderId);
                    data.setStartTime(Json.getString("start_time"));
                    data.setEndTime(Json.getString("finish_time"));
                    data.setGoodsName(Json.getString("goods_category"));
                    data.setStateCode(Json.getIntValue("order_state"));
                    data.setDistance(Json.getFloatValue("distance"));
                    data.setCost(Json.getFloatValue("cost"));
                    data.setBorrowContainerId(Json.getString("borrow_lock_id"));
                    data.setReturnContainerId(Json.getString("return_lock_id"));
                    view.refreshUI(data);
                    view.hideLoading();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    OrderDetailData data = new OrderDetailData();
                    data.setOrderId(orderId);
                    data.setGoodsName("单车");
                    data.setStateCode(3);
                    data.setDistance(3.5f);
                    data.setCost(1.02f);
                    data.setBorrowContainerId("b23");
                    data.setReturnContainerId("r45");
                    view.refreshUI(data);
                    view.hideLoading();
                }

                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelOrder(String token, String orderId)
    {
        try
        {
            view.showLoading();
            HashMap<String, String> param = new HashMap<>();
            param.put("token", token);
            param.put("order_sn", orderId);
            param.put("msg","");
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", orderCancel), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                    view.cancel(false,"订单取消失败");
                }

                @Override
                protected void handleCallbackSuccess(Call call, Response response)
                {
                    view.hideLoading();
                    view.cancel(true,"订单取消成功");
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    view.hideLoading();
                    view.cancel(false,"订单取消失败");
                }
            }, param);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void payOrder(String token, String orderId)
    {
        try
        {
            view.showLoading();
            HashMap<String, String> param = new HashMap<>();
            param.put("token", token);
            param.put("order_sn", orderId);
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", orderPay), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                    view.pay(false,"订单支付失败");
                }

                @Override
                protected void handleCallbackSuccess(Call call, Response response)
                {
                    view.hideLoading();
                    view.pay(true,"订单支付成功");
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    view.hideLoading();
                    view.pay(false,"订单支付失败");
                }
            }, param);
        } catch (IOException e)

        {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy()
    {

    }
}
