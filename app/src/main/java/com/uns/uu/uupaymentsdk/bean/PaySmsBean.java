package com.uns.uu.uupaymentsdk.bean;

import android.os.Parcel;

/**
 * Created by zhaoyan on 2018/2/10.
 * 信用卡支付
 */

public class PaySmsBean extends BaseBean {

    private String userId;          // Y,       入账方用户ID
    private String orderId = "UNS20180305160114384";         // Y,       订单号
    private String orderTime = "20180305141828";       // Y,       订单时间
    private String curType = "CNY";   // Y,       金额类型（CNY）
    private String purpose;         // Y,       目的
    private String amount;          // Y,       金额
    private String responseUrl;     // N,       商户响应地址
    private String token;
    private String name;
    private String idNum;
    private String cardNo;
    private String bankcardId;      //          银行卡id
    private String mobilePhoneNum;
    private String validTime;       // Y,       信用卡有效期
    private String cvv2;            // Y,       cvv2
    private String key=super.getMerchantKey();
//    private String remark;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCurType() {
        return curType;
    }

    public void setCurType(String curType) {
        this.curType = curType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(String bankcardId) {
        this.bankcardId = bankcardId;
    }

    public String getMobilePhoneNum() {
        return mobilePhoneNum;
    }

    public void setMobilePhoneNum(String mobilePhoneNum) {
        this.mobilePhoneNum = mobilePhoneNum;
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.userId);
        dest.writeString(this.orderId);
        dest.writeString(this.orderTime);
        dest.writeString(this.curType);
        dest.writeString(this.purpose);
        dest.writeString(this.amount);
        dest.writeString(this.responseUrl);
        dest.writeString(this.token);
        dest.writeString(this.name);
        dest.writeString(this.idNum);
        dest.writeString(this.cardNo);
        dest.writeString(this.bankcardId);
        dest.writeString(this.mobilePhoneNum);
        dest.writeString(this.validTime);
        dest.writeString(this.cvv2);
        dest.writeString(this.key);
    }

    public PaySmsBean() {
    }

    protected PaySmsBean(Parcel in) {
        super(in);
        this.userId = in.readString();
        this.orderId = in.readString();
        this.orderTime = in.readString();
        this.curType = in.readString();
        this.purpose = in.readString();
        this.amount = in.readString();
        this.responseUrl = in.readString();
        this.token = in.readString();
        this.name = in.readString();
        this.idNum = in.readString();
        this.cardNo = in.readString();
        this.bankcardId = in.readString();
        this.mobilePhoneNum = in.readString();
        this.validTime = in.readString();
        this.cvv2 = in.readString();
        this.key = in.readString();
    }

    public static final Creator<PaySmsBean> CREATOR = new Creator<PaySmsBean>() {
        @Override
        public PaySmsBean createFromParcel(Parcel source) {
            return new PaySmsBean(source);
        }

        @Override
        public PaySmsBean[] newArray(int size) {
            return new PaySmsBean[size];
        }
    };
}
