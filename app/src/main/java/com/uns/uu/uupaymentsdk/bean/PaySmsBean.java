package com.uns.uu.uupaymentsdk.bean;

/**
 * Created by zhaoyan on 2018/2/10.
 */

public class PaySmsBean extends BaseBaen{
//    arrayMap.put("merchantId", merchantId)
//            arrayMap.put("orderId", )
//            arrayMap.put("orderTime", )
//            arrayMap.put("curType", )
//            arrayMap.put("userId", )
//            arrayMap.put("customerId", )
//            arrayMap.put("remark", )
//            arrayMap.put("amount", )
//            arrayMap.put("purpose", )
//            arrayMap.put("responseUrl", )
//            arrayMap.put("token", )
//            arrayMap.put("bankcardId", )

    private String userId;
    private String orderId;
    private String orderTime;
    private String curType;
    private String purpose;
    private String amount;
    private String responseUrl;
    private String token;
    private String name;
    private String idNum;
    private String cardNo;
    private String bankcardId;
    private String mobilePhoneNum;
    private String validTime;
    private String cvv2;
    private String key;
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
}
