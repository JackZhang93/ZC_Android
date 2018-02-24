package com.uns.uu.uupaymentsdk.utils

import java.util.regex.Pattern

/**
 * Created by zhaoyan on 2018/2/2.
 * 正则
 */
class PatterUtils {

    companion object {
        fun matchPhone(phone: CharSequence): Boolean {
            return Pattern.matches("^1[0-9]{10}$", phone)
        }
    }
}