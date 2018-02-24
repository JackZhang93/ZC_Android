package com.uns.uu.uupaymentsdk.view

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import kotlinx.android.synthetic.main.activity_input_bank_card.*

/**
 * author: 张承
 * time：2018/2/23
 * des：忘记支付密码输入银行卡号
 */
class InputBankCardActivity : BaseActivity() {

    private var canClick: Boolean = false
    override fun getLayout(): Int {
        return R.layout.activity_input_bank_card
    }

    override fun initView() {
        tv_user_name.text = getHideName("马化腾")
        tv_input_card_next.setOnClickListener {
            if (canClick) {
                val intent = Intent(baseContext, InputBindCardInfoActivity::class.java)
                intent.putExtra("bankCard", et_input_bank_card.text.toString().trim())
                startActivity(intent)
            }
        }
    }

    override fun initData() {
        et_input_bank_card.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et_input_bank_card.text.toString().trim().length > 1) {
                    canClick = true
                    tv_input_card_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    tv_input_card_next.background = resources.getDrawable(R.drawable.btn_bg_unselect)
                }
            }
        })
    }

    private fun getHideName(name: String): String {
        val sb = StringBuffer()
        for (i in 0 until name.length - 1) {
            sb.append("*")
        }
        sb.append(name.substring(name.length - 1))
        return sb.toString()
    }

}