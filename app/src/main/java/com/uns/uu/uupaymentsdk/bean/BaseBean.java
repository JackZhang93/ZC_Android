package com.uns.uu.uupaymentsdk.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhaoyan on 2018/2/7.
 */

public class BaseBean implements Parcelable {
    private String merchantKey="kb201610171300#!!!";        //key
    private String merchantId = "1120140210111823001";      //商户id
    private String customerId = "33833";                    //用户id

    public static final Creator<BaseBean> CREATOR = new Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel in) {
            return new BaseBean(in);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.merchantKey);
        dest.writeString(this.merchantId);
        dest.writeString(this.customerId);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.merchantKey = in.readString();
        this.merchantId = in.readString();
        this.customerId = in.readString();
    }

}
