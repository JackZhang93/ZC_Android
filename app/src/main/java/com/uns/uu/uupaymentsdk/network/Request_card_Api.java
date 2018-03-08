package com.uns.uu.uupaymentsdk.network;


import com.uns.uu.uupaymentsdk.bean.CardBin;
import com.uns.uu.uupaymentsdk.bean.RspInfo;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by peixuan.xie on 2016/8/11.
 */
public interface Request_card_Api {
    //获取卡片信息
    @GET(Api.CARD_BIN)
    Observable<CardBin> checkBin(@QueryMap Map<String, String> map);
    //旧卡验证
    @FormUrlEncoded
    @POST(Api.PWD_OLD_CARD_IDENTIFY_CODE)
    Observable<RspInfo> pwdOldCardIdentifyCode(@FieldMap(encoded = true) Map<String, String> map);

}
