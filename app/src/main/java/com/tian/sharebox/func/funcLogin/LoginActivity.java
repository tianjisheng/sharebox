package com.tian.sharebox.func.funcLogin;

import android.content.Context;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.tian.sharebox.R;
import com.tian.sharebox.activity.ActivityRoute;
import com.tian.sharebox.activity.BaseActivity;
import com.tian.sharebox.widget.ClearEditText;
import com.tian.sharebox.widget.LoadingToastView;
import com.tian.sharebox.utils.ToastViewUtil;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/27
 * @describe 登录界面
 */

public class LoginActivity extends BaseActivity implements LoginContract.View
{
    private ClearEditText numberEditText;
    private EditText verifyCodeEditText;
    private Button verifyCodeButton;
    private Button signInButton;
    private LoadingToastView loadingToastView = null;
    private LoginContract.Presenter presenter;
    private ClearEditText passwordEditText;
    private View code = null;

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_login;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_login_toolbar;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_login_toolbar_title_text;
    }

    @Override
    protected void setContent()
    {

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void initSelfLayout()
    {
        super.initSelfLayout();
        presenter = new LoginPresenter(this);
        code = findViewById(R.id.code_layout);
        passwordEditText = (ClearEditText) findViewById(R.id.password_number);
        passwordEditText.setOnTextChangeListener(new ClearEditText.OnTextChangeListener()
        {
            @Override
            public void afterTextChange(Editable s)
            {
                if (s.toString().length()>0)
                {
                    signInButton.setEnabled(true);
                }else
                {
                    signInButton.setEnabled(false);
                }
            }
        });
        verifyCodeButton = (Button) findViewById(R.id.get_verify_code_btn);
        verifyCodeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.getVerificationCode(numberEditText.getText().toString());
            }
        });
        signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideIme();
                presenter.login(numberEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
        numberEditText = (ClearEditText) findViewById(R.id.user_name_number);
        verifyCodeEditText = (EditText) findViewById(R.id.verify_code);

    }

    private void hideIme()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void showVerifyCode()
    {
        findViewById(R.id.get_voice_verify_code).setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorToast(final String error)
    {
       runOnUiThread(new Runnable()
       {
           @Override
           public void run()
           {
               ToastViewUtil.showToast(error);
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
                if (loadingToastView == null)
                {
                    loadingToastView = (LoadingToastView) findViewById(R.id.activity_login_loading);
                    loadingToastView.setLoadingText("正在登录");
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

    @Override
    public void loginSuccess()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                LoginActivity.this.finish();
            }
        });
    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }

    public void onClick(View view)
    {
        ActivityRoute.dispatcherActivity(ActivityRoute.RegisterActivity, "");
    }
}
