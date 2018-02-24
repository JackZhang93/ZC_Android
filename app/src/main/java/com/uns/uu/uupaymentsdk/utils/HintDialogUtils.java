package com.uns.uu.uupaymentsdk.utils;

import android.annotation.SuppressLint;
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

import java.util.List;

/**
 * Created by zhen.liu on 2018/2/2.
 * 提示框
 */

public class HintDialogUtils {

    private Context mContext;
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

    public HintDialogUtils(Context context, String title, List<String>
            contentArr) {
        this.mContext = context;
        init(title, contentArr);
    }

    public HintDialogUtils(Context context, String title, boolean isTextCenter, List<String>
            contentArr,
                           boolean showLeftBtn, String leftString, boolean showRightBtn, String rightString) {
        this.mContext = context;
        init(title, isTextCenter, contentArr, showLeftBtn, leftString, showRightBtn, rightString);
    }

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        if (dialog_title != null) {
            dialog_title.setText(TextUtils.isEmpty(title) ? "提示" : title);
        }
    }

    /**
     * 设置内容，以数组的形式传递
     *
     * @param isTextCenter 文本是否居中
     * @param contentArr   内容
     */
    public void setContentArr(boolean isTextCenter, List<String> contentArr) {
        //非空判断
        if (ll_center_container != null) {
            ll_center_container.removeAllViews();
        }
        //集合非空判断
        if (contentArr != null && contentArr.size() > 0) {
            int size = contentArr.size();
            for (int i = 0; i < size; i++) {
                TextView textView = getTextView(isTextCenter, contentArr.get(i), LinearLayout
                        .LayoutParams.MATCH_PARENT);
                LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.TOP);
                TextView tip = getTextView(isTextCenter, (i + 1) + "、", LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(tip);
                linearLayout.addView(textView);
                if (ll_center_container != null) {
                    ll_center_container.addView(linearLayout);
                }
            }
        }
    }

    /**
     * 添加自己定义的view
     *
     * @param isTextCenter *
     * @param contentArr   *
     * @param selfView     view
     */
    public void setSelfView(boolean isTextCenter, String[] contentArr, View selfView, boolean
            showBar) {
        ll_center_container.removeAllViews();
        ll_center_container.addView(selfView);
        if (contentArr != null) {
            for (int i = 0; i < contentArr.length; i++) {
                if (contentArr.length > 1) {
                    TextView textView = getTextView(isTextCenter, contentArr[i], LinearLayout.LayoutParams.MATCH_PARENT);
                    LinearLayout linearLayout = new LinearLayout(mContext);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setGravity(Gravity.TOP);
                    if (showBar) {
                        TextView tip = getTextView(isTextCenter, (i + 1) + "、", LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearLayout.addView(tip);
                    }
                    linearLayout.addView(textView);
                    ll_center_container.addView(linearLayout);
                } else {
                    TextView textView = getSingleTextView(isTextCenter, contentArr[i], LinearLayout.LayoutParams
                            .MATCH_PARENT);
                    ll_center_container.addView(textView);
                }
            }
        }
    }

    @SuppressLint("RtlHardcoded")
    private TextView getTextView(boolean isTextCenter, String text, int width) {
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimensionPixelSize(R.dimen.dimens_14));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 5;
        if (isTextCenter) {
            textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        } else {
            textView.setGravity(Gravity.START | Gravity.LEFT);
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @SuppressLint("RtlHardcoded")
    private TextView getSingleTextView(boolean isTextCenter, String text, int width) {
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimensionPixelSize(R.dimen.dimens_14));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 5;
        if (isTextCenter) {
            textView.setGravity(Gravity.CENTER);
        } else {
            textView.setGravity(Gravity.START | Gravity.LEFT);
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /**
     * 设置左边和右边按钮的文字与显示
     *
     * @param showLeftBtn 显示左边的按钮
     * @param leftString  显示右边的按钮
     */
    public void setLeftOrRight(boolean showLeftBtn, String leftString, boolean showRightBtn, String rightString) {
        if (showLeftBtn && showRightBtn) {
            dialog_cancel.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_btn_left_select));
            dialog_ok.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_btn_right_select));
        } else if (showLeftBtn) {
            dialog_cancel.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_btn_left_or_right_select));
            //显示取消按钮
            dialog_cancel.setVisibility(View.VISIBLE);
            //隐藏提示按钮
            dialog_ok.setVisibility(View.GONE);
            dialog_cancel.setText(TextUtils.isEmpty(leftString) ? "取消" : leftString);
        } else if (showRightBtn) {
            dialog_ok.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_btn_left_or_right_select));
            //隐藏取消按钮
            dialog_cancel.setVisibility(View.GONE);
            //隐藏分割线
            view_center_line.setVisibility(View.GONE);
            //显示提示框
            dialog_ok.setVisibility(View.VISIBLE);
            dialog_ok.setText(TextUtils.isEmpty(rightString) ? "知道了" : rightString);
        }
        if (showLeftBtn) {
            dialog_cancel.setVisibility(View.VISIBLE);
            dialog_cancel.setText(TextUtils.isEmpty(leftString) ? "取消" : leftString);
        } else {
            dialog_cancel.setVisibility(View.GONE);
            view_center_line.setVisibility(View.GONE);
        }
        if (showRightBtn) {
            dialog_ok.setVisibility(View.VISIBLE);
            dialog_ok.setText(TextUtils.isEmpty(rightString) ? "知道了" : rightString);
        } else {
            dialog_ok.setVisibility(View.GONE);
            view_center_line.setVisibility(View.GONE);
        }
    }

    private void init(String title, List<String> contentArr) {
        this.init(title, true, contentArr, true, "", true, "");
    }

    private void init(String title, boolean isTextCenter, List<String> contentArr, boolean showLeftBtn, String leftString, boolean showRightBtn, String rightString) {
        View contentView = View.inflate(mContext, R.layout.dialog_unreceive_verify_code, null);
        dialog = new AlertDialog.Builder(mContext, R.style.dialog).create();
        dialog.setView(contentView);
        dialog.setCanceledOnTouchOutside(false);
        dialog_title = contentView.findViewById(R.id.dialog_title);
        ll_center_container = contentView.findViewById(R.id.ll_center_container);
        dialog_ok = contentView.findViewById(R.id.dialog_ok);
        view_center_line = contentView.findViewById(R.id.view_center_line);
        dialog_cancel = contentView.findViewById(R.id.dialog_cancel);
        setTitle(title);
        setLeftOrRight(showLeftBtn, leftString, showRightBtn, rightString);
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
                dialog.dismiss();
                showDialog();
            } else {
                if (!dialog.isShowing()) {
                    dialog.show();
                    Window window = dialog.getWindow();
                    if (window != null) {
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.gravity = Gravity.CENTER;
                        attributes.dimAmount = 0.28f;
                        window.setAttributes(attributes);
                        window.getDecorView().setPadding(0, 0, 0, 0);
                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    }
                }

            }
        }
    }

    public interface ClickListener {
        void onLeftClick();

        void onRightClick();
    }

}
