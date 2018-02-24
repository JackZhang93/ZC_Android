package com.uns.uu.uupaymentsdk.bean;

import android.os.Parcel;

/**
 * Created by zhaoyan on 2018/2/8.
 * 绑定信用卡
 */

public class BindCreditCard extends BaseCardBean{
    private String validTime; //信用卡有效期
    private String cvv2;      //后三位

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.validTime);
        dest.writeString(this.cvv2);
    }

    public BindCreditCard() {
    }

    protected BindCreditCard(Parcel in) {
        super(in);
        this.validTime = in.readString();
        this.cvv2 = in.readString();
    }

    public static final Creator<BindCreditCard> CREATOR = new Creator<BindCreditCard>() {
        @Override
        public BindCreditCard createFromParcel(Parcel source) {
            return new BindCreditCard(source);
        }

        @Override
        public BindCreditCard[] newArray(int size) {
            return new BindCreditCard[size];
        }
    };
}
