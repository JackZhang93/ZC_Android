package com.uns.uu.uupaymentsdk.utils;

import rx.Subscription;

/**
 * Created by peixuan.xie on 2016/8/11.
 */
public class RxUtils {

    public static Subscription subscription;

    public static void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
