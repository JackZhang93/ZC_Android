package com.uns.uu.uupaymentsdk.view

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.utils.HintDialogUtils
import com.uns.uu.uupaymentsdk.utils.RefreshVerifyCode
import com.uns.uu.uupaymentsdk.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_reset_pwd_check_code.*

/**
 * author: 张承
 * time：2018/2/23
 * des：
 */
class ResetPwdCheckCodeActivity : BaseActivity() {
    private var canClick: Boolean = false
    private lateinit var refresh: RefreshVerifyCode
    private lateinit var handler: Handler
    override fun getLayout(): Int {
        return R.layout.activity_reset_pwd_check_code
    }

    override fun initView() {
        handler = Handler()
        refresh = RefreshVerifyCode(tv_get_code,handler)
        et_code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (et_code.text.toString().trim().length == 6) {
                    canClick = true
                    tv_check_code_next.background = resources.getDrawable(R.drawable.btn_bg_selected)
                } else {
                    canClick = false
                    tv_check_code_next.background = resources.getDrawable(R.drawable.btn_bg_unselect)
                }
            }
        })
    }

    override fun initData() {
        if (intent.hasExtra("mobile")) {
            tv_tip.text = String.format("本次操作需要短信确认，验证码已发送至手机：%s，请按提示操作。", getHideMobile(intent.getStringExtra("mobile")))
        }

        tv_get_code.setOnClickListener {
            getVerifyCode()
        }

        tv_get_code_wrong.setOnClickListener {
            ToastUtils.showToast(baseContext, "收不到验证码")
        }

        tv_check_code_next.setOnClickListener {
            if (canClick) {//跳到InputPwdResetActivity
                ToastUtils.showToast(baseContext, "下一步")
            }
        }

        getVerifyCode()

    }

    private fun getHideMobile(mobile: String): String {
        return mobile.substring(0, 3) + "*****" + mobile.substring(8, mobile.length)
    }

    //获取手机验证码
    private fun getVerifyCode() {
        refresh.sure()
        refresh.setCount(60)
        handler.post(refresh)
    }

    private fun showTip(content:String) {
        val dialog = HintDialogUtils(this)
        dialog.setLeftOrRight(false,"",true,"知道了")
        dialog.setContent(true, content)
        dialog.showDialog()
    }

}