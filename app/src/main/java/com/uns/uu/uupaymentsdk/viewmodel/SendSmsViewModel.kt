package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import android.text.TextUtils
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
        arrayMap["merchantId"] = merchantId
        arrayMap["phoneNo"] = phoneNo
        val stringBuffer = StringBuffer()
        stringBuffer.append("merchantId=").append(merchantId)
                .append("&phoneNo=").append(phoneNo).append("&merchantKey=")
                .append("kb201610171300#!!!")
        MyLogger.kLog().d(stringBuffer.toString())
        arrayMap["mac"] = MD5.getMD5ofStr(stringBuffer.toString())
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
     * @param paySmsBean 支付
     */
    fun sendPaySmS(paySmsBean: PaySmsBean): MutableLiveData<RspInfo> {
        val mutableLiveData = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap["merchantId"] = paySmsBean.merchantId
        arrayMap["userId"] = paySmsBean.userId
        arrayMap["customerId"] = paySmsBean.customerId
        arrayMap["orderId"] = paySmsBean.orderId
        arrayMap["orderTime"] = paySmsBean.orderTime
        arrayMap["curType"] = paySmsBean.curType
        arrayMap["purpose"] = paySmsBean.purpose
        arrayMap["amount"] = paySmsBean.amount
        if (!TextUtils.isEmpty(paySmsBean.responseUrl)) {
            arrayMap["responseUrl"] = paySmsBean.responseUrl
        }else{
            arrayMap["responseUrl"]=""
        }
        arrayMap["token"] = ""
        arrayMap["name"] = ""
        arrayMap["idNum"] = ""
        arrayMap["cardNo"] = ""
        arrayMap["remark"] = "remark"
        arrayMap["bankcardId"] = paySmsBean.bankcardId
        if (!TextUtils.isEmpty(paySmsBean.mobilePhoneNum)) {
            arrayMap["mobilePhoneNum"] = paySmsBean.mobilePhoneNum
        }else{
            arrayMap["mobilePhoneNum"]=""
        }
        arrayMap["validTime"] = paySmsBean.validTime
        arrayMap["cvv2"] = paySmsBean.cvv2
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
                .append("")
                .append("&token=")
                .append("")
                .append("&name=")
                .append("")
                .append("&idNum=")
                .append("")
                .append("&cardNo=")
                .append("")
                .append("&bankcardId=")
                .append(paySmsBean.bankcardId)
                .append("&mobilePhoneNum=")
                .append("")
                .append("&validTime=")
                .append(paySmsBean.validTime)
                .append("&cvv2=")
                .append(paySmsBean.cvv2)
                .append("&key=")
                .append(paySmsBean.key)
        MyLogger.kLog().d(String.format("mac 是  %s", stringBuffer.toString()))
        arrayMap["mac"] = MD5.getMD5ofStr(stringBuffer.toString())
        NetWorkRequest.bindCreditCardsendSms(arrayMap, object : MySubscriber<RspInfo?>() {
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