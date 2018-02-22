package com.uns.uu.uupaymentsdk

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_dialog_lauout)
        if (getLayout() != 0) {
            val view = LayoutInflater.from(baseContext).inflate(getLayout(), null)
            wallet_content.addView(view)
            initView()
            initData()
        }
    }

    //获取布局
    protected abstract fun getLayout(): Int

    //加载布局
    protected abstract fun initView()

    //初始化数据
    protected abstract fun initData()

}
