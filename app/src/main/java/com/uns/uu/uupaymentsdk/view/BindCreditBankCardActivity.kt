package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_bindcreditbankcard.*

/**
 * Created by zhaoyan on 2018/1/31.
 * 绑定信用卡
 */
class BindCreditBankCardActivity : BaseActivity() {
    private var mGetCreditBankInfo: Boolean = false //获取到信用卡信息
    private lateinit var mData: BindCreditCard
    private lateinit var mDialog: HintDialogUtils
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
        bind_credit_ok.isClickable = mGetCreditBankInfo
        bind_credit_ok.setOnClickListener {
            if (PatterUtils.matchPhone(bind_credit_card_phone_info.text)) {

                GetCardInfoViewModel().validCardNo(CheckCard()).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {
                        val intent = Intent(baseContext, CheckSmsActivity::class.java)
                        intent.putExtra("phone", bind_credit_card_phone_info.text.trim().toString())
                        intent.putExtra("type", 2)
                        mData.apply {
                            validTime = bind_credit_card_time_info.text.toString().trim()
                            cvv2 = bind_credit_card_cvv2_info.text.toString().trim()
                        }
                        intent.putExtra("data", mData)
                        startActivity(intent)
                    } else {
                        ToastUtils.showToast(this@BindCreditBankCardActivity, it?.rspMsg)
                    }

                })

            } else {
                Toast.makeText(baseContext, "请输入正确的手机号码", Toast.LENGTH_LONG).show()
            }
        }
        mDialog = HintDialogUtils(this@BindCreditBankCardActivity)
        //信用卡有效期说明提示
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(resources.getDimensionPixelSize(R.dimen
                .dimens_124), resources.getDimensionPixelSize(R.dimen
                .dimens_77)).apply {
            gravity = Gravity.CENTER
        }
        imageView.setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.dimens_11))
        imageView.setImageResource(R.mipmap.ic_launcher)
        bind_credit_card_time_info_icon.setOnClickListener {
            mDialog.setTitle("有效期说明")
            mDialog.setSelfView(true, arrayOf("有效期是打印在信用卡正面卡号下方，标准格式为月份在前，年份在后的一串数字。"), imageView, false)
            mDialog.setLeftOrRight(false, "", true, "")
            mDialog.showDialog()
        }
        //cvv2说明提示
        bind_credit_card_cvv2_info_icon.setOnClickListener {
            mDialog.setTitle("CVV2说明")
            mDialog.setSelfView(true, arrayOf("CVV2是信用卡背面后三位数字"), imageView, false)
            mDialog.setLeftOrRight(false, "", true, "")
            mDialog.showDialog()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        GetCardInfoViewModel().getCardInfo("6250861322900100").observe(this, Observer {
            if (CardBinConstant.YES == it?.retCode) {
                bind_credit_card_type_info.text = "${it.data?.issName}  ${it.data?.cardTypeName}"
                mGetCreditBankInfo = true
                bind_credit_ok.isClickable = mGetCreditBankInfo
                mData = BindCreditCard().apply {
                    cardNo = "6250861322900100"
                    bankCode = it.data.issuerCode
                    cardType = "1"
                }
            } else {

            }
        })
    }
}