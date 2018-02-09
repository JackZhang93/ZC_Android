package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
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
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_bindcreditbankcard.*

/**
 * Created by zhaoyan on 2018/1/31.
 * 绑定信用卡
 */
class BindCreditBankCardActivity : BaseActivity() {
    private var getCreditBankInfo: Boolean = false //获取到信用卡信息
    private lateinit var data: BindCreditCard
    override fun getLayout(): Int {
        return R.layout.activity_bindcreditbankcard
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

            Toast.makeText(baseContext, "同意icon改变", Toast.LENGTH_LONG).show()
        }
        bind_credit_ok.isClickable = getCreditBankInfo
        bind_credit_ok.setOnClickListener {
            if (PatterUtils.matchPhone(bind_credit_card_phone_info.text)) {

                GetCardInfoViewModel().validCardNo(CheckCard()).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {
                        val intent = Intent(baseContext, CheckSmsActivity::class.java)
                        intent.putExtra("phone", bind_credit_card_phone_info.text.trim().toString())
                        intent.putExtra("type", 2)
                        data.apply {
                            validTime = bind_credit_card_time_info.text.toString().trim()
                            cvv2 = bind_credit_card_cvv2_info.text.toString().trim()
                        }
                        intent.putExtra("data", data)
                        startActivity(intent)
                    } else {
                        ToastUtils.showToast(this@BindCreditBankCardActivity, it?.rspMsg)
                    }

                })

            } else {
                Toast.makeText(baseContext, "请输入正确的手机号码", Toast.LENGTH_LONG).show()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    override fun initData() {
        GetCardInfoViewModel().getCardInfo("6250861322900100").observe(this, Observer {
            if (CardBinConstant.YES == it?.retCode) {
                bind_credit_card_type_info.text = "${it.data?.issName}  ${it.data?.cardTypeName}"
                getCreditBankInfo = true
                bind_credit_ok.isClickable = getCreditBankInfo
                data = BindCreditCard().apply {
                    cardNo = "6222620110028944586"
                    bankCode = it.data.issuerCode
                    cardType = "2"
                }
            } else {

            }
        })
    }
}