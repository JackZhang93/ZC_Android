package com.uns.uu.uupaymentsdk.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.uns.uu.unstoast.UnsToast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BindBankCard
import com.uns.uu.uupaymentsdk.bean.BindCreditCard
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.viewmodel.BindCardViewModel
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_check_sms.*

/**
 * Created by zhaoyan on 2018/2/2.
 * 输入验证码界面
 */
class CheckSmsActivity : BaseActivity() {
    private lateinit var mPhone: String             //手机号码
    private lateinit var mDialog: HintDialogUtils   //提示框
    private var mType = -1                          //类型
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mType = intent.getIntExtra("type", -1)
    }

    override fun getLayout(): Int {
        return R.layout.activity_check_sms
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        mPhone = intent.getStringExtra("phone") ?: ""
        @Suppress("RemoveCurlyBracesFromTemplate")
        bind_credit_info.text = "绑定银行卡需要短信确认，验证码已发送至\n" +
                "手机：${mPhone}，请按提示操作。"
        mDialog = HintDialogUtils(this)
//        bind_credit_sms.inputType = InputType.TYPE_NULL
    }

    override fun initData() {
        check_sms_send.setOnClickListener {
            check_sms_send.isClickable = false
            //发送短信
            SendSmsViewModel().sendSmS("1120140210111823001", mPhone).observe(this, Observer {

            })
        }
        check_sms_ok.setOnClickListener {
            if (mType == 2) {
                val bindCreditCard = intent.getParcelableExtra<BindCreditCard>("data")
                //设置验证码
                bindCreditCard.validCode = bind_credit_sms.text.toString().trim()
                //设置手机号
                bindCreditCard.phoneNo = mPhone
                //邦定信用卡
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

        bind_credit_sms.setOnClickListener {
            UnsToast(applicationContext).apply {
                setText("测试")
                duration=Toast.LENGTH_SHORT
                setGravity(Gravity.TOP,0,200)
                show()
            }
        }
    }

    override fun onStop() {
//        UnsKeyBoardUtils.shared(this@CheckSmsActivity, bind_credit_sms)?.hideKeyboard()
        super.onStop()
    }
}