package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.bigkoo.pickerview.TimePickerView
import com.bigkoo.pickerview.utils.MyDatePickReversePup
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.PaySmsBean
import com.uns.uu.uupaymentsdk.utils.HideKey
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_bindcreditbankcardforpay.*
import java.util.*

/**
 * Created by zhaoyan on 2018/1/31.
 * 绑定信用卡
 */
class BindCreditBankCardForPayActivity : BaseActivity() {
    private lateinit var mData: PaySmsBean
    private var isClick: Boolean = false //是否同意条款
    private var hasDate: Boolean = false //有效期
    private var hasCvv2: Boolean = false //cvv2
    private val endDay = 4701859200000L
    private lateinit var pup: MyDatePickReversePup
    private var mValidTime: String = "" //信用卡到期时间
    override fun getLayout(): Int {
        return R.layout.activity_bindcreditbankcardforpay
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("data")) {
            mData = intent.getParcelableExtra("data")
            mData.amount = "0.01"
            mData.bankcardId = "127"
            mData.userId = mData.customerId
            mData.purpose = "充值"
        }
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        initPup()
        //设置可以点击模式
        bind_credit_accord_info.movementMethod = LinkMovementMethod.getInstance()
        val spannableString = SpannableString("同意《用户协议》。")
        //添加span
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View?) {
                //点击时间处理
                Toast.makeText(p0?.context, "《用户协议》", Toast.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint?) {
                super.updateDrawState(ds)
                //去除下划线
                ds?.isUnderlineText = false
                //设置span颜色
                @Suppress("DEPRECATION")
                ds?.color = resources.getColor(R.color.colorPrimary)
            }
        }, 2, spannableString.length - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //设置span到textView
        bind_credit_accord_info.text = spannableString
        //同意的点击事件
        bind_credit_accord.setOnClickListener {
            if (!isClick) {
                bind_credit_card_icon.setImageResource(R.mipmap.user_protocol_checked)
            } else {
                bind_credit_card_icon.setImageResource(R.mipmap.user_protocol_uncheck)
            }
            isClick = !isClick
            check()
        }

        //有效期
        bind_credit_card_time_info.setOnClickListener {
            HideKey.hideSoftKeyboard(this@BindCreditBankCardForPayActivity)
            pup.showPop(it)
        }

        //cvv2
        bind_credit_card_cvv2_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                hasCvv2 = s?.length ?: 0 >= 3
                check()
            }
        })
        UnsViewUtils.nextViewOk(bind_credit_ok, false)
        bind_credit_ok.setOnClickListener {
            //发送支付验证码
            mData.apply {
                validTime=mValidTime
                cvv2 = bind_credit_card_cvv2_info.text.toString().trim()
            }
            SendSmsViewModel().sendPaySmS(mData).observe(this, android.arch.lifecycle.Observer {

            })

        }
    }

    private fun check() {
        if (hasDate && hasCvv2 && isClick) {
            UnsViewUtils.nextViewOk(bind_credit_ok, true)
        } else {
            UnsViewUtils.nextViewOk(bind_credit_ok, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initPup() {
        pup = MyDatePickReversePup(this@BindCreditBankCardForPayActivity, TimePickerView.Type
                .YEAR_MONTH, 0L, endDay, System.currentTimeMillis(), MyDatePickReversePup.OnSureClick {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            //月份小于两位
            if (month < 10) {
                mValidTime = "0$month${year % 1000}"
                bind_credit_card_time_info.text = "0$month/$year"
            } else {
                mValidTime = "$month${year % 1000}"
                bind_credit_card_time_info.text = "$month/$year"
            }
            hasDate = true
            check()
        })
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
    }
}