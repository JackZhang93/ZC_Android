package com.unspay.wallet.utils;

public class Encrypt_RSA_Drive {


    private Encrypt_RSA encrypt_RSA;


    public Encrypt_RSA_Drive() {

        encrypt_RSA = new Encrypt_RSA();
    }

    public String getEncrypt_RSA(String originalData) {
        try {
            return encrypt_RSA.encrypt_RSA(originalData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
