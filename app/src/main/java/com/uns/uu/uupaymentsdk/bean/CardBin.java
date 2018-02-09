package com.uns.uu.uupaymentsdk.bean;

/**
 * Created by peixuan.xie on 2016/8/15.
 */
public class CardBin extends BaseBaen{

    private String retCode;
    private Data data;


    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        private String id;
        private String cardBin;
        private String cardBinLen;
        private String cardLen;
        private String cardName;
        private String cardType;
        private String cardTypeName;
        private String issCode;
        private String issName;
        private String issuerCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCardBin() {
            return cardBin;
        }

        public void setCardBin(String cardBin) {
            this.cardBin = cardBin;
        }

        public String getCardBinLen() {
            return cardBinLen;
        }

        public void setCardBinLen(String cardBinLen) {
            this.cardBinLen = cardBinLen;
        }

        public String getCardLen() {
            return cardLen;
        }

        public void setCardLen(String cardLen) {
            this.cardLen = cardLen;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardTypeName() {
            return cardTypeName;
        }

        public void setCardTypeName(String cardTypeName) {
            this.cardTypeName = cardTypeName;
        }

        public String getIssCode() {
            return issCode;
        }

        public void setIssCode(String issCode) {
            this.issCode = issCode;
        }

        public String getIssName() {
            return issName;
        }

        public void setIssName(String issName) {
            this.issName = issName;
        }

        public String getIssuerCode() {
            return issuerCode;
        }

        public void setIssuerCode(String issuerCode) {
            this.issuerCode = issuerCode;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", cardBin='" + cardBin + '\'' +
                    ", cardBinLen='" + cardBinLen + '\'' +
                    ", cardLen='" + cardLen + '\'' +
                    ", cardName='" + cardName + '\'' +
                    ", cardType='" + cardType + '\'' +
                    ", cardTypeName='" + cardTypeName + '\'' +
                    ", issCode='" + issCode + '\'' +
                    ", issName='" + issName + '\'' +
                    ", issuerCode='" + issuerCode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CardBin{" +
                "retCode='" + retCode + '\'' +
                ", data=" + data +
                '}';
    }
}
