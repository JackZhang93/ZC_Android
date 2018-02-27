package com.uns.uu.uupaymentsdk.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.core.internal.deps.protobuf.ExtensionRegistryLite
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.proto.assertion.ViewAssertions
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.uns.uu.uupaymentsdk.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by zhaoyan on 2018/2/27.
 */
@RunWith(value = AndroidJUnit4::class)
@LargeTest
open class BindBankCardActivityTest {
    @get:Rule //添加ActivityTestRule，注意泛型，测试MainActivity
    var mActivityRule = ActivityTestRule<BindBankCardActivity>(BindBankCardActivity::class
            .java)

    @Test
    open fun initData() {
        //同意条款
        onView(allOf(withId(R.id.bind_bank_accord), isDisplayed())).perform(click())
        //手机号
        onView(allOf(withId(R.id.bind_credit_card_phone_info), isDisplayed())).perform(ViewActions.replaceText
        ("17717529827"), closeSoftKeyboard())

        val matches = allOf(withId(R.id.bind_bank_ok), isClickable()).matches(true)
        when (matches) {
            true -> {
                ViewAssertions.registerAllExtensions(ExtensionRegistryLite.getEmptyRegistry())
            }
            else -> {
            }
        }


    }
}