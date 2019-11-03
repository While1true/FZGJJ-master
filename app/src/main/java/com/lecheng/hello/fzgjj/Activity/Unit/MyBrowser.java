package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lecheng.hello.fzgjj.R;

/*
* */
public class MyBrowser extends BaseTitleActivity {
    private WebView webView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.utils_browser;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("1-30年简表");
        init();
    }

    private void init() {

        setProgressBarVisibility(true);
        webView = (WebView) findViewById(R.id.house_webview);

        webView.setWebViewClient(new WebViewClient() {
            // Load opened URL in the application instead of standard browser
            // application
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            // Set progress bar during loading
            public void onProgressChanged(WebView view, int progress) {
                MyBrowser.this.setProgress(progress * 100);
            }
        });

        // Enable some feature like Javascript and pinch zoom
        WebSettings websettings = webView.getSettings();
        webView.getSettings().setTextZoom(60);
        websettings.setJavaScriptEnabled(true);                        // Warning! You can have XSS vulnerabilities!
        websettings.setBuiltInZoomControls(true);

        webView.loadUrl(getIntent().getStringExtra("web_url"));
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack(); // goBack()表示返回WebV
        else finish();
//        webView.goBack();//浏览器返回
//        finish();//浏览器退出关闭
    }


    @Override
    public void finish() {
        try {
            webView.removeAllViews();
            webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();

        super.finish();
    }
}
