package com.uns.uu.uupaymentsdk.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.util.ArrayMap
import com.uns.uu.uupaymentsdk.bean.CardBin
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.bean.PwdOldCard
import com.uns.uu.uupaymentsdk.bean.RspInfo
import com.uns.uu.uupaymentsdk.network.MySubscriber
import com.uns.uu.uupaymentsdk.network.NetWorkRequest
import com.uns.uu.uupaymentsdk.utils.MD5
import com.uns.uu.uupaymentsdk.utils.MyLogger
import com.uns.uu.uupaymentsdk.utils.Utils

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
        Utils.getThread {
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
        }.start()
        return data
    }

    /**
     * 检查银行卡是否已经绑定
     * @param bean 参数
     */
    fun validCardNo(bean: CheckCard): MutableLiveData<RspInfo> {
        val data = MutableLiveData<RspInfo>()
        Utils.getThread {
            val arrayMap = ArrayMap<String, String>()
            arrayMap["merchantId"] = bean.merchantId
            arrayMap["customerId"] = bean.customerId
            arrayMap["cardNo"] = bean.cardNo
            arrayMap["accountType"] = bean.accountType
            val mac = StringBuffer().append("merchantId=").append(bean
                    .merchantId).append("&customerId=").append(bean.customerId).append("&cardNo=")
                    .append(bean.cardNo).append("&merchantKey=").append(bean.merchantKey)
            MyLogger.kLog().d(String.format("mac 是  %s", mac))
            arrayMap["mac"] = MD5.getMD5ofStr(mac.toString())
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
        }.start()
        return data
    }

    /**
     * 旧卡验证
     * @param bean 参数
     */
    fun pwdOldCardIdentifyCode(bean: PwdOldCard): MutableLiveData<RspInfo> {
        val data = MutableLiveData<RspInfo>()
        Utils.getThread {
            val arrayMap = ArrayMap<String, String>()
            arrayMap["merchantId"] = bean.merchantId
            arrayMap["customerId"] = bean.customerId
            arrayMap["bankCardId"] = bean.bankCardId
            arrayMap["cardNo"] = bean.cardNo
            arrayMap["cardType"] = bean.cardType
            arrayMap["phoneNo"] = bean.phoneNo
            arrayMap["validCode"] = bean.validCode
            arrayMap["idCode"] = bean.idCode
            arrayMap["name"] = bean.name
            val mac = StringBuffer()
            mac.append("merchantId=").append(bean.merchantId)
            mac.append("&customerId=").append(bean.merchantId)
            mac.append("&bankCardId=").append(bean.bankCardId)
            mac.append("&cardNo=").append(bean.cardNo)
            mac.append("&cardType=").append(bean.cardType)
            mac.append("&phoneNo=").append(bean.phoneNo)
            mac.append("&validCode=").append(bean.validCode)
            mac.append("&idCode=").append(bean.idCode)
            mac.append("&name=").append(bean.name)
            mac.append("&merchantKey=").append(bean.merchantKey)
            MyLogger.kLog().d(String.format("mac 是  %s", mac))
            arrayMap["mac"] = MD5.getMD5ofStr(mac.toString())
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
        }.start()
        return data
    }

}