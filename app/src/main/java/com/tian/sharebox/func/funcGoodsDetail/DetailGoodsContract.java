package com.tian.sharebox.func.funcGoodsDetail;

import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.presenter.BasePresenter;
import com.tian.sharebox.view.BaseView;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public interface DetailGoodsContract
{
    interface View extends BaseView
    {
        void refreshCategory(ArrayList<CategoryData> list);
    }

    interface Presenter extends BasePresenter
    {
        void getCategory(String category);

        void getAllCategory();
    }
}
