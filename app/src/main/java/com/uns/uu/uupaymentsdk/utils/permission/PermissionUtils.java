package com.uns.uu.uupaymentsdk.utils.permission;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * Project：u8-app-android
 * Author: alex
 * Description：权限工具类
 * Date：2018/4/20
 */
public class PermissionUtils {

    public interface PermissionListener {
        void onSuccess();
    }

    public static void requestPermission(final Context context, final PermissionListener listener, String... permissions) {
        final PermissionSetting mSetting = new PermissionSetting(context);
        AndPermission.with(context)
                .permission(permissions)
                .rationale(new DefaultRationale())
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (listener != null) {
                            listener.onSuccess();
                        }
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }
}
