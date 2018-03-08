package com.uns.uu.uupaymentsdk.view

import android.content.ComponentName
import android.content.Intent
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_input_bind_card_four_step.*

/**
 * author: 张承
 * time：2018/2/23
 * des：忘记支付密码填写银行卡信息界面(四要素)
 */
class InputBindCardInfoFourStepActivity : BaseActivity() {

    private var isChecked: Boolean = true //用户协议是否被勾选
    private var cardId: String = "" //银行卡号
    private var canClick: Boolean = false

    override fun getLayout(): Int {
        return R.layout.activity_input_bind_card_four_step
    }

    override fun initView() {
        setTitle("验证身份")
        cardId = intent.getStringExtra("cardId")

        tv_card_num.text = StringBuffer().append(intent.getStringExtra("bankName"))
                .append(if (intent.getIntExtra("cardType",1) == 1) "借记卡" else "信用卡").append("（")
                .append(intent.getStringExtra("cardMessage")).append("）").toString()
    }

    override fun initData() {
        bind_bank_card.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                iv_check_protocol.setImageResource(R.mipmap.user_protocol_checked)
            } else {
                iv_check_protocol.setImageResource(R.mipmap.user_protocol_uncheck)
            }
        }

        tv_user_protocol.setOnClickListener {
            ToastUtils.showToast(baseContext, "用户协议")
        }

        tv_next.setOnClickListener {
            if (canClick) {
                if (isChecked) {
                    if (PatterUtils.matchPhone(et_phone.text.trim())) {
                        val intent = Intent(baseContext, ResetPwdCheckCodeActivity::class.java)
                        intent.putExtra("mobile", et_phone.text.toString().trim())
                        startActivity(intent)
                    } else {
                        ToastUtils.showToast(this@InputBindCardInfoFourStepActivity, "请输入正确手机号")
                    }
                } else {
                    ToastUtils.showToast(baseContext, "您必须同意此协议才可修改密码")
                }
            }
        }

        tv_new_reset_pwd.setOnClickListener {//跳转到com.uns.uu.ui.money.activity.ChooseResetPwdActivity
            val intent = Intent()
            val componentName = ComponentName(packageName,"com.uns.uu.ui.money.activity.ChooseResetPwdActivity")
            intent.component = componentName
            startActivity(intent)
            finish()
        }

    }

}