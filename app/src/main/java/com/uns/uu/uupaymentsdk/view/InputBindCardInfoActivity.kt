package com.uns.uu.uupaymentsdk.view

import android.arch.lifecycle.Observer
import android.content.ComponentName
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.PatterUtils
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_input_bank_card.*
import kotlinx.android.synthetic.main.activity_input_bind_card.*

/**
 * author: 张承
 * time：2018/2/23
 * des：忘记支付密码填写银行卡信息界面(手机号)
 */
class InputBindCardInfoActivity : BaseActivity() {

    private var isChecked: Boolean = true //用户协议是否被勾选
    private var cardId: String = "" //银行卡号
    private var canClick: Boolean = false

    override fun getLayout(): Int {
        return R.layout.activity_input_bind_card
    }

    override fun initView() {
        setTitle("填写银行卡信息")
        cardId = intent.getStringExtra("cardId")
        et_card_type.text = intent.getStringExtra("cardType")

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et_phone.text.toString().trim().length == 11) {
                    canClick = true
                    @Suppress("DEPRECATION")
                    tv_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    @Suppress("DEPRECATION")
                    tv_next.background = resources.getDrawable(R.drawable.btn_bg_unselect)
                }
            }
        })

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
                    if (PatterUtils.Companion.matchPhone(et_phone.text.trim())) {
                        SendSmsViewModel().sendSmS(intent.getStringExtra("merchantId"),et_phone.text.toString().trim()).observe(this,Observer {
                            if (CardBinConstant.YES == it?.rspCode) {
                                val intent = Intent(baseContext, ResetPwdCheckCodeActivity::class.java)
                                intent.putExtra("mobile", et_phone.text.toString().trim())
                                startActivity(intent)
                                finish()
                            } else {
                                showTip(it?.rspCode + "")
                            }
                        })

                    } else {
                        ToastUtils.showToast(this@InputBindCardInfoActivity, "请输入正确手机号")
                    }
                } else {
                    showTip("您必须同意此协议才可修改密码")
                }
            }
        }

        tv_new_reset_pwd.setOnClickListener {
            val intent = Intent()
            val componentName = ComponentName(packageName,"com.uns.uu.ui.money.activity.ChooseResetPwdActivity")
            intent.component = componentName
            startActivity(intent)
            finish()
        }

    }

    private fun showTip(content: String) {
        val dialog = HintDialogUtils(this)
        dialog.setLeftOrRight(false, "", true, "知道了")
        dialog.setContent(true, content)
        dialog.showDialog()
    }
}