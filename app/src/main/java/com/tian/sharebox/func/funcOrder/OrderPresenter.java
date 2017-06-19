package com.tian.sharebox.func.funcOrder;

import static com.tian.sharebox.network.NetworkConfig.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.MyApplication;
import com.tian.sharebox.data.OrderData;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class OrderPresenter implements OrderContract.Presenter
{
    private OrderContract.View view;

    public OrderPresenter(OrderContract.View view)
    {
        this.view = view;
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public void loadData(String token, int pageNum)
    {
        view.showLoading();
        try
        {
            OkHttpApiImpl.getInstance().getString(buildUrl(baseUrl, "/", orderAll, "/", token, "/", "" + pageNum), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    ArrayList<OrderData> list = new ArrayList<>();
                    OrderData data1 = new OrderData();
                    data1.setStartTime("2017-06-07 00:00:00");
                    data1.setEndTime("2017-06-08 00:00:00");
                    data1.setGoodsName("单车");
                    data1.setStateCode(0);
                    data1.setOrderId("id001");
                    list.add(data1);

                    OrderData data2 = new OrderData();
                    data2.setStartTime("2017-06-09 00:00:00");
                    data2.setEndTime("2017-06-10 00:00:00");
                    data2.setGoodsName("从点播");
                    data2.setStateCode(5);
                    data2.setOrderId("id002");
                    list.add(data2);
                    view.refreshUI(2, 0, list);
                    view.hideLoading();
                }

                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    JSONArray jsonArray = Json.getJSONArray("order_list");
                    ArrayList<OrderData> list = new ArrayList<OrderData>();
                    int total = Json.getIntValue("total");
                    int pageNum = Json.getIntValue("page_num");
                    if (jsonArray != null && jsonArray.size() > 0)
                    {
                        for (Object object : jsonArray)
                        {
                            JSONObject o = (JSONObject) object;
                            OrderData data = new OrderData();
                            data.setOrderId(o.getString("order_sn"));
                            data.setStateCode(o.getIntValue("order_state"));
                            data.setGoodsName(o.getString("goods_name"));
                            data.setStartTime(o.getString("start_time"));
                            data.setEndTime(o.getString("finish_time"));
                            list.add(data);
                        }
                    }
                    view.refreshUI(total, pageNum, list);
                    view.hideLoading();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
