package com.uns.uu.uupaymentsdk.view

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.utils.HideKey
import com.uns.uu.uupaymentsdk.utils.MyLogger
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStateBarColor(R.color.colorWhite)
        HideKey.initialize(this)
        setContentView(R.layout.activity_base)
        base_go_back.setOnClickListener {
            finish()
        }
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

    //设置状态栏颜色
    private fun setStateBarColor(colorResId: Int) {
        setStateBarColor(colorResId, true, true)
    }

    //设置标题
    protected fun setTitle(title: String) {
        if (!TextUtils.isEmpty(title)) {
            base_title.text = title
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param colorResId      目标颜色
     * @param stateBarVisible 状态栏是否显示，
     * 改变状态栏颜色：stateBarVisible设置为true
     * 沉浸式状态栏：colorResId设置为0，stateBarVisible设置为false
     * @param dark            设置状态栏字体颜色是否变黑
     */
    private fun setStateBarColor(colorResId: Int, stateBarVisible: Boolean, dark: Boolean) {
        var color = 0
        if (colorResId != 0) {
            color = ContextCompat.getColor(this, colorResId)
        }
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                SystemBarHelper.setStatusBarDarkModeForM(window, dark)
                SystemBarHelper.tintStatusBar(this, color, 0F, stateBarVisible)
            }
            SystemBarHelper.isFlyme4Later() -> {
                SystemBarHelper.setStatusBarDarkModeForFlyme4(window, dark)
                SystemBarHelper.tintStatusBar(this, color, 0F, stateBarVisible)
            }
            SystemBarHelper.isMIUI6Later() -> {
                SystemBarHelper.setStatusBarDarkModeForMIUI6(window, dark)
                SystemBarHelper.tintStatusBar(this, color, 0F, stateBarVisible)
            }
            else -> SystemBarHelper.tintStatusBar(this, color, stateBarVisible)
        }
    }
}
