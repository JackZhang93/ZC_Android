package com.uns.uu.uupaymentsdk.bean;

import android.os.Parcel;

/**
 * Created by zhaoyan on 2018/2/8.
 * 绑定银行卡
 */

public class BindBankCard extends BaseCardBean{
    private String accountType="P";     //账户类型,P个人C企业，默认个人。
    private String idCode;              //身份证号,企业绑定银行卡时传入

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.accountType);
        dest.writeString(this.idCode);
    }

    public BindBankCard() {
    }

    protected BindBankCard(Parcel in) {
        super(in);
        this.accountType = in.readString();
        this.idCode = in.readString();
    }

    public static final Creator<BindBankCard> CREATOR = new Creator<BindBankCard>() {
        @Override
        public BindBankCard createFromParcel(Parcel source) {
            return new BindBankCard(source);
        }

        @Override
        public BindBankCard[] newArray(int size) {
            return new BindBankCard[size];
        }
    };
}
