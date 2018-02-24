package com.uns.uu.unstoast;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhaoyan on 2018/2/24.
 * 自定义Toast
 */

public class UnsToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     * or {@link Activity} object.
     */
    private TextView message;
    private CharSequence oldInfo = "";
    private long time = 0;
    private ObjectAnimator mAnimator;

    public UnsToast(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.toast_uns, null);
        message = view.findViewById(R.id.uns_message);
        setView(view);
        mAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.8f, 0.6f, 0.4f, 0.2f, 0.0f);
        mAnimator.setDuration(4000);
        mAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        mAnimator.setRepeatCount(1);
    }


    @Override
    public void setText(int resId) {
        message.setText(resId);
    }


    @Override
    public void setText(CharSequence s) {
        message.setText(s);
    }

    @Override
    public void show() {

        if (oldInfo.equals(message.getText())) {
            if (System.currentTimeMillis() - time >= 4000) {
                super.show();
                startAnimation();
                time = System.currentTimeMillis();
                oldInfo = message.getText();
            }
        } else {
            super.show();
            startAnimation();
            time = System.currentTimeMillis();
            oldInfo = message.getText();
        }

    }

    private void startAnimation() {
        if (null != mAnimator) {
            mAnimator.start();
        }
    }
}
