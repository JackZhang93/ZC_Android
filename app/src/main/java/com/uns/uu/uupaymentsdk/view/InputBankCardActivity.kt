package com.uns.uu.uupaymentsdk.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_input_bank_card.*

/**
 * author: 张承
 * time：2018/2/23
 * des：输入银行卡号
 */
class InputBankCardActivity : BaseActivity() {

    private var canClick: Boolean = false
    override fun getLayout(): Int {
        return R.layout.activity_input_bank_card
    }

    override fun initView() {
        setTitle("忘记支付密码")
        if (intent.hasExtra("userName")) {
            tv_user_name.text = getHideName(intent.getStringExtra("userName"))
        }
        tv_input_card_next.setOnClickListener {
            if (canClick) {
                GetCardInfoViewModel().getCardInfo(et_input_bank_card.text.toString()).observe(this, Observer {
                    if (CardBinConstant.YES == it?.retCode) {
                        val intent = Intent(baseContext, InputBindCardInfoActivity::class.java)
                        intent.putExtra("cardId", et_input_bank_card.text.toString().trim())
                        intent.putExtra("cardType", "${it.data?.issName}${it.data?.cardTypeName}")
                        startActivity(intent)
                        finish()
                    } else {
                        showTip(it?.retCode + "")
                    }
                })

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
                if (et_input_bank_card.text.toString().trim().isNotEmpty()) {
                    canClick = true
                    @Suppress("DEPRECATION")
                    tv_input_card_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    @Suppress("DEPRECATION")
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

    private fun showTip(content: String) {
        val dialog = HintDialogUtils(this)
        dialog.setLeftOrRight(false, "", true, "知道了")
        dialog.setContent(true, content)
        dialog.showDialog()
    }

}