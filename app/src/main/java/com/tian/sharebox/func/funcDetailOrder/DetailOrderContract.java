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
        void showLoading();
        void hideLoading();
        void refreshUI(OrderDetailData data);
    }
    
    interface Presenter extends BasePresenter
    {
        void loadData(String token,String orderId);
        void cancelOrder(String token,String orderId);
        void payOrder(String token,String orderId);
    }
}
