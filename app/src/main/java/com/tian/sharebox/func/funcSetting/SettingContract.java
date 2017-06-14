package com.tian.sharebox.func.funcSetting;

import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/12
 * @describe
 */

public interface SettingContract
{
    interface View extends BaseView
    {
        
    }
    
    interface Presenter extends BasePresenter
    {
        void logout(String token);
    }
}
