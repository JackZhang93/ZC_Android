package com.uns.uu.uupaymentsdk.utils;

import android.os.Handler;
import android.widget.TextView;

/**
 * author: 张承
 * time：2018/2/24
 * des：
 */

public class RefreshVerifyCode implements Runnable{
    public static final int TIME = 1000;
    private TextView btn;
    private Handler handler;
    private int count;
    private boolean isRunning = true;

    public RefreshVerifyCode(TextView btn, Handler handler) {
        this.btn = btn;
        this.handler = handler;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        if (!isRunning) {
            btn.setText("重新获取");
            btn.setEnabled(true);
            return;
        }
        if (count-- > 0) {
            btn.setText(String.format("重新发送(%d)",count));
            btn.setEnabled(false);
            handler.postDelayed(this, TIME);
        } else {
            btn.setText("重新获取");
            btn.setEnabled(true);
        }
    }

    public void cancel() {
        isRunning = false;
    }
    public void sure(){
        isRunning = true;
    }
}
