package com.uns.uu.uupaymentsdk.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uns.uu.uupaymentsdk.R;

/**
 * Created by zhen.liu on 2018/2/2.
 * 提示框
 */

public class HintDialogUtils {

    private Context mContext;
    private View contentView;
    private AlertDialog dialog;
    private TextView dialog_title;
    private LinearLayout ll_center_container;
    private Button dialog_cancel;
    private Button dialog_ok;
    private View view_center_line;

    private ClickListener clickListener;

    public HintDialogUtils(Context context) {
        this.mContext = context;
        init("", true, null, true, "", true, "");
    }

    public HintDialogUtils(Context context, String title, boolean isTextCenter, String[] contentArr, boolean showLeftBtn, String leftString, boolean showRightBtn, String rightString) {
        this.mContext = context;
        init(title, isTextCenter, contentArr, showLeftBtn, leftString, showRightBtn, rightString);
    }

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (dialog_title != null) {
            dialog_title.setText(TextUtils.isEmpty(title) ? "提示" : title);
        }
    }

    /**
     * 设置内容，以数组的形式传递
     *
     * @param isTextCenter
     * @param contentArr
     */
    public void setContentArr(boolean isTextCenter, String[] contentArr) {
        ll_center_container.removeAllViews();
        for (int i = 0; i < contentArr.length; i++) {
            TextView textView = getTextView(isTextCenter, contentArr[i], LinearLayout.LayoutParams.MATCH_PARENT);
            if (contentArr.length > 1) {
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.TOP);
                TextView tip = getTextView(isTextCenter, (i + 1) + "、", LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(tip);
                linearLayout.addView(textView);
                ll_center_container.addView(linearLayout);
            } else {
                ll_center_container.addView(textView);
            }
        }
    }

    /**
     * 添加自己定义的view
     *
     * @param isTextCenter
     * @param contentArr
     * @param selfView
     */
    public void setSelfView(boolean isTextCenter, String[] contentArr, View selfView) {
        ll_center_container.removeAllViews();
        ll_center_container.addView(selfView);
        for (int i = 0; i < contentArr.length; i++) {
            TextView textView = getTextView(isTextCenter, contentArr[i], LinearLayout.LayoutParams.MATCH_PARENT);
            if (contentArr.length > 1) {
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.TOP);
                TextView tip = getTextView(isTextCenter, (i + 1) + "、", LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(tip);
                linearLayout.addView(textView);
                ll_center_container.addView(linearLayout);
            } else {
                ll_center_container.addView(textView);
            }
        }
    }

    private TextView getTextView(boolean isTextCenter, String text, int width) {
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimensionPixelSize(R.dimen.dimens_14));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 5;
        if (isTextCenter) {
            textView.setGravity(Gravity.CENTER);
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /**
     * 设置左边按钮的文字与显示
     *
     * @param showLeftBtn
     * @param leftString
     */
    public void setLeft(boolean showLeftBtn, String leftString) {
        if (showLeftBtn) {
            dialog_cancel.setVisibility(View.VISIBLE);
            dialog_cancel.setText(TextUtils.isEmpty(leftString) ? "取消" : leftString);
        } else {
            dialog_cancel.setVisibility(View.GONE);
            view_center_line.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边的文字与显示
     *
     * @param showRightBtn
     * @param rightString
     */
    public void setRight(boolean showRightBtn, String rightString) {
        if (showRightBtn) {
            dialog_ok.setVisibility(View.VISIBLE);
            dialog_ok.setText(TextUtils.isEmpty(rightString) ? "知道了" : rightString);
        } else {
            dialog_ok.setVisibility(View.GONE);
            view_center_line.setVisibility(View.GONE);
        }
    }

    private void init(String title, boolean isTextCenter, String[] contentArr, boolean showLeftBtn, String leftString, boolean showRightBtn, String rightString) {
        contentView = View.inflate(mContext, R.layout.dialog_unreceive_verify_code, null);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setView(contentView);
        dialog.setCanceledOnTouchOutside(false);
        dialog_title = (TextView) contentView.findViewById(R.id.dialog_title);
        ll_center_container = (LinearLayout) contentView.findViewById(R.id.ll_center_container);
        dialog_cancel = (Button) contentView.findViewById(R.id.dialog_cancel);
        dialog_ok = (Button) contentView.findViewById(R.id.dialog_ok);
        view_center_line = contentView.findViewById(R.id.view_center_line);

        setTitle(title);
        setLeft(showLeftBtn, leftString);
        setRight(showRightBtn, rightString);
        setContentArr(isTextCenter, contentArr);
        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (clickListener != null) {
                    clickListener.onLeftClick();
                }
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (clickListener != null) {
                    clickListener.onRightClick();
                }
            }
        });
    }

    /**
     * 显示dialog
     */
    public void showDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                return;
            } else {
                dialog.show();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = Gravity.CENTER;
                attributes.dimAmount = 0.28f;
                window.setAttributes(attributes);
                window.getDecorView().setPadding(0, 0, 0, 0);
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        }
    }

    public interface ClickListener {
        void onLeftClick();

        void onRightClick();
    }

}
