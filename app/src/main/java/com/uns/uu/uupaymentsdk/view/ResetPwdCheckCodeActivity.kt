package com.uns.uu.uupaymentsdk.view

import android.arch.lifecycle.Observer
import android.content.ComponentName
import android.content.Intent
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.RefreshVerifyCode
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import com.uns.uu.uupaymentsdk.utils.Utils
import com.uns.uu.uupaymentsdk.viewmodel.SendSmsViewModel
import kotlinx.android.synthetic.main.activity_input_bind_card.*
import kotlinx.android.synthetic.main.activity_reset_pwd_check_code.*

/**
 * author: 张承
 * time：2018/2/23
 * des：忘记支付密码通过银行卡，获取手机验证码
 */
class ResetPwdCheckCodeActivity : BaseActivity() {
    private var canClick: Boolean = false
    private lateinit var refresh: RefreshVerifyCode
    private lateinit var handler: Handler
    private lateinit var mDialog: HintDialogUtils   //提示框
    override fun getLayout(): Int {
        return R.layout.activity_reset_pwd_check_code
    }

    override fun initView() {
        setTitle("验证手机号")
        handler = Handler()
        refresh = RefreshVerifyCode(tv_get_code, handler)
        mDialog = HintDialogUtils(this)
        et_code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et_code.text.toString().trim().length == 6) {
                    canClick = true
                    @Suppress("DEPRECATION")
                    tv_check_code_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    @Suppress("DEPRECATION")
                    tv_check_code_next.background = resources.getDrawable(R.drawable.btn_bg_unselect)
                }
            }
        })
    }

    override fun initData() {
        if (intent.hasExtra("mobile")) {
            tv_tip.text = String.format("本次操作需要短信确认，验证码已发送至手机：%s，请按提示操作。", Utils.getTel(intent
                    .getStringExtra("mobile")))
        }

        tv_get_code.setOnClickListener {
            getVerifyCode()
        }

        tv_get_code_wrong.setOnClickListener {
            mDialog.apply {
                setTitle("收不到验证码")
                setContentArr(true, arrayListOf("请确认当前是否使用银行预留手机号。", "请检查短信是否被手机安全软件拦截。", "若预留手机号已停用，请联系银行客服咨询。"))
                setLeftOrRight(false, "", true, "")
            }.showDialog()
        }

        tv_check_code_next.setOnClickListener {
            if (canClick) {//跳到com.uns.uu.ui.money.activity.InputPwdResetActivity
                val intent = Intent()
                val componentName = ComponentName(packageName,"com.uns.uu.ui.money.activity.InputPwdResetActivity")
                intent.component = componentName
                startActivity(intent)
                finish()
            }
        }

        getVerifyCode()

    }


    //获取手机验证码
    private fun getVerifyCode() {
        SendSmsViewModel().sendSmS(intent.getStringExtra("merchantId"),intent
                .getStringExtra("mobile")).observe(this, Observer {
            if (CardBinConstant.YES == it?.rspCode) {
                val intent = Intent(baseContext, ResetPwdCheckCodeActivity::class.java)
                intent.putExtra("mobile", et_phone.text.toString().trim())
                startActivity(intent)
                finish()
            } else {
                showTip(it?.rspCode + "")
            }
        })
        refresh.sure()
        refresh.setCount(60)
        handler.post(refresh)
    }

    private fun showTip(content: String) {
        val dialog = HintDialogUtils(this)
        dialog.setLeftOrRight(false, "", true, "知道了")
        dialog.setContent(true, content)
        dialog.showDialog()
    }

}