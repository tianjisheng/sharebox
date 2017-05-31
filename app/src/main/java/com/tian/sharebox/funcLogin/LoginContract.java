package com.tian.sharebox.funcLogin;

import com.tian.sharebox.BaseView;
import com.tian.sharebox.presenter.BasePresenter;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public interface LoginContract
{
    interface View extends BaseView
    {
        void showVerifyCode();
        void showErrorToast(String error);
        void showLoading();
        void hideLoading();
    }
    
    interface Presenter extends BasePresenter
    {
        void getVerificationCode(String number);
        void login(String number,String code);
    }
}
