package com.uns.uu.uupaymentsdk;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhen.liu on 2018/2/5.
 * 银行卡问题界面
 */

public class BankQuestionActivity extends Activity {

    private WebView webView;
    private TextView tv_title;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_question);
        initView();
    }

    private void initView() {
        webView = findViewById(R.id.wv_web);
        tv_title = findViewById(R.id.tv_title);
        iv_back = findViewById(R.id.iv_back);
        tv_title.setText("银行卡问题");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webView.getSettings().setUseWideViewPort(true);//自适应
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDisplayZoomControls(false);// 设定缩放控件隐藏
        webView.setVerticalScrollBarEnabled(false); // 垂直不显示
        webView.getSettings().setSaveFormData(true);//是否保存表单
        webView.getSettings().setBuiltInZoomControls(true);//是否支持缩放
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("http://192.168.9.82:8080/UU/commonProblem.html");
       /* //00为生产环境，01为测试环境 ，03为UAT环境
        if (Configs.getWalletModel().equals("01")) {
            webView.loadUrl("http://192.168.9.82:8080/UU/commonProblem.html");
        } else if (Configs.getWalletModel().equals("03")){
            webView.loadUrl("http://uu.weidu7.com/UU/commonProblem.html");
        }else{
            webView.loadUrl("http://uu.wedo77.com/UU/commonProblem.html");
        }*/

        webView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("about:blank");
            Toast.makeText(BankQuestionActivity.this, "网络连接慢", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        webView.loadUrl("about:blank");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
