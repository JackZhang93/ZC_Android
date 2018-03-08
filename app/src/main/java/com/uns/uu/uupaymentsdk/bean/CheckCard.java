package com.uns.uu.uupaymentsdk.bean;

/**
 * Created by zhaoyan on 2018/2/7.
 */

public class CheckCard extends BaseBean {
    private String cardNo = "";        //银行卡卡号
    private String accountType = "P";  //P个人，C企业，默认个人

//    public CheckCard(String cardNo) {
//        this.cardNo = cardNo;
//    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
