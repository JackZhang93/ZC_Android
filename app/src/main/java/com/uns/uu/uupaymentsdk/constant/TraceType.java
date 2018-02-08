package com.uns.uu.uupaymentsdk.constant;

/**
 * Created by peixuan.xie on 2016/8/11.
 * 定义钱包功能选项
 */
public class TraceType {

    public final static int REG = 1;//注册钱包

    public final static int UPDATE_PWD = 2;//修改钱包密码

    public final static int FORGET_PWD_OlD = 3;//忘记密码，使用旧卡

    public final static int BIND_CARD = 4;//绑卡

    public final static int UNBIND_CARD = 5;//解绑卡

    public final static int RECHARGE = 6;//充值

    public final static int BIND_RECHARGE = 7;//绑卡加充值

    public final static int WITHDRAW = 8;//提现

    public final static int TRANSFER_B2U = 9;//转账_冻结

    public final static int TRANSFER_U2U = 10;//转账_冻结

    public final static int NO_PWD_PAY = 11;//无密支付

    public final static int FORGET_PWD_NEW = 12;//忘记密码,使用新卡

    public final static int SET_PWD = 13;//设置密码

    public final static int TRAN_B2U = 14;//转账

    public final static int TRAN_U2U = 15;//转账

    public final static int VALI_PWD = 16;//验证密码

    public final static int CHOSE_BACK = 17; //红包

    public final static int CHOSE_SERVER = 18; //服务和需求
    
    public final static int CHOSE_ACTIVITY = 19; //活动
    
    public final static int CHOSE_ENTERPRISE_BACK = 20; //发企业红包
    
    public final static int MERCHAT_PAY = 21; //消费
    public final static int MERCHAT_WITH_PAYTYPE_PAY = 22; //消费2
    public final static int INSURANCE_PAY = 23; //投保
    public final static int SHOUP_PAY = 24; //买商品
}
