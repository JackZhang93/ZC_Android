package com.uns.uu.uupaymentsdk.network;

/**
 * Created by peixuan.xie on 2016/8/11.
 * 网络API
 */
@SuppressWarnings("WeakerAccess")
public class Api {

    //卡bin
    public static final String BASE_CARD_URL = "http://moto.kabao8.com:19081/web_cardbin/";


    public static final String CARD_BIN = "cardbin/get.uns";
    //   生产
//	public static final String FORMAL_URL = "https://uu.wedo77.com/UU/";
//	//	银生宝生产环境
//	public static final String FORMAL_URL_OLD = "http://211.147.83.228:38082/unspay-wallet/";
//	// 商户
//	public static final String SHOP_URL = "http://172.22.22.57:8180/unspay-wallet/";
//
//
//	//测试系统
//	public static final String TEST_YSB_URL = "http://192.168.9.81:80/UU/";
//	public static final String TEST_YSB_UAT_URL = "https://uu.weidu7.com/UU/";
    public static final String TEST_YSB_URL_OLD = "http://172.22.25.21:8084/unspay-wallet/";
    //
//		public static final String TEST_YSB_URL = "http://172.22.200.73:8080/UU/";
//	public static final String TEST_U8_UAT_URL = "https://uu.weidu7.com/UU/";
//	public static final String TEST_U8_URL = "http://192.168.9.81:80/UU/";
//	public static final String RESULT_U8_URL = "https://uu.wedo77.com/UU/";
//
//	public static final String VERSION_INFO = "appVerinfo/version.txt";
//
//	//注册
//	public static final String REAL_NAME_REG = "walletSdk/register";
//
//	//设置密码
//	public static final String REAL_NAME_SET_PWD = "walletSdk/setPwd";
//
//	//发送短信验证码
//	public static final String REAL_NAME_SEND_SMS_CODE = "wallet/sendSmsCode";
//
//	//验证短信验证码
////	public static final String REAL_NAME_VALID_SMS_CODE = "";
//
    //绑定银行卡
    public static final String BIND_CARD = "wallet/bindCardNo";
    //绑定信用卡
    public static final String BIN_CREDIT_CARD = "wallet/bindCreditCard";

    //发送短信验证码
    public static final String REAL_NAME_SEND_SMS_CODE = "wallet/sendSmsCode";
    //
//	//充值
//	public static final String RECHARGE = "wallet/recharge";
//
//	//提现
//	public static final String WITHDRAW = "wallet/withdraw";
//
//	//验证密码
//	public static final String VER_PWD = "walletSdk/checkpaypwd";
//
//	//解绑卡
//	public static final String UN_BING_CARD = "wallet/unwrap";
//
    //验证银行卡是否绑定
    public static final String VALID_CARD_NO = "wallet/validcardNo";
    //绑定行用卡发送验证码
    public static final String BIN_CREDIT_CARD_SEND_SMS_CODE = "wallet/getVericode";
    //
//	//转账（bank to user）交易冻结
//	public static final String TRAN_B2U = "walletSdk/transferFrozenByBank";
//
//	//转账（user to user）交易冻结
//	public static final String TRAN_U2U = "walletSdk/tradingFrozen";
//
//	//转账（bank to user） 无冻结
//	public static final String TRAN_B2U_UNWITH = "walletSdk/syncTransferByBank";
//
//	//转账 （user to user）无冻结
//	public static final String TRAN_U2U_UNWITH = "walletSdk/syncTransfer";
//
    //旧卡验证
    public static final String PWD_OLD_CARD_IDENTIFY_CODE = "wallet/forgetPwdForCardNo";
//
//	//发红包(余额)
//	public static final String SEND_RED_PACKET = "walletSdk/sendEnvelope";
//	//发红包(银行)
//	public static final String SEND_RED_PACKET_BANK = "walletSdk/sendEnvelopeByBank";
//	//发企业红包
//	public static final String SEND_ENTERPRISE_RED_PACKET = "walletSdk/SendEPEnvelope";
//	//活动付款
//	public static final String ACTIVITY_PAY = "activity";
//	//服务付款
//	public static final String SERVICE_PAY = "wallet";
//	//企业福利消费
//	public static final String WELFARE_CONSUMPTION = "walletSdk/welfareConsumption";
}
