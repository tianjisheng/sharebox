package com.tian.sharebox.func.funcDetailOrder;

import com.tian.sharebox.data.OrderDetailData;
import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public interface DetailOrderContract
{
    interface View extends BaseView
    {
        void refreshUI(OrderDetailData data);
        void cancel(boolean isSuccess,String describe);
        void pay(boolean isSuccess,String describe);
    }
    
    interface Presenter extends BasePresenter
    {
        void loadData(String token,String orderId);
        void cancelOrder(String token,String orderId);
        void payOrder(String token,String orderId);
    }
}
