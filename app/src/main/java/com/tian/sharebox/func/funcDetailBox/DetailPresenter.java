package com.tian.sharebox.func.funcDetailBox;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.data.AvailableGoodsData;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.CheckNonNullUtil;

import static com.tian.sharebox.network.NetworkConfig.*;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/2
 * @describe
 */

public class DetailPresenter implements DetailContract.Presenter
{
    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view)
    {
        this.view = CheckNonNullUtil.checkNotNull(view);
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public void loadData(String containerId)
    {
        view.showLoading();
        try
        {
            OkHttpApiImpl.getInstance().getString(buildUrl(baseUrl, "/", containerSummary, "/", containerId), new ShareBoxCallback()
            {
                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    JSONArray detail_list = Json.getJSONArray("detail_list");
                    if (detail_list != null && detail_list.size() > 1)
                    {
                        ArrayList<AvailableGoodsData> list = new ArrayList<>();
                        for (Object object : detail_list)
                        {
                            JSONObject o = (JSONObject) object;
                            AvailableGoodsData data = new AvailableGoodsData();
                            data.setTitle(o.getString("name"));
                            data.setCategoryId(o.getString("category"));
                            data.setAvailableTotal(o.getIntValue("remain_total"));
                            list.add(data);
                        }
                        view.refreshUI(list);
                    }
                    view.hideLoading();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    try
                    {
                        Thread.sleep(3000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    view.hideLoading();
                    ArrayList<AvailableGoodsData> list = new ArrayList<>();
                    AvailableGoodsData data0 = new AvailableGoodsData();
                    data0.setTitle("名称");
                    data0.setAvailableTotal("当前可用数");
                    list.add(data0);


                    AvailableGoodsData data1 = new AvailableGoodsData();
                    data1.setTitle("单车");
                    data1.setAvailableTotal(23);
                    list.add(data1);

                    AvailableGoodsData data2 = new AvailableGoodsData();
                    data2.setTitle("飞行器");
                    data2.setAvailableTotal(12);
                    list.add(data2);

                    AvailableGoodsData data3 = new AvailableGoodsData();
                    data3.setTitle("手机");
                    data3.setAvailableTotal(9);
                    list.add(data3);

                    AvailableGoodsData data4 = new AvailableGoodsData();
                    data4.setTitle("充电宝");
                    data4.setAvailableTotal(3);
                    list.add(data4);

                    AvailableGoodsData data5 = new AvailableGoodsData();
                    data5.setTitle("雨伞");
                    data5.setAvailableTotal(213);
                    list.add(data5);
                    view.refreshUI(list);
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
}
