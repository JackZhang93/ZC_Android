package com.uns.uu.uupaymentsdk;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

/**
 * author: 张承
 * time：2018/2/5
 * des：
 */

public class InputBindCardInfoActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView mTvCardInfo;

    private AppCompatEditText mEtName;

    private AppCompatEditText mEtIdCard;

    private AppCompatEditText mEtPhone;

    private ImageView mIvCheckProtocol;

    private AppCompatTextView mTvUserProtocol;

    private AppCompatTextView mTvNext;

    private AppCompatTextView mTvNewResetPwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_input_bind_card;
    }

    @Override
    protected void initView() {

        mTvCardInfo = findViewById(R.id.tv_card_info);
        mEtName =  findViewById(R.id.et_name);
        mEtIdCard = findViewById(R.id.et_id_card);
        mEtPhone =findViewById(R.id.et_phone);
        mIvCheckProtocol = findViewById(R.id.iv_check_protocol);
        mIvCheckProtocol.setOnClickListener(this);
        mTvUserProtocol = findViewById(R.id.tv_user_protocol);
        mTvUserProtocol.setOnClickListener(this);
        mTvNext = findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
        mTvNewResetPwd = findViewById(R.id.tv_new_reset_pwd);
        mTvNewResetPwd.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_check_protocol:
                break;
            case R.id.tv_user_protocol://跳转到用户协议
                break;
            case R.id.tv_next:
                break;
            case R.id.tv_new_reset_pwd://跳转到选择重置密码界面
                break;
        }
    }
}
