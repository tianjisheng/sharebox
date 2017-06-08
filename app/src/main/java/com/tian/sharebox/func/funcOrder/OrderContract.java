package com.tian.sharebox.func.funcOrder;

import com.tian.sharebox.data.OrderData;
import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public interface OrderContract
{
    interface View extends BaseView
    {
        void refreshUI(int total,int pageNum,ArrayList<OrderData> list);
    }
    
    interface Presenter extends BasePresenter
    {
        void loadData(String token,int pageNum);
    }
}
