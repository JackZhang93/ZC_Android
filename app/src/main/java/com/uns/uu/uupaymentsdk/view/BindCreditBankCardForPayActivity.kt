package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_bindcreditbankcardforpay.*

/**
 * Created by zhaoyan on 2018/1/31.
 * 绑定信用卡
 */
class BindCreditBankCardForPayActivity : BaseActivity() {
    private lateinit var mData: BindCreditCard
    private var isClick: Boolean = false //是否同意条款
    private var hasDate: Boolean = false //有效期
    private var hasCvv2: Boolean = false //cvv2
    override fun getLayout(): Int {
        return R.layout.activity_bindcreditbankcardforpay
    }

    override fun initView() {
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
            hasDate=true
            check()
        }

        //cvv2
        bind_credit_card_cvv2_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                hasCvv2 = s?.length ?: 0 >= 4
                check()
            }
        })
        UnsViewUtils.nextViewOk(bind_credit_ok, false)
        bind_credit_ok.setOnClickListener {
            GetCardInfoViewModel().validCardNo(CheckCard()).observe(this, Observer {
                if (Constant.REQ_SUCCESS == it?.rspCode) {
                    val intent = Intent(baseContext, CheckSmsActivity::class.java)
//                        intent.putExtra("phone", bind_credit_card_phone_info.text.trim().toString())
                    intent.putExtra("type", 2)
                    mData.apply {
                        validTime = bind_credit_card_time_info.text.toString().trim()
                        cvv2 = bind_credit_card_cvv2_info.text.toString().trim()
                    }
                    intent.putExtra("data", mData)
                    startActivity(intent)
                } else {
                    ToastUtils.showToast(this@BindCreditBankCardForPayActivity, it?.rspMsg)
                }

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
    override fun initData() {
    }
}