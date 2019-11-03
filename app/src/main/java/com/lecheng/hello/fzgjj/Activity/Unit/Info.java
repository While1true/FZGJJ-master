package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


import butterknife.Bind;
import butterknife.ButterKnife;

public class Info extends AppCompatActivity {


    @Bind(R.id.wv)
    WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_info);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("详情");
        String title = getIntent().getStringExtra("title");
        String time = getIntent().getStringExtra("time");
        try {
            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);//设定支持viewport
//            settings.setTextZoom(280);
            settings.setMinimumFontSize(SizeUtils.dp2px(15));
            settings.setLoadWithOverviewMode(true);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);//设定支持缩放
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView webView, String s) {
                    super.onPageFinished(webView, s);
                    int i = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    webView.measure(i,i);
                }
            });

            String content = getIntent().getStringExtra("info");
            String source = getIntent().getStringExtra("source");
            String content1 = content.replace("line-height", "what").replace("font-size", "what2").replaceAll("<br/>", "");
            int margin = SizeUtils.dp2px(13);
            String titlehtml="<h2 align=\"center\"  style=\"margin:"+ margin +" "+margin+" "+margin/3+" "+margin+";\">"+title+"</h2><p align=\"center\" style=\"margin:0 auto;color:#666666;\">"+"发布时间:" + time.substring(2, 16)+"</p><hr style=\" height:2px;border:none;border-top:2px solid #EDEDED;\"></hr>";

            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "onCreate: " + "信息来源：" + titlehtml+content1 + source);
            wv.loadDataWithBaseURL("about:blank",
                    titlehtml+content1 + (TextUtils.isEmpty(source) ? "" : ("信息来源：" + source)),
                    "text/html", "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
