package com.uns.uu.uupaymentsdk.weight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.uns.uu.uupaymentsdk.R;

import java.lang.reflect.Method;

/**
 * Created by zhaoyan on 2018/2/23.
 * 自定义EdiText
 */

@SuppressLint("AppCompatCustomView")
public class UUEditText extends AppCompatEditText implements KeyboardView.OnKeyboardActionListener, View
        .OnClickListener {

    private View mDecorView;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;
    private PopupWindow mKeyboardWindow;

    public UUEditText(Context context) throws IllegalAccessException {
        this(context, null);
    }

    public UUEditText(Context context, AttributeSet attrs) throws IllegalAccessException {
        this(context, attrs, R.attr.editTextStyle);
    }

    public UUEditText(Context context, AttributeSet attrs, int defStyleAttr) throws IllegalAccessException {
        super(context, attrs, defStyleAttr);
        initKeyBoard(context, attrs);
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (!focused) {
            hideKeyboard();
        } else {
            hideSysInput();
            showKeyboard();
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDecorView = ((Activity) getContext()).getWindow().getDecorView();
        hideSysInput();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        hideKeyboard();
        mKeyboardWindow = null;
        mKeyboardView = null;
        mKeyboard = null;
        mDecorView = null;
    }

    /**
     * 初始化键盘
     *
     *
     */
    @SuppressLint("InflateParams")
    private void initKeyBoard(Context context, AttributeSet attrs) throws IllegalAccessException {
        @SuppressLint("CustomViewStyleable") TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KeyBoard);
        if (!array.hasValue(R.styleable.KeyBoard_xml)) {
            throw new IllegalAccessException("xml is not null");
        }

        int resourceId = array.getResourceId(R.styleable.KeyBoard_xml, 0);
        mKeyboardView = (KeyboardView) LayoutInflater.from(context).inflate(R.layout.uns_key_board, null);
        mKeyboard = new Keyboard(context, resourceId);
        mKeyboardView.setKeyboard(mKeyboard);
        //禁止预览
        mKeyboardView.setPreviewEnabled(false);

        //设置键盘事件
        mKeyboardView.setOnKeyboardActionListener(this);
        //将keyboardview放入popupwindow方便显示以及位置调整。
        mKeyboardWindow = new PopupWindow(mKeyboardView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        array.recycle();
        //设置点击事件，点击后键盘弹起，系统键盘收起。
        setOnClickListener(this);
        //屏蔽当前edittext的系统键盘
        notSystemSoftInput();


    }

    /**
     * 屏蔽系统输入法 sdk>10
     */
    private void notSystemSoftInput() {
        ((Activity) getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(this, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏系统键盘
     */
    private void hideSysInput() {
        if (null != this.getWindowToken()) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            if (null != imm) {
                imm.hideSoftInputFromInputMethod(this.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    /**
     * 显示自定义键盘
     */
    private void showKeyboard() {
        if (null != mKeyboardWindow) {
            if (!mKeyboardWindow.isShowing()) {
                mKeyboardWindow.showAtLocation(mDecorView, Gravity.BOTTOM, 0, 0);
            }
        }
    }

    /**
     * 隐藏自定义键盘
     */
    private void hideKeyboard() {
        if (null != mKeyboardWindow && mKeyboardWindow.isShowing()) {
            mKeyboardView.handleBack();
            mKeyboardWindow.dismiss();
        }
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

        Editable editable = this.getText();
        //获取光标偏移量下标
        int startIndex = this.getSelectionStart();
        switch (primaryCode) {
            case Keyboard.KEYCODE_CANCEL:// 隐藏键盘
                hideKeyboard();
                break;
            case Keyboard.KEYCODE_DELETE:// 回退
                if (editable != null && editable.length() > 0) {
                    if (startIndex > 0) {
                        editable.delete(startIndex - 1, startIndex);
                    }
                }
                break;
            default:
                editable.insert(startIndex, Character.toString((char) primaryCode));
                break;
        }
    }


    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    @Override
    public void onClick(View v) {
        requestFocus();
//        requestFocusFromTouch();
        hideSysInput();
        showKeyboard();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null != mKeyboardWindow && mKeyboardWindow.isShowing()) {

                hideKeyboard();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
