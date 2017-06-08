package com.tian.sharebox.func.funcDetailBox;

import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/2
 * @describe
 */

public interface DetailContract
{
    interface View extends BaseView
    {
        void refreshUI(ArrayList<CategoryData> list);
    }
    
    interface Presenter extends BasePresenter
    {
        void loadData(String containerId);
    }
}
