package com.uns.uu.uupaymentsdk;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * author: 张承
 * time：2018/2/5
 * des：
 */

public class InputBankCardActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText mTvUserName;

    private AppCompatEditText mEtBankCard;

    private AppCompatTextView mTvNext;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_input_bank_card;
    }

    @Override
    protected void initView() {

        mTvUserName = findViewById(R.id.tv_user_name);
        mEtBankCard = findViewById(R.id.et_bank_card);
        mEtBankCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTvNext = findViewById(R.id.tv_next);
        mTvNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        mTvUserName.setText(getHideName(name));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_next:
                break;
        }
    }

    private String getHideName(String name) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < name.length() - 1; i++) {
            sb.append("*");
        }
        sb.append(name.substring(name.length() - 1));
        return sb.toString();
    }
}
