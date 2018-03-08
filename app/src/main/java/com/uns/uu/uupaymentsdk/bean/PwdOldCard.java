package com.uns.uu.uupaymentsdk.bean;

/**
 * Created by zhaoyan on 2018/3/8.
 * 旧卡验证
 */

public class PwdOldCard extends BaseBean {
    private String bankCardId;      //银行卡所在Id
    private String cardNo;        //卡号
    private String cardType;      //卡类型 1表示借记卡，2表示信用卡
    private String phoneNo;        //手机号
    private String validCode;       //验证码
    private String idCode;          //身份证号
    private String name;          //姓名

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
