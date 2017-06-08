package com.tian.sharebox.func.funcBorrow;

import static com.tian.sharebox.network.NetworkConfig.*;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/5
 * @describe
 */

public class BorrowPresenter implements BorrowContract.Presenter
{
    private BorrowContract.View view;

    public BorrowPresenter(BorrowContract.View view)
    {
        this.view = view;
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public void checkInput(String input)
    {
        if (input != null)
        {
            view.updateIcon(1, "获取信息成功");
        } else
        {
            view.updateIcon(0, "信息为空");
        }
    }
    private String orderId = "";
    private String goodsSn = "";
    public String getOrderId()
    {
        return orderId;
    }
    public String getGoodsSn()
    {
        return goodsSn;
    }
    @Override
    public void borrow(final String token, final String goodsSn)
    {
        try
        {
            view.updateIcon(3, "正在解锁...");
            view.showLoading();
            HashMap<String, String> param = new HashMap<>();
            param.put("token", token);
            param.put("goods_sn", goodsSn);
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", add), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    view.hideLoading();
                    view.updateIcon(0, "网络原因，访问失败，检查网络后重新扫描");
                }

                @Override
                protected void handleCancelCall(Call call, IOException e)
                {
                    view.hideLoading();
                    view.updateIcon(0, "已取消");
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
                    view.updateIcon(0, "解锁失败，可返回扫描重试");
                }

                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    int state = Json.getIntValue("order_state");
                    if (state == 0)
                    {
                        view.hideLoading();
                        view.updateIcon(0, "解锁失败，可返回扫描重试");
                    } else if (state == 2)
                    {
                        try
                        {
                            Thread.sleep(1000);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        borrow(token,goodsSn);
                        LogUtil.i("borrow again");
                    } else
                    {
                        orderId = Json.getString("order_sn");
                        view.hideLoading();
                        view.updateIcon(2, "恭喜，解锁成功，可在我的租借中查看详情");
                    }
                }
            }, param);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel()
    {
        OkHttpApiImpl.getInstance().cancel(buildUrl(baseUrl, "/", add));
    }
}
