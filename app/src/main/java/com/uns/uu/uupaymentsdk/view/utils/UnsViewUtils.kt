package com.uns.uu.uupaymentsdk.view.utils

import android.view.View
import com.uns.uu.uupaymentsdk.R

/**
 * Created by zhaoyan on 2018/2/24.
 * 确定按钮变化
 */
class UnsViewUtils {
    companion object {
        fun nextViewOk(view: View, ok: Boolean) {
            view.isEnabled = ok
            if (ok) {
                @Suppress("DEPRECATION")
                view.background = view.resources.getDrawable(R.drawable.btn_bg_selected)
            } else {
                @Suppress("DEPRECATION")
                view.background = view.resources.getDrawable(R.drawable.btn_bg_unselect)
            }
        }
    }
}