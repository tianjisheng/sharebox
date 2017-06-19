package com.tian.sharebox.func.funcGoodsDetail;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;

import static com.tian.sharebox.network.NetworkConfig.*;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public class DetailGoodsPresenter implements DetailGoodsContract.Presenter
{
    private DetailGoodsContract.View view = null;

    public DetailGoodsPresenter(DetailGoodsContract.View view)
    {
        this.view = view;
    }

    @Override
    public void getCategory(String category)
    {
        try
        {
            view.showLoading();
            OkHttpApiImpl.getInstance().getString(buildUrl(baseUrl, "/", goodsCategory, "/", category), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                }

                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    ArrayList<CategoryData> list = new ArrayList<CategoryData>(1);
                    CategoryData data = new CategoryData();
                    data.setCategoryCharging(Json.getString("charging"));
                    data.setCategoryId(Json.getString("category"));
                    data.setCategoryName(Json.getString("name"));
                    data.setIconPath(Json.getString("goods_img"));
                    view.refreshCategory(list);
                    view.hideLoading();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
            view.hideLoading();
        }
    }

    @Override
    public void getAllCategory()
    {

    }

    @Override
    public void onDestroy()
    {

    }
}
