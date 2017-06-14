package com.tian.sharebox.func.funcRegister;

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
import com.tian.sharebox.utils.ToastViewUtil;
import com.tian.sharebox.widget.ClearEditText;
import com.tian.sharebox.widget.LoadingToastView;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/27
 * @describe 登录界面
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View
{
    private ClearEditText numberEditText;
    private EditText verifyCodeEditText;
    private ClearEditText passwordEditText;
    private ClearEditText nicknameEditText;
    private Button verifyCodeButton;
    private Button signInButton;
    private LoadingToastView loadingToastView = null;
    private RegisterContract.Presenter presenter;

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_register;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_register_toolbar;
    }

    @Override
    protected int getTitleId()
    {
        return R.id.activity_register_toolbar_title_text;
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

    private View password = null;
    private View nickname = null;
    @Override
    protected void initSelfLayout()
    {
        super.initSelfLayout();
        presenter = new RegisterPresenter(this);
        password = findViewById(R.id.password_layout);
        nickname = findViewById(R.id.nickname_layout);
        
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
                presenter.register(numberEditText.getText().toString(), passwordEditText.getText().toString(),nicknameEditText.getText().toString());
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
                } else
                {
                    verifyCodeButton.setEnabled(false);
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
                if (s.toString().equals("170527"))
                {
                    password.setVisibility(View.VISIBLE);
                    nickname.setVisibility(View.VISIBLE);
                }else 
                {
                    password.setVisibility(View.GONE);
                    nickname.setVisibility(View.GONE);
                }
            }
        });

        passwordEditText = (ClearEditText) findViewById(R.id.password_number);
        passwordEditText.addTextChangedListener(new TextWatcher()
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
                signInButton.setEnabled(s.toString().length() >= 6);
            }
        });
        nicknameEditText = (ClearEditText) findViewById(R.id.user_name_number);
        
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
                    loadingToastView.setLoadingText("正在注册");
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
    public void registerSuccess()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                RegisterActivity.this.finish();
            }
        });
    }

    @Override
    public boolean isUIShown()
    {
        return isShown();
    }
}
