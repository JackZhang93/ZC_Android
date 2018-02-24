package com.uns.uu.uupaymentsdk.view

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
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
    private var bankCard: String = "" //银行卡号
    private var canClick: Boolean = false

    override fun getLayout(): Int {
        return R.layout.activity_input_bind_card_four_step
    }

    override fun initView() {
        bankCard = intent.getStringExtra("bankCard")
        tv_card_num.text = "建设银行储蓄卡（6442）"
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

        tv_new_reset_pwd.setOnClickListener {//跳转到ChooseResetPwdActivity
            ToastUtils.showToast(baseContext, "换个方式重置密码")
        }

    }

}