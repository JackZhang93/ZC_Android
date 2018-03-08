package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.view.Gravity
import android.widget.Toast
import com.uns.uu.unstoast.UnsToast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BindBankCard
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.RefreshVerifyCode
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.utils.Utils
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.BindCardViewModel
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_check_sms.*

/**
 * Created by zhaoyan on 2018/2/2.
 * 输入验证码界面
 */
class CheckSmsActivity : BaseActivity() {
    private lateinit var mPhone: String             //手机号码
    private lateinit var merchantId: String         //商户号
    private lateinit var mDialog: HintDialogUtils   //提示框
    private var mType = -1                          //类型
    private lateinit var refresh: RefreshVerifyCode
    private lateinit var handler: Handler
    private var cardId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        mType = intent.getIntExtra("type", -1)
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_check_sms
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        mPhone = intent.getStringExtra("phone") ?: ""
        merchantId = intent.getStringExtra("merchantId") ?: ""
        @Suppress("RemoveCurlyBracesFromTemplate")
        bind_credit_info.text = "绑定银行卡需要短信确认，验证码已发送至\n" +
                "手机：${Utils.getTel(mPhone)}，请按提示操作。"
        mDialog = HintDialogUtils(this)
    }

    override fun initData() {
        handler = Handler()
        refresh = RefreshVerifyCode(check_sms_send, handler)
        //重新发送验证码
        check_sms_send.isClickable = false
        getVerifyCode()
        SendSmsViewModel().sendSmS(merchantId, mPhone).observe(this, Observer {

        })
        check_sms_send.setOnClickListener {
            check_sms_send.isClickable = false
            //发送短信
            getVerifyCode()
            SendSmsViewModel().sendSmS(merchantId, mPhone).observe(this, Observer {

            })
        }
        //获取手机验证码
        getVerifyCode()
        //点击下一步按钮
        check_sms_ok.setOnClickListener {
            if (mType == 2) {
                val bindCreditCard = intent.getParcelableExtra<BindCreditCard>("data")
                //设置验证码
                bindCreditCard.validCode = bind_credit_sms.text.toString().trim()
                //设置手机号
                bindCreditCard.phoneNo = mPhone
                //绑定信用卡
                BindCardViewModel().bindCreditCard(bindCreditCard).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {

                    } else {
                        ToastUtils.showToast(this@CheckSmsActivity, it?.rspMsg)
                    }
                })
            } else if (mType == 1) {
                val bindBankCard = intent.getParcelableExtra<BindBankCard>("data")
                //设置验证码
                bindBankCard.validCode = bind_credit_sms.text.toString().trim()
                //设置手机号
                bindBankCard.phoneNo = mPhone
                //绑定银行卡
                BindCardViewModel().bindBankCard(bindBankCard).observe(this, Observer {
                    if (Constant.REQ_SUCCESS == it?.rspCode) {

                    } else {
                        ToastUtils.showToast(this@CheckSmsActivity, it?.rspMsg)
                    }
                })
            }
        }

        //收不到验证码说明
        bind_credit_sms_qua.setOnClickListener {
            mDialog.apply {
                setTitle("收不到验证码")
                setContentArr(true, arrayListOf("请确认当前是否使用银行预留手机号。", "请检查短信是否被手机安全软件拦截。", "若预留手机号已停用，请联系银行客服咨询。"))
                setLeftOrRight(false, "", true, "")
            }.showDialog()
        }
        UnsViewUtils.nextViewOk(check_sms_ok, false)
        //监听验证码
        bind_credit_sms.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length ?: 0 >= 4) {
                    UnsViewUtils.nextViewOk(check_sms_ok, true)
                } else {
                    UnsViewUtils.nextViewOk(check_sms_ok, false)
                }
            }
        })
        //短信验证码
        bind_credit_sms.setOnClickListener {
            UnsToast(applicationContext).apply {
                setText("测试")
                duration = Toast.LENGTH_SHORT
                setGravity(Gravity.TOP, 0, 200)
            }.show()
        }
    }

    //获取手机验证码
    private fun getVerifyCode() {
        refresh.sure()
        refresh.setCount(60)
        handler.post(refresh)
    }
}