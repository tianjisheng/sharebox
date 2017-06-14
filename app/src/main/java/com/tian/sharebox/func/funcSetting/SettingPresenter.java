package com.tian.sharebox.func.funcSetting;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

import static com.tian.sharebox.network.NetworkConfig.*;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/12
 * @describe
 */

public class SettingPresenter implements SettingContract.Presenter
{
    private SettingContract.View view = null;

    public SettingPresenter(SettingContract.View view)
    {
        this.view = view;
    }

    @Override
    public void logout(String token)
    {
        try
        {
            HashMap<String, String> param = new HashMap<>();
            param.put("token", token);
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", logout), new ShareBoxCallback()
            {
                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {

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
