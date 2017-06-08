package com.tian.sharebox.func.funcBorrow;

import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/5
 * @describe
 */

public interface BorrowContract
{
    interface View extends BaseView
    {
        /**
         * state == 0 失败
         * state == 1 获取信息正确
         * state == 2 租借成功
         * state == 3 loading
         * state == 4 取消
         * @param state
         * @param res
         */
        void updateIcon(int state,String res);
    }
    
    interface Presenter extends BasePresenter
    {
        void checkInput(String input);
        void borrow(String token,String goodsSn);
        void cancel();
            
    }
}
