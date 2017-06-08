package com.tian.sharebox.func.funcLogin;

import android.support.annotation.NonNull;

import static com.tian.sharebox.network.NetworkConfig.*;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.MyApplication;
import com.tian.sharebox.network.okhttp.OkHttpApiImpl;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.CheckNonNullUtil;
import com.tian.sharebox.utils.LogUtil;
import com.tian.sharebox.utils.PhoneNumberUtil;
import com.tian.sharebox.utils.ShareDataUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public class LoginPresenter implements LoginContract.Presenter
{
    private LoginContract.View loginView;

    public LoginPresenter(@NonNull LoginContract.View view)
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
    public void login(String number, String code)
    {
        LogUtil.i("log:", "number==", number, "code==", code);
        try
        {
            loginView.showLoading();
            HashMap<String, String> param = new HashMap<>();
            param.put("username", number);
            param.put("password", code);
            OkHttpApiImpl.getInstance().postString(buildUrl(baseUrl, "/", login), new ShareBoxCallback()
            {
                @Override
                protected void handleResultSuccess(Call call, JSONObject Json)
                {
                    String token = Json.getString("token");
                    if (token == null)
                    {
                        handleResultFailure(call);
                        return;
                    }
                    MyApplication.mApplication.setToken(token);
                    loginView.showErrorToast("登录成功");
                    loginView.hideLoading();
                    loginView.loginSuccess();
                }

                @Override
                protected void handleResultFailure(Call call)
                {
                    MyApplication.mApplication.setToken("tianjisheng");
                    loginView.showErrorToast("登录成功");
                    loginView.hideLoading();
                    loginView.loginSuccess();
                }

                @Override
                public void handleCallbackFailure(Call call, IOException e)
                {
                    loginView.showErrorToast("登录失败");
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
