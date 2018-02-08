package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import com.uns.uu.uupaymentsdk.bean.CardBin
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.bean.RspInfo
import com.uns.uu.uupaymentsdk.network.MySubscriber
import com.uns.uu.uupaymentsdk.network.NetWorkRequest
import com.uns.uu.uupaymentsdk.utils.MD5
import com.uns.uu.uupaymentsdk.utils.MyLogger

/**
 * Created by zhaoyan on 2018/2/6.
 * 获取银行卡信息
 */
class GetCardInfoViewModel : ViewModel() {
    /**
     * 获取卡片信息
     * @param cardId 银行卡卡号
     */
    fun getCardInfo(cardId: String): MutableLiveData<CardBin> {
        val data = MutableLiveData<CardBin>()
        NetWorkRequest.cheakCard(cardId, object : MySubscriber<CardBin>() {
            override fun onNext(t: CardBin?) {
                logger.d(t?.toString())
                data.postValue(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                data.postValue(CardBin())
            }

        })
        return data
    }

    /**
     * 检查银行卡是否已经绑定
     * @param bean 参数
     */
    fun validCardNo(bean: CheckCard): MutableLiveData<RspInfo> {
        val data = MutableLiveData<RspInfo>()
        val arrayMap = ArrayMap<String, String>()
        arrayMap.put("merchantId", bean.merchantId)
        arrayMap.put("customerId", bean.customerId)
        arrayMap.put("cardNo", bean.cardNo)
        arrayMap.put("accountType", bean.accountType)
        val mac = StringBuffer().append("merchantId=").append(bean
                .merchantId).append("&customerId=").append(bean.customerId).append("&cardNo=")
                .append(bean.cardNo).append("&merchantKey=").append(bean.merchantKey)
        MyLogger.kLog().d(mac)
        arrayMap.put("mac", MD5.getMD5ofStr(mac.toString()))
        NetWorkRequest.validCardNo(arrayMap, object : MySubscriber<RspInfo?>() {
            override fun onNext(t: RspInfo?) {
                logger.d(t?.toString())
                data.postValue(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                data.postValue(RspInfo())
            }
        })
        return data
    }
}