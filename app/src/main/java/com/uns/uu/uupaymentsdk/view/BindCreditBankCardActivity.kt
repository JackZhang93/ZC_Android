package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bigkoo.pickerview.TimePickerView
import com.bigkoo.pickerview.utils.MyDatePickReversePup
import com.uns.uu.unstoast.UnsToast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BaseBean
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.HideKey
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_bindcreditbankcard.*
import java.util.*

/**
 * Created by zhaoyan on 2018/1/31.
 * 绑定信用卡
 */
class BindCreditBankCardActivity : BaseActivity() {
    private var mGetCreditBankInfo: Boolean = false //获取到信用卡信息
    private lateinit var mData: BindCreditCard
    private lateinit var mDialog: HintDialogUtils
    private lateinit var mBaseData: BaseBean
    private var hasDate: Boolean = false //有效期
    private var hasCvv2: Boolean = false //cvv2
    private var isClick: Boolean = false //是否同意条款
    private val endDay = 4701859200000L
    private lateinit var pup: MyDatePickReversePup
    private var cardId: String = ""
    private var mValidTime: String = "" //信用卡到期时间
    override fun getLayout(): Int {
        return R.layout.activity_bindcreditbankcard
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("cardId")) {
            cardId = intent.getStringExtra("cardId")
        } else {
            throw NullPointerException("cardId is NULL")
        }

        if (intent.hasExtra("data")) {
            mBaseData = intent.getParcelableExtra("data")
            mData = BindCreditCard().apply {
                merchantId = mBaseData.merchantId
                customerId = mBaseData.customerId
                merchantId = mBaseData.merchantKey
            }
        } else {
            throw NullPointerException("data is NULL")
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
                bind_credit_icon.setImageResource(R.mipmap.user_protocol_checked)
            } else {
                bind_credit_icon.setImageResource(R.mipmap.user_protocol_uncheck)
            }
            isClick = !isClick
            check()
        }
        //选择有效期
        bind_credit_card_time_info.setOnClickListener {
            HideKey.hideSoftKeyboard(this@BindCreditBankCardActivity)
            pup.showPop(it)

        }
        //填写cvv2
        bind_credit_card_cvv2_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                hasCvv2 = s?.length ?: 0 >= 3
                check()
            }
        })
        UnsViewUtils.nextViewOk(bind_credit_ok, false)
        //下一步
        bind_credit_ok.setOnClickListener {
            //检查卡片是否绑定
            GetCardInfoViewModel().validCardNo(CheckCard(cardId)).observe(this, Observer {
                if (Constant.REQ_SUCCESS == it?.rspCode) {
                    val intent = Intent(baseContext, CheckSmsActivity::class.java)
                    intent.putExtra("phone", bind_credit_card_phone_info.text.trim().toString())
                    intent.putExtra("type", 2)
                    mData.apply {
                        validTime = mValidTime
                        cvv2 = bind_credit_card_cvv2_info.text.toString().trim()
                    }
                    intent.putExtra("data", mData)
                    startActivity(intent)
                } else {
                    ToastUtils.showToast(this@BindCreditBankCardActivity, it?.rspMsg)
                }

            })
        }
        //检查手机号
        bind_credit_card_phone_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                check()
            }
        })


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
        //信用卡有效期
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

    //设置下一步按钮是否可以点击
    private fun check() {
        if (mGetCreditBankInfo && hasDate && hasCvv2 && isClick && PatterUtils.matchPhone
                (bind_credit_card_phone_info.text)) {
            UnsViewUtils.nextViewOk(bind_credit_ok, true)
        } else {
            UnsViewUtils.nextViewOk(bind_credit_ok, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initPup() {
        pup = MyDatePickReversePup(this@BindCreditBankCardActivity, TimePickerView.Type
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
        //获取信用卡信息
        GetCardInfoViewModel().getCardInfo(cardId).observe(this, Observer {
            when {
            //支持该卡片
                CardBinConstant.YES == it?.retCode -> {
                    bind_credit_card_type_info.text = "${it.data?.issName}  ${it.data?.cardTypeName}"
                    mGetCreditBankInfo = true
                    check()
                    mData = BindCreditCard().apply {
                        cardNo = cardId
                        bankCode = it.data.issuerCode
                        cardType = "2"
                    }
                }
            //不支持该卡片
                CardBinConstant.NO == it?.retCode -> {
                    UnsToast(baseContext).apply {
                        setText("暂不支持该卡片！")
                        setGravity(Gravity.CENTER,
                                0,
                                0)
                    }.show()
                }
                else -> {
                    UnsToast(baseContext).apply {
                        setText("网络超时，请稍后重试!")
                        setGravity(Gravity.CENTER,
                                0,
                                0)
                    }.show()
                }
            }
        })
    }
}