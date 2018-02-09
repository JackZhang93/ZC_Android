package com.uns.uu.uupaymentsdk.view

import android.content.Intent
import com.uns.uu.uupaymentsdk.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView() {

        button1.setOnClickListener {
            startActivity(Intent(baseContext, BindCreditBankCardActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(baseContext, BindBankCardActivity::class.java))
        }


        button3.setOnClickListener {
            startActivity(Intent(baseContext, CheckSmsActivity::class.java))
        }
    }

    override fun initData() {
    }

}
