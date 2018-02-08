package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BindBankCard
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.viewmodel.BindCardViewModel
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_check_sms.*

/**
 * Created by zhaoyan on 2018/2/2.
 * 输入验证码界面
 */
class CheckSmsActivity : BaseActivity() {
    private lateinit var phone: String
    private var type = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = intent.getIntExtra("type", -1)
    }

    override fun getLayout(): Int {
        return R.layout.activity_check_sms
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        phone = intent.getStringExtra("phone") ?: ""
        @Suppress("RemoveCurlyBracesFromTemplate")
        bind_credit_info.text = "绑定银行卡需要短信确认，验证码已发送至\n" +
                "手机：${phone}，请按提示操作。"
    }

    override fun initData() {
        check_sms_send.setOnClickListener {
            check_sms_send.isClickable = false
            //发送短信
            SendSmsViewModel().sendSmS("1120140210111823001", phone).observe(this, Observer {

            })
        }
        check_sms_ok.setOnClickListener {
            if (type == 2) {
                val bindCreditCard = intent.getParcelableExtra<BindCreditCard>("data")
                //设置验证码
                bindCreditCard.validCode = bind_credit_sms.text.toString().trim()
                //设置手机号
                bindCreditCard.phoneNo = phone
                //邦定信用卡
                BindCardViewModel().bindCreditCard(bindCreditCard).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {

                    } else {
                        ToastUtils.showToast(this@CheckSmsActivity, it?.rspMsg)
                    }
                })
            } else if (type == 1) {
                val bindBankCard = intent.getParcelableExtra<BindBankCard>("data")
                //设置验证码
                bindBankCard.validCode = bind_credit_sms.text.toString().trim()
                //设置手机号
                bindBankCard.phoneNo = phone
                //绑定银行卡
                BindCardViewModel().bindBankCard(bindBankCard).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {

                    } else {
                        ToastUtils.showToast(this@CheckSmsActivity, it?.rspMsg)
                    }
                })
            }
        }
    }
}