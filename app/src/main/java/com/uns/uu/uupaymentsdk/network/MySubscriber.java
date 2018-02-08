package com.uns.uu.uupaymentsdk.network;


import com.uns.uu.uupaymentsdk.utils.MyLogger;

import rx.Subscriber;

/**
 * Created by peixuan.xie on 2016/8/11.
 */
public abstract class MySubscriber<T> extends Subscriber<T> {

    public MyLogger logger=MyLogger.kLog();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        logger.d (e.getMessage ());

        return;

    }


}