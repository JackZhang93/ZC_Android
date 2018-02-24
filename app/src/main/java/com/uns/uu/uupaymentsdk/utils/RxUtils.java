package com.uns.uu.uupaymentsdk.utils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by peixuan.xie on 2016/8/11.
 * RxUtils
 */
public class RxUtils {

    public static Subscription subscription;

    public static void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static <T> Observable<T> getObservable(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(t);
                subscriber.onCompleted();
                if (!subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                }
            }
        });
    }
}
