package com.uns.uu.uupaymentsdk.network;

import com.uns.uu.uupaymentsdk.utils.MyLogger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peixuan.xie on 2016/8/11.
 * 网络请求接口
 */
public class NetWork {

    private static Request_card_Api request_card_api;
    private static Regist_card_Api regist_card_Api;
    private static MyLogger logger = MyLogger.kLog();

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static Request_card_Api getRequest_card_api() {
        if (request_card_api == null) {
            request_card_api = getRetrofit(Api.BASE_CARD_URL).create(Request_card_Api.class);
        }

        return request_card_api;
    }

    private static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(new LogInterceptor())
                        .build())
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();

    }

    /**
     * 银生宝环境
     *
     * @return
     */
    public static Regist_card_Api getRegist_card_Api() {
        if (regist_card_Api == null) {
            regist_card_Api = getRetrofit(Api.TEST_YSB_URL_OLD).create(Regist_card_Api.class);
        }
        return regist_card_Api;
    }



    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            HttpUrl url = response.request().url();
            logger.w(">>>>请求的Url  : " + url);
            if (response.body() != null) {
                ResponseBody body = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }
    }
}
