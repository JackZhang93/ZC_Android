package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import com.uns.uu.uupaymentsdk.bean.RspInfo
import com.uns.uu.uupaymentsdk.network.MySubscriber
import com.uns.uu.uupaymentsdk.network.NetWorkRequest
import com.uns.uu.uupaymentsdk.utils.MD5
import com.uns.uu.uupaymentsdk.utils.MyLogger

/**
 * Created by zhaoyan on 2018/2/7.
 * 获取短信验证码
 */
class SendSmsViewModel : ViewModel() {

    /**
     * 获取短信验证码
     * @param merchantId 商户id
     * @param phoneNo  手机号码
     */
    fun sendSmS(merchantId: String, phoneNo: String): MutableLiveData<RspInfo> {
        val mutableLiveData = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap.put("merchantId", merchantId)
        arrayMap.put("phoneNo", phoneNo)
        val stringBuffer = StringBuffer()
        stringBuffer.append("merchantId=").append(merchantId)
                .append("&phoneNo=").append(phoneNo).append("&merchantKey=")
                .append("kb201610171300#!!!")
        MyLogger.kLog().d(stringBuffer.toString())
        arrayMap.put("mac", MD5.getMD5ofStr(stringBuffer.toString()))
        NetWorkRequest.sendSms(arrayMap, object : MySubscriber<RspInfo?>() {
            override fun onNext(t: RspInfo?) {
                logger.d(t.toString())
                mutableLiveData.postValue(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                mutableLiveData.postValue(RspInfo().apply {

                })
            }
        })
        return mutableLiveData

    }
}