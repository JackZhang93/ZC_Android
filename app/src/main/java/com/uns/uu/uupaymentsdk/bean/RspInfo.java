package com.uns.uu.uupaymentsdk.bean;

import com.uns.uu.uupaymentsdk.constant.Constant;

/**
 * Created by peixuan.xie on 2016/8/22.
 */
public class RspInfo {

    private String rspCode = Constant.REQ_FAIL;
    private String rspMsg = "当前网络环境差，请稍后重试!";
    private String customerId;
    private String bankCardId;
    private String bankCode;    //银行
    private String cardNo;    //银行
    private String payEnterpriseId; //企业id
    private String recordId; //记录id

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPayEnterpriseId() {
        return payEnterpriseId;
    }

    public void setPayEnterpriseId(String payEnterpriseId) {
        this.payEnterpriseId = payEnterpriseId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @Override
    public String toString() {
        return "RspInfo{" +
                "rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                ", customerId='" + customerId + '\'' +
                ", bankCardId='" + bankCardId + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", payEnterpriseId='" + payEnterpriseId + '\'' +
                ", recordId='" + recordId + '\'' +
                '}';
    }
}
