package com.tian.sharebox.func.funcGoodsDetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.widget.LoadingToastView;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public class DetailGoodsActivity extends BaseActivity implements DetailGoodsContract.View
{
    private DetailGoodsAdapter adapter;
    private DetailGoodsPresenter presenter;
    private RecyclerView recyclerView;
    private LoadingToastView loadingToastView = null;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_goods_detail_toolbar;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_goods_detail_toolbar_title_text;
    }

    @Override
    protected void initSelfLayout()
    {
        presenter = new DetailGoodsPresenter(this);
        adapter = new DetailGoodsAdapter(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.activity_goods_detail_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        String categoryId = getIntent().getStringExtra(ActivityRoute.ParamJsonKey);
        if (categoryId != null && categoryId.length() > 0)
        {
            presenter.getCategory(categoryId);
        }
    }

    @Override
    public void refreshCategory(ArrayList<CategoryData> list)
    {
        adapter.setGoods(list);
    }


    @Override
    public boolean isUIShown()
    {
        return false;
    }

    @Override
    public void showLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (loadingToastView == null)
                {
                    loadingToastView = (LoadingToastView) findViewById(R.id.activity_goods_detail_loading);
                    loadingToastView.setLoadingText("正在加载...");
                }

                if (!loadingToastView.isShown())
                {
                    loadingToastView.show();
                }
            }
        });
    }

    @Override
    public void hideLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (loadingToastView != null && loadingToastView.isShown())
                {
                    loadingToastView.hide();
                }
            }
        });
    }
}
