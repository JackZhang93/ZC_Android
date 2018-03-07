package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BaseBean
import com.uns.uu.uupaymentsdk.bean.BindBankCard
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_bindbankcard.*

/**
 * Created by zhaoyan on 2018/2/2.
 * 绑定银行卡界面
 */
class BindBankCardActivity : BaseActivity() {
    private lateinit var mData: BindBankCard
    private lateinit var mDialog: HintDialogUtils
    private lateinit var mBaseData: BaseBean
    private var isClick: Boolean = false //是否同意条款
    private var cardId: String = ""
    override fun getLayout(): Int {
        return R.layout.activity_bindbankcard
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("cardId")) {
            cardId = intent.getStringExtra("cardId")
        } else {
            throw NullPointerException("cardId is NULL")
        }
        if (intent.hasExtra("data")) {
            mBaseData = intent.getParcelableExtra("data")
            mData = BindBankCard().apply {
                merchantKey = mBaseData.merchantKey
                merchantId = mBaseData.merchantId
                customerId = mBaseData.customerId
            }
        } else {
            throw NullPointerException("data is NULL")
        }
        if (TextUtils.isEmpty(cardId)) {
            cardId = "6222620110028944586"
        }
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        //设置可以点击模式
        bind_bank_accord_info.movementMethod = LinkMovementMethod.getInstance()
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
        bind_bank_accord_info.text = spannableString
        //同意的点击事件
        bind_bank_accord.setOnClickListener {
            if (!isClick) {
                bind_bank_icon.setImageResource(R.mipmap.user_protocol_checked)
            } else {
                bind_bank_icon.setImageResource(R.mipmap.user_protocol_uncheck)
            }
            isClick = !isClick
            if (PatterUtils.Companion.matchPhone(bind_credit_card_phone_info.text.trim()) && isClick) {
                UnsViewUtils.nextViewOk(bind_bank_ok, true)
            } else {
                UnsViewUtils.nextViewOk(bind_bank_ok, false)
            }
        }
        //跳转到验证短信验证码界面
        bind_bank_ok.setOnClickListener {
            if (PatterUtils.Companion.matchPhone(bind_credit_card_phone_info.text.trim())) {
                GetCardInfoViewModel().validCardNo(CheckCard(cardId)).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {
                        val intent = Intent(baseContext, CheckSmsActivity::class.java)
                        intent.putExtra("phone", bind_credit_card_phone_info.text.trim().toString())
                        intent.putExtra("type", 1)
                        intent.putExtra("data", mData)
                        startActivity(intent)
                    } else {
                        ToastUtils.showToast(this@BindBankCardActivity, it?.rspMsg)
                    }

                })

            } else {
                Toast.makeText(baseContext, "请输入正确的手机号码", Toast.LENGTH_LONG).show()
            }
        }
        //监听手机号
        bind_credit_card_phone_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (PatterUtils.Companion.matchPhone(s?.trim().toString()) && isClick) {
                    UnsViewUtils.nextViewOk(bind_bank_ok, true)
                }
            }

        })

        UnsViewUtils.nextViewOk(bind_bank_ok, false)
        //银行卡预留手机号提示
        mDialog = HintDialogUtils(this@BindBankCardActivity, "预留手机号说明", arrayListOf("请咨询您的发卡机构是否提供支持" +
                "微度的卡片。", "没有预留、手机号忘记或者已停用" +
                "请联系银行客服更新处理。"))
        mDialog.setLeftOrRight(false, "", true, "")
        try {
            bind_bank_phone_hint.setOnClickListener {
                mDialog.showDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //获取卡片信息
        GetCardInfoViewModel().getCardInfo(cardId).observe(this, Observer {
            if (CardBinConstant.YES == it?.retCode) {
                bind_credit_card_type_info.text = "${it.data?.issName}  ${it.data?.cardTypeName}"
                mData.apply {
                    cardNo = cardId
                    bankCode = it.data.issuerCode
                    cardType = "1"
                }
            } else {

            }
        })
    }

    override fun initData() {
    }
}