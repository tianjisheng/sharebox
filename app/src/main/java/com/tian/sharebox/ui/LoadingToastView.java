package com.tian.sharebox.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tian.sharebox.R;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/25
 * @describe
 */

public class LoadingToastView extends FrameLayout
{
    private ImageView loadImage;
    private TextView loadText;
    private AnimationDrawable loadAnimation;

    public LoadingToastView(Context context)
    {
        super(context);
    }

    public LoadingToastView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs, 0);
    }

    public LoadingToastView(@NonNull Context context, @Nullable AttributeSet attrs,
                            @AttrRes int defStyleAttr)
    {
        super(context, attrs, defStyleAttr, 0);
    }


    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        this.loadText = (TextView) findViewById(R.id.loading_toast_text);
        this.loadImage = (ImageView) findViewById(R.id.loading_toast_img);
        this.loadAnimation = (AnimationDrawable) loadImage.getDrawable();
    }

    public void show()
    {
        if (this.loadAnimation != null)
        {
            this.loadAnimation.start();
        }
        setVisibility(VISIBLE);
    }

    public void hide()
    {
        if (this.loadAnimation != null)
        {
            this.loadAnimation.stop();
        }
        setVisibility(INVISIBLE);
    }

    public void setLoadingText(String text)
    {
        if (this.loadText != null)
        {
            this.loadText.setText(text);
        }
    }

    public void setLoadingText(int resId)
    {
        if (this.loadText != null)
        {
            this.loadText.setText(resId);
        }

    }
}
