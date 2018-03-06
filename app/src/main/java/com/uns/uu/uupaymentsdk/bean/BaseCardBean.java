package com.uns.uu.uupaymentsdk.bean;

import android.os.Parcel;

/**
 * Created by zhaoyan on 2018/2/8.
 * 绑卡基本信息
 */

public class BaseCardBean extends BaseBean {
    private String cardNo;                  //卡号
    private String cardType;                //卡类型 1表示借记卡，2表示信用卡
    private String bankCode;                //银行代码
    private String phoneNo;                 //手机号码
    private String validCode;               //验证码
    private String mac;                     //报文鉴别码

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.cardNo);
        dest.writeString(this.cardType);
        dest.writeString(this.bankCode);
        dest.writeString(this.phoneNo);
        dest.writeString(this.validCode);
        dest.writeString(this.mac);
    }

    public BaseCardBean() {
    }

    protected BaseCardBean(Parcel in) {
        super(in);
        this.cardNo = in.readString();
        this.cardType = in.readString();
        this.bankCode = in.readString();
        this.phoneNo = in.readString();
        this.validCode = in.readString();
        this.mac = in.readString();
    }

}
