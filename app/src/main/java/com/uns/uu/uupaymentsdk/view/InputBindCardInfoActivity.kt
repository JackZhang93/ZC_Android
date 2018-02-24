package com.uns.uu.uupaymentsdk.view

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_input_bind_card.*

/**
 * author: 张承
 * time：2018/2/23
 * des：忘记支付密码填写银行卡信息界面(手机号)
 */
class InputBindCardInfoActivity : BaseActivity() {

    private var isChecked: Boolean = true //用户协议是否被勾选
    private var bankCard: String = "" //银行卡号
    private var canClick: Boolean = false

    override fun getLayout(): Int {
        return R.layout.activity_input_bind_card
    }

    override fun initView() {
        bankCard = intent.getStringExtra("bankCard")
        et_card_type.text = "建设银行储蓄卡"
        et_phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et_phone.text.toString().trim().length == 11) {
                    canClick = true
                    tv_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    tv_next.background = resources.getDrawable(R.drawable.btn_bg_unselect)
                }
            }
        })

    }

    override fun initData() {
        iv_check_protocol.setOnClickListener {
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
                    if (PatterUtils.Companion.matchPhone(et_phone.text.trim())) {
                        val intent = Intent(baseContext, ResetPwdCheckCodeActivity::class.java)
                        intent.putExtra("mobile", et_phone.text.toString().trim())
                        startActivity(intent)
                    } else {
                        ToastUtils.showToast(this@InputBindCardInfoActivity, "请输入正确手机号")
                    }
                } else {
                    showTip("您必须同意此协议才可修改密码")
                }
            }
        }

        tv_new_reset_pwd.setOnClickListener {//跳转到ChooseResetPwdActivity
            ToastUtils.showToast(baseContext, "换个方式重置密码")
        }

    }

    private fun showTip(content:String) {
        val dialog = HintDialogUtils(this)
        dialog.setLeftOrRight(false,"",true,"知道了")
        dialog.setContentArr(true, arrayListOf(content))
        dialog.showDialog()
    }
}