package com.uns.uu.uupaymentsdk.view

import android.content.Intent
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BaseBean
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_test
    }

    override fun initView() {
        val intent = Intent()
        intent.putExtra("data", BaseBean())
        button1.setOnClickListener {
            intent.putExtra("cardId", "6250861322900100")
            intent.setClass(baseContext, BindCreditBankCardActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            intent.putExtra("cardId", "6222620110028944586")
            intent.setClass(baseContext, BindBankCardActivity::class.java)
            startActivity(intent)
        }


        button3.setOnClickListener {
            startActivity(Intent(baseContext, CheckSmsActivity::class.java))
        }

        button4.setOnClickListener {
            intent.putExtra("name", "赵岩")
            intent.setClass(baseContext, InputBindBankActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initData() {
    }

}
