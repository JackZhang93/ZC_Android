package com.uns.uu.uupaymentsdk.network;


import com.uns.uu.uupaymentsdk.bean.RspInfo;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by peixuan.xie on 2016/8/11.
 */
public interface Regist_card_Api {
    //绑定银行卡
    @POST(Api.BIND_CARD)
    Observable<RspInfo> bindCard(@QueryMap Map<String, String> map);
    //绑定信用卡
    @POST(Api.BIN_CREDIT_CARD)
    Observable<RspInfo> bindCreditCard(@QueryMap Map<String, String> map);
    //发送验证码
    @POST(Api.REAL_NAME_SEND_SMS_CODE)
    Observable<RspInfo> sendMsg(@QueryMap Map<String, String> map);

    @POST(Api.VALID_CARD_NO)
    Observable<RspInfo> validCardNo(@QueryMap Map<String, String> map);
}
