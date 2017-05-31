package com.tian.sharebox.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tian.sharebox.R;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/27
 * @describe
 */

public class LoadingPageView extends LinearLayout
{
    private ImageView loadImage;
    private TextView loadText;
    private AnimationDrawable loadAnimation;
    public LoadingPageView(Context context)
    {
        super(context);
    }

    public LoadingPageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public LoadingPageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public LoadingPageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        this.loadText = (TextView) findViewById(R.id.loading_text);
        this.loadImage = (ImageView) findViewById(R.id.loading_page_img);
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
