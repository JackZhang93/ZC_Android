package com.uns.uu.uupaymentsdk.utils;

import android.os.CountDownTimer;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;

@SuppressWarnings("JavaDoc")
public class Utils {

    private static boolean isDebug = true;
    public static final String CHECKVALE = "IWS/tmvtNvEAyb2M49rAtpg9qJvCRCe2rzESf5vzzZ9278ZIZJK2/U+3eKLI3WLNyClbW/fRrojv3FJqiSdnBbRmnEpTW+ocA45Q42y8QbpDnrel/+68UYGioIlxiZIRUGX56Sh9y/ZWr22KXJfeT61ZcXPVoDl+MYq9HGPBlxM=";//发布
    //	public static final String checkVale = "Lp2W3STA8Iq3d4VRLhmaAR+traGmQdecJg1rFQgMC7UuoVKKhJMFihRYTYkQOjKy8NM0Humu8tLCIL3/i5XtWuglHmZb2gLPpO1EefgCQzKf0BmS4n9/+sWxkNxjBM+MTepex+/WXhWjWpTng+ZtRgQXSZx75tbIwo5yDc7u6/w=";//调试
    public static final String PUBKEY = "65537";
    public static final String MODEL = "148504140845426438561644512326434666302048536031929165680994939150392786777934290662765912341529824786974532946194356218824494392592754487978358757511031053399553087629966852034926006187534033182518689479050955430521352079215909572293790991435779658099929047249830767497052127466310116598178129593579671944733";


    /**
     * 获取json对象里面相对应键的值
     *
     * @param jsonObject json对象
     * @param jsonKey    key
     * @return value
     * @throws JSONException
     * @author Luotm
     * @date 2014-07-16
     */
    public static String jsonIsNull(JSONObject jsonObject, String jsonKey) throws JSONException {
        if (null != jsonObject && !jsonObject.isNull(jsonKey))
            return jsonObject.getString(jsonKey);
        return "";
    }


    /**
     * 金额格式判断 小数点后保留两位
     *
     * @author Luotm
     * @date 2014-7-22上午9:40:23
     */
    public static boolean isAmount(String amount) {
        if (!TextUtils.isEmpty(amount)) {
            return !(Double.valueOf(amount) > 0 && amount.length() < 13);
        }
        return true;
    }

    /**
     * 时间格式转化
     *
     * @author Luotm
     * @date 2014-7-22上午9:41:20
     */
    public static boolean isPayTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        long mtime = Long.parseLong(time.substring(0, 7));
        long today = Long.valueOf(sdf.format(new Date()));
        if (mtime > today) {
            return true;
        }
        return false;
    }

    /**
     * 八位随机数
     *
     * @author Luotm
     * @date 2014-7-23下午6:42:44
     */
    public static String getRadom() {
        Random random = new Random();
        String rad = "";
        for (int i = 0; i < 8; i++) {
            if (rad != null) {
                rad = rad.concat(Math.abs(random.nextInt(10)) + "");
            } else {
                rad = Math.abs(random.nextInt(10)) + "";
            }
        }
        return rad;
    }

    /**
     * 将有规律的字符串转换成map
     *
     * @param
     * @author Luotm
     * @date 2014-7-22上午9:36:27
     */
    public static Map<String, String> getResultMap(String param) {
        String key, value;
        String[] result = param.split("&");
        Map<String, String> resultMap = new HashMap<String, String>();
        for (int i = 0; i < result.length; i++) {
            String[] key_value = result[i].split("=");
            key = result[i].split("=")[0];
            if (key_value.length > 1) {
                value = result[i].split("=")[1];
            } else {
                value = "";
            }
            resultMap.put(key, value);
        }
        return resultMap;
    }

    public static void log(String print_info) {
        if (isDebug) {
            System.out.println(print_info);
        }
    }

    public static String getPid(String pid) {
        String str = "";
        if (!pid.equals("")) {
            String s0 = pid.substring(0, 6);
            String s1 = pid.substring(8, pid.length() - 1);
            str = s0 + s1;
        }
        return str;
    }

    //获取当前系统时间,
    public static String systemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /*
     *格式化银行卡
     */
    public static String formatCardNum(String s) {
        String noblank = s.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < noblank.length(); ++i) {
            sb.append(noblank.charAt(i));
            if (i == 3 || i == 7 || i == 11 || i == 15) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    /*
     * 卡号遮蔽
     */
    public static String getAcctNum(String cardNo) {
        String acctNum = "";
        int len = cardNo.length();
        int starLen = len - 10;
        String starStr = "";
        for (int i = 0; i < starLen; i++) {
            starStr += "*";
        }
        if (cardNo != null && cardNo.length() >= 10) {
            acctNum = cardNo.substring(0, 6) + starStr + cardNo.substring(cardNo.length() - 4);
        }
        return acctNum;
    }

    /*
     * 手机号遮蔽
     */
    public static String getTel(String tel) {
        if (TextUtils.isEmpty(tel)) {
            return "";
        } else if (tel.length() >= 7) {
            return tel.substring(0, 3) + "*****" + tel.substring(tel.length() - 4, tel.length());
        } else {
            return tel.substring(0, 3) + "*****";
        }
    }

    /*
     *姓名遮盖
     */
    public static String getName(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        } else {
            int length = name.length();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length - 1; i++) {
                builder.append("*");
            }
            String substring = name.substring(length - 1, length);
            builder.append(substring);
            return builder.toString();
        }
    }

    /*
     *计时器
     */
    public static class TimeCount extends CountDownTimer {
        private TimeCountImpl timeCount;

        public TimeCount(long millisInFuture, long countDownInterval, TimeCountImpl timer) {
            super(millisInFuture, countDownInterval);
            this.timeCount = timer;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeCount.timeLoading(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            timeCount.onFinish();
        }

        public interface TimeCountImpl {
            void timeLoading(long millisUntilFinished);

            void onFinish();
        }

    }

    public static Thread getThread(Runnable runnable) {
        return Executors.defaultThreadFactory().newThread(runnable);
    }

}
