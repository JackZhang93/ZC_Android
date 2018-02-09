package com.uns.uu.uupaymentsdk.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by peixuan.xie on 2016/8/11.
 */
public class ToastUtils {

    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;
    /**
     * 显示Toast
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT*1000){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
                toast=null;
            }
        }
        oneTime = twoTime ;
    }
}
