package com.tian.sharebox.funcLogin;

import android.support.annotation.NonNull;

import com.tian.sharebox.utils.CheckNonNullUtil;
import com.tian.sharebox.utils.LogUtil;
import com.tian.sharebox.utils.PhoneNumberUtil;

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
        }else
        {
            loginView.showErrorToast("手机格式有误！");
        }
       
    }

    @Override
    public void login(String number, String code)
    {
        LogUtil.i("log:","number==",number,"code==",code);
    }

    @Override
    public void onDestroy()
    {
        
    }
}
