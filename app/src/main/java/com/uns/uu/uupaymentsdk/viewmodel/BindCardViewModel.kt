package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import com.uns.uu.uupaymentsdk.bean.BindBankCard
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.bean.RspInfo
import com.uns.uu.uupaymentsdk.network.MySubscriber
import com.uns.uu.uupaymentsdk.network.NetWorkRequest
import com.uns.uu.uupaymentsdk.utils.MD5
import com.uns.uu.uupaymentsdk.utils.MyLogger

/**
 * Created by zhaoyan on 2018/2/8.
 * 绑卡
 */
class BindCardViewModel : ViewModel() {
    /**
     *绑定信用卡
     * @param data 信息
     */
    fun bindCreditCard(data: BindCreditCard): MutableLiveData<RspInfo> {
        val liveData = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap["merchantId"] = data.merchantId
        arrayMap["customerId"] = data.customerId
        arrayMap["cardNo"] = data.cardNo
        arrayMap["cardType"] = data.cardType
        arrayMap["bankCode"] = data.bankCode
        arrayMap["phoneNo"] = data.phoneNo
        arrayMap["validCode"] = data.validCode
        arrayMap["validTime"] = data.validTime
        arrayMap["cvv2"] = data.cvv2
        val buffer = StringBuffer().append("merchantId=")
                .append(data.merchantId)
                .append("&customerId=")
                .append(data.customerId)
                .append("&cardNo=")
                .append(data.cardNo)
                .append("&cardType=")
                .append(data.cardType)
                .append("&bankCode=")
                .append(data.bankCode)
                .append("&phoneNo=")
                .append(data.phoneNo)
                .append("&validCode=")
                .append(data.validCode)
                .append("&validTime=")
                .append(data.validTime)
                .append("&cvv2=")
                .append(data.cvv2)
                .append("&merchantKey=")
                .append(data.merchantKey)
        MyLogger.kLog().d(String.format("mac 是  %s", buffer.toString()))
        arrayMap["mac"] = MD5.getMD5ofStr(buffer.toString())
        NetWorkRequest.bindCreditCard(arrayMap, object : MySubscriber<RspInfo?>() {
            override fun onNext(t: RspInfo?) {
                logger.d(t)
                liveData.postValue(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                liveData.postValue(RspInfo())
            }
        })
        return liveData
    }


    /**
     *绑定银行卡
     * @param data 信息
     */
    fun bindBankCard(data: BindBankCard): MutableLiveData<RspInfo> {
        val liveData = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap["merchantId"] = data.merchantId
        arrayMap["customerId"] = data.customerId
        arrayMap["cardNo"] = data.cardNo
        arrayMap["cardType"] = data.cardType
        arrayMap["bankCode"] = data.bankCode
        arrayMap["phoneNo"] = data.phoneNo
        arrayMap["validCode"] = data.validCode
        arrayMap["accountType"] = data.accountType
        val buffer = StringBuffer().append("merchantId=").append(data.merchantId)
                .append("&customerId=").append(data.customerId).append("&cardNo=")
                .append(data.cardNo).append("&cardType=").append(data.cardType)
                .append("&bankCode=").append(data.bankCode).append("&phoneNo=")
                .append(data.phoneNo).append("&validCode=").append(data.validCode)
                .append("&merchantKey=").append(data.merchantKey)
        MyLogger.kLog().d(String.format("mac 是  %s", buffer.toString()))
        arrayMap["mac"] = MD5.getMD5ofStr(buffer.toString())
        NetWorkRequest.bindCard(arrayMap, object : MySubscriber<RspInfo?>() {
            override fun onNext(t: RspInfo?) {
                logger.d(t)
                liveData.postValue(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                liveData.postValue(RspInfo())
            }
        })
        return liveData
    }
}