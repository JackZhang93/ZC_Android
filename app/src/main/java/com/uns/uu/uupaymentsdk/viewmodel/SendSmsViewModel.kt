package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import com.uns.uu.uupaymentsdk.bean.PaySmsBean
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


    /**
     * 获取支付的短信验证码
     * @param merchantId 商户id
     * @param phoneNo  手机号码
     */
    fun sendPaySmS(paySmsBean: PaySmsBean): MutableLiveData<RspInfo> {
        val mutableLiveData = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap.put("merchantId", paySmsBean.merchantId)
        arrayMap.put("userId", paySmsBean.userId)
        arrayMap.put("customerId", paySmsBean.customerId)
        arrayMap.put("orderId", paySmsBean.orderId)
        arrayMap.put("orderTime", paySmsBean.orderTime)
        arrayMap.put("curType", paySmsBean.curType)
        arrayMap.put("purpose", paySmsBean.purpose)
        arrayMap.put("amount", paySmsBean.amount)
        arrayMap.put("responseUrl", paySmsBean.responseUrl)
        arrayMap.put("token", paySmsBean.token)
        arrayMap.put("name", paySmsBean.name)
        arrayMap.put("idNum", paySmsBean.idNum)
        arrayMap.put("cardNo", paySmsBean.cardNo)
        arrayMap.put("bankcardId", paySmsBean.bankcardId)
        arrayMap.put("mobilePhoneNum", paySmsBean.mobilePhoneNum)
        arrayMap.put("validTime", paySmsBean.validTime)
        arrayMap.put("cvv2", paySmsBean.cvv2)
//        arrayMap.put("key", paySmsBean.key)
        val stringBuffer = StringBuffer()
        stringBuffer.append("merchantId=")
                .append(paySmsBean.merchantId)
                .append("&userId=")
                .append(paySmsBean.userId)
                .append("&customerId=")
                .append(paySmsBean.customerId)
                .append("&orderId=")
                .append(paySmsBean.orderId)
                .append("&orderTime=")
                .append(paySmsBean.orderTime)
                .append("&curType=")
                .append(paySmsBean.curType)
                .append("&purpose=")
                .append(paySmsBean.purpose)
                .append("&amount=")
                .append(paySmsBean.amount)
                .append("&responseUrl=")
                .append("&token=")
                .append("&name=")
                .append("&idNum=")
                .append("&cardNo=")
                .append("&bankcardId=")
                .append(paySmsBean.bankcardId)
                .append("&mobilePhoneNum=")
                .append("&validTime=")
                .append(paySmsBean.validTime)
                .append("&cvv2=")
                .append(paySmsBean.cvv2)
                .append("&key=")
                .append(paySmsBean.key)
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