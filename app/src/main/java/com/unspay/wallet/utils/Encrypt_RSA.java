package com.unspay.wallet.utils;

/**
 * Created by yu.cai on 2018/3/2.
 */

public class Encrypt_RSA {
    static{
        System.loadLibrary("RSACodec");
    }

    /**
     *
     * @param originalData 加密前字符串
     * @return 成功返回加密后字符串
     * @throws Exception : 失败返回错误原因
     */
    public String encrypt_RSA(String originalData) throws Exception {
        Boolean ret = new Boolean(false);
        String destData = encrypt_RSA(originalData,ret);
        if(false==ret){
            throw new Exception(destData);
        }
        return destData;
    }
    /**
     *
     * @param originalData  加密前字符串
     * @param isSuccess 是否成功
     * @return : 成功返回加密后字符串, 失败返回加密失败原因
     */
    @SuppressWarnings("JniMissingFunction")
    private native String encrypt_RSA(String originalData, Boolean isSuccess);
}
