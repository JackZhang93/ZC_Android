package com.uns.uu.uupaymentsdk.network;

import com.uns.uu.uupaymentsdk.bean.CardBin;
import com.uns.uu.uupaymentsdk.bean.RspInfo;
import com.uns.uu.uupaymentsdk.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by peixuan.xie on 2016/8/15.
 * 网络请求
 */
public class NetWorkRequest {
    /**
     * 获取卡片信息
     *
     * @param cardNo     卡号
     * @param subscriber 订阅
     */
    public static synchronized void cheakCard(String cardNo, Subscriber<CardBin> subscriber) {

        Map<String, String> map = new HashMap<>();
        RxUtils.unsubscribe();
        map.put("cardNo", cardNo);

        RxUtils.subscription = NetWork.getRequest_card_api().checkBin(map).subscribeOn(Schedulers
                .io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 绑定银行卡
     *
     * @param map        *
     * @param subscriber *
     */
    public static synchronized void bindCard(Map<String, String> map, Subscriber<RspInfo> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRegist_card_Api().bindCard(map).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 绑定信用卡
     *
     * @param map        *
     * @param subscriber *
     */
    public static synchronized void bindCreditCard(Map<String, String> map, Subscriber<RspInfo>
            subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRegist_card_Api().bindCreditCard(map).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 发短信
     *
     * @param map        *
     * @param subscriber *
     */
    public static synchronized void sendSms(Map<String, String> map, Subscriber<RspInfo> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRegist_card_Api().sendMsg(map).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 绑定信用卡发短信
     *
     * @param map        *
     * @param subscriber *
     */
    public static synchronized void bindCreditCardsendSms(Map<String, String> map,
                                                          Subscriber<RspInfo>
                                                                  subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRegist_card_Api().bindCreditCardSendMsg(map).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 检查银行卡是否已经绑定
     *
     * @param map
     * @param subscriber
     */
    public static synchronized void validCardNo(Map<String, String> map, Subscriber<RspInfo> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRegist_card_Api().validCardNo(map).subscribeOn
                (Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 旧卡验证
     *
     * @param map
     * @param subscriber
     */
    public static synchronized void pwdOldCardIdentifyCode(Map<String, String> map, Subscriber<RspInfo> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = NetWork.getRequest_card_api().pwdOldCardIdentifyCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}