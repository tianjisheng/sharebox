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
                presenter.login(numberEditText.getText().toString(), verifyCodeEditText.getText().toString());
            }
        });
        numberEditText = (ClearEditText) findViewById(R.id.mobile_number);

        numberEditText.setOnTextChangeListener(new ClearEditText.OnTextChangeListener()
        {
            @Override
            public void afterTextChange(Editable s)
            {
                if (s.toString().length() > 0)
                {
                    verifyCodeButton.setEnabled(true);
                    signInButton.setEnabled(verifyCodeEditText.getText().length() > 0);
                } else
                {
                    verifyCodeButton.setEnabled(false);
                    signInButton.setEnabled(false);
                }
            }
        });
        verifyCodeEditText = (EditText) findViewById(R.id.verify_code);
        verifyCodeEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                signInButton.setEnabled(s.toString().length() > 0);
            }
        });
    }

    private void hideIme()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showVerifyCode()
    {
        findViewById(R.id.get_voice_verify_code).setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorToast(String error)
    {
        if (Looper.myLooper() == null)
        {
            Looper.prepare();
        }
        ToastViewUtil.showToast(error);
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
}
