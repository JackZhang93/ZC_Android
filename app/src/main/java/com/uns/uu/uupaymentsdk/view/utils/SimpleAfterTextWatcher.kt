package com.uns.uu.uupaymentsdk.view.utils

import android.text.TextWatcher

/**
 * Created by zhaoyan on 2018/2/26.
 *
 */
abstract class SimpleAfterTextWatcher:TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}