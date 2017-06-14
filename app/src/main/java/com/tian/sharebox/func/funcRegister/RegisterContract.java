package com.tian.sharebox.func.funcRegister;

import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public interface RegisterContract
{
    interface View extends BaseView
    {
        void showVerifyCode();
        void showErrorToast(String error);
        void registerSuccess();
    }
    
    interface Presenter extends BasePresenter
    {
        void getVerificationCode(String number);
        void register(String number, String password,String userName);
    }
}
