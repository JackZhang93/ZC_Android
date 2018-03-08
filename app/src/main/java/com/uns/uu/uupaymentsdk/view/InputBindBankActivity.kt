package com.uns.uu.uupaymentsdk.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import com.uns.uu.unstoast.UnsToast
import com.uns.uu.uupaymentsdk.R
import com.uns.uu.uupaymentsdk.bean.BaseBean
import com.uns.uu.uupaymentsdk.bean.CardBin
import com.uns.uu.uupaymentsdk.bean.CheckCard
import com.uns.uu.uupaymentsdk.constant.CardBinConstant
import com.uns.uu.uupaymentsdk.constant.Constant
import com.uns.uu.uupaymentsdk.utils.Utils
import com.uns.uu.uupaymentsdk.view.utils.SimpleAfterTextWatcher
import com.uns.uu.uupaymentsdk.view.utils.UnsViewUtils
import com.uns.uu.uupaymentsdk.viewmodel.GetCardInfoViewModel
import kotlinx.android.synthetic.main.activity_input_card.*

/**
 * Created by zhaoyan on 2018/3/2.
 * 手动绑卡界面
 *
 */
class InputBindBankActivity : BaseActivity() {
    private var hasCard: Boolean = false
    private lateinit var mBaseData: BaseBean
    private lateinit var mCheckCard: CheckCard
    private var name: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("name")) {
            name = intent.getStringExtra("name")
        } else {
            throw NullPointerException("name is NULL")
        }
        if (intent.hasExtra("data")) {
            mBaseData = intent.getParcelableExtra("data")
            mCheckCard = CheckCard().apply {
                merchantId = mBaseData.merchantId
                customerId = mBaseData.customerId
                merchantKey = mBaseData.merchantKey
            }
        } else {
            throw NullPointerException("data is NULL")
        }
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_input_card
    }

    override fun initView() {
        input_bank_card_name_info.text = Utils.getName(name)
        UnsViewUtils.nextViewOk(bind_bank_ok, false)
        //添加卡号检测
        input_bank_card_info.addTextChangedListener(object : SimpleAfterTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                hasCard = s?.length ?: 0 >= 16
                UnsViewUtils.nextViewOk(bind_bank_ok, hasCard)
            }

        })
    }

    override fun initData() {
        //点击下一步按钮
        bind_bank_ok.setOnClickListener {
            //获取卡片信息
            GetCardInfoViewModel().getCardInfo(input_bank_card_info.text.toString())
                    .observe(this, Observer { card ->
                        when {
                            CardBinConstant.YES == card?.retCode -> {
                                //检查卡片是否绑定
                                checkCard(card)
                            }
                            CardBinConstant.NO == card?.retCode -> UnsToast(baseContext).apply {
                                setText("暂不支持该卡片！")
                                setGravity(Gravity.CENTER,
                                        0,
                                        0)
                            }.show()
                            else -> {
                                UnsToast(baseContext).apply {
                                    setText("网络超时，请稍后重试!")
                                    setGravity(Gravity.CENTER,
                                            0,
                                            0)
                                }.show()
                            }
                        }


                    })
        }
    }

    //检查卡片是否绑定
    private fun checkCard(card: CardBin) {
        mCheckCard.cardNo = input_bank_card_info
                .text.toString()
        GetCardInfoViewModel().validCardNo(mCheckCard)
                .observe(this, Observer {
                    //没有绑过该卡
                    if (Constant.REQ_SUCCESS == it?.rspCode) {
                        if (card.data.cardType == "0") { //银行卡
                            //跳转到绑银行卡界面
                            val intent = Intent(baseContext, BindBankCardActivity::class.java)
                            intent.putExtra("name", "")
                            intent.putExtra("cardId", input_bank_card_info.text.toString())
                            intent.putExtra("data", mBaseData)
                            startActivity(intent)
                        } else if (card.data.cardType == "1") { //信用卡
                            //跳转到绑信用卡界面
                            val intent = Intent(baseContext, BindCreditBankCardActivity::class.java)
                            intent.putExtra("name", "")
                            intent.putExtra("cardId", input_bank_card_info.text.toString())
                            intent.putExtra("data", mBaseData)
                            startActivity(intent)
                        }
                    } else {
                        UnsToast(baseContext).apply {
                            setText(it?.rspMsg)
                            setGravity(Gravity.CENTER,
                                    0,
                                    0)
                        }.show()
                    }
                })
    }
}