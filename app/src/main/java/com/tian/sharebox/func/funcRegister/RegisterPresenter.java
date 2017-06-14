package com.tian.sharebox.func.funcRegister;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.MyApplication;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.CheckNonNullUtil;
import com.tian.sharebox.utils.LogUtil;
import com.tian.sharebox.utils.PhoneNumberUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

import static com.tian.sharebox.network.NetworkConfig.baseUrl;
import static com.tian.sharebox.network.NetworkConfig.buildUrl;
import static com.tian.sharebox.network.NetworkConfig.login;
import static com.tian.sharebox.network.NetworkConfig.register;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public class RegisterPresenter implements RegisterContract.Presenter
{
    private RegisterContract.View loginView;

    public RegisterPresenter(@NonNull RegisterContract.View view)
    {
        loginView = CheckNonNullUtil.checkNotNull(view);
    }

    @Override
    public void getVerificationCode(String number)
    {
        if (PhoneNumberUtil.isPhoneNumberValid(number))
        {
            loginView.showVerifyCode();
        } else
        {
            loginView.showErrorToast("手机格式有误！");
        }

    }

    @Override
    public void register(final String number, String password, final String userName)
    {
        LogUtil.i("log:", "number==", number, "password==", password);
        try
        {
            loginView.showLoading();
            HashMap<String, String> param = new HashMap<>();
            param.put("mobile", number);
            param.put("username", userName);
            param.put("password", password);
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", register), new ShareBoxCallback()
            {
                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    loginView.showErrorToast("注册成功");
                    loginView.hideLoading();
                    loginView.registerSuccess();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    loginView.showErrorToast("注册成功");
                    loginView.hideLoading();
                    loginView.registerSuccess();
                }

                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    loginView.showErrorToast("注册失败");
                    loginView.hideLoading();
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
