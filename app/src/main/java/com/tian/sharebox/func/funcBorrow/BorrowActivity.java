package com.tian.sharebox.func.funcBorrow;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tian.sharebox.MyApplication;
import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.utils.LogUtil;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class BorrowActivity extends BaseActivity implements BorrowContract.View
{
    private ImageView loadImg = null;
    private TextView loadText = null;
    private Button confirmBtn = null;
    private RotateAnimation rotateAnimation = null;
    private BorrowContract.Presenter presenter = null;

    @Override
    protected void setContent()
    {

    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_borrow_toolbar;
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_borrow;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_borrow_toolbar_title_text;
    }

    @Override
    protected void initSelfLayout()
    {
        presenter = new BorrowPresenter(this); 
        loadImg = (ImageView) findViewById(R.id.activity_borrow_load_img);
        loadText = (TextView) findViewById(R.id.activity_borrow_title);
        confirmBtn = (Button) findViewById(R.id.activity_borrow_btn);

        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        rotateAnimation.setDuration(600);

        Intent intent = getIntent();
        String paramJson = intent.getStringExtra(ActivityRoute.ParamJsonKey);
        LogUtil.i("scan info ===" + paramJson);
        presenter.checkInput(paramJson);
    }

    public void onClick(View view)
    {
        if (currentState == 0)
        {
            ActivityRoute.dispatcherActivity(ActivityRoute.InputCodeActivity, "");
            finish();
        } else if (currentState == 1)
        {
            presenter.borrow(MyApplication.mApplication.getToken(), ((BorrowPresenter) presenter).getGoodsSn());
        } else if (currentState == 2)
        {
            ActivityRoute.dispatcherActivity(ActivityRoute.DetailOrderActivity, ((BorrowPresenter) presenter).getOrderId());
            finish();
        } else if (currentState == 3)
        {
            presenter.cancel();
        }
    }

    int currentState = -1;

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    @Override
    public void updateIcon(final int state, final String res)
    {
        currentState = state;
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                loadImg.clearAnimation();
                if (state == 0)
                {
                    loadImg.setImageResource(R.drawable.borrow_error);
                    confirmBtn.setText("返回重试");
                } else if (state == 1)
                {
                    loadImg.setImageResource(R.drawable.borrow_scan_ok);
                    confirmBtn.setText("确认租借");
                } else if (state == 2)
                {
                    loadImg.setImageResource(R.drawable.borrow_success);
                    confirmBtn.setText("前往订单页面");
                } else if (state == 3)
                {
                    loadImg.setImageResource(R.drawable.borrow_load);
                    confirmBtn.setText("取消");
                } else if (state == 4)
                {
                    loadImg.setImageResource(R.drawable.borrow_error);
                    confirmBtn.setText("返回重试");
                }
                loadText.setText(res);
            }
        });

    }

    @Override
    public void showLoading()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                loadImg.clearAnimation();
                loadImg.startAnimation(rotateAnimation);
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
                loadImg.clearAnimation();
            }
        });

    }
}
