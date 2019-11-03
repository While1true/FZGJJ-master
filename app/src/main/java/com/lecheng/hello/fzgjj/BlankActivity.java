package com.lecheng.hello.fzgjj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.ReUI.HomeActivity;
import com.lecheng.hello.fzgjj.Bean.Access;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Receiver.MiPushReceiver;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.StateBarUtils;

import java.util.concurrent.TimeUnit;

import RxWeb.MyObserve;
import RxWeb.RxLifeUtils;
import RxWeb.WebUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by vange on 2017/9/1.
 */

public class BlankActivity extends AppCompatActivity {
    boolean canVisit = false;
    private Runnable action = new Runnable() {
        @Override
        public void run() {
            if (!canVisit)
                finish();
        }
    };
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StateBarUtils.hideBottomUIMenu(getWindow());
        super.onCreate(savedInstanceState);
//        StateBarUtils.performTransStateBar(getWindow());
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        /**
         * 上传ip地址
         */
        ApiService.uploadIp(this, new MyObserve(this) {
            @Override
            public void onNext(Object value) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        /**
         * 检查是否允许访问
         * 为减低网络差的情况下的影响3秒发送3次 来检查
         */
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .flatMap(new Function<Long, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Long integer) throws Exception {
                        return WebUtils.doSoapNet(Access.class, Api.accessControl, null);
                    }
                }).cast(Access.class).subscribe(new MyObserve<Access>(this) {
            @Override
            public void onNext(Access value) {
                RxLifeUtils.getInstance().remove(BlankActivity.this);
                    if (value.getStatus() == 1) {
                        canVisit = true;
                        DoNext();
                    } else {
                        setContentView(R.layout.testlayout);
                        TextView tv = (TextView) findViewById(R.id.tv);
                        tv.setText(value.getMsg());
                        tv.removeCallbacks(action);
                        tv.postDelayed(action, 5000);
                    }
            }

            @Override
            public void onError(Throwable e) {
                if (handler == null) {
                    handler = new Handler();
                    handler.postDelayed(action, 2000);
                }
                new MyToast(BlankActivity.this, "无法访问服务器", 1);

            }
        });
//        ApiService.accessControl();
    }

    private void DoNext() {
        String channelId = MySP.loadData(this, "channelId", "").toString();
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "channelId--", "onCreate: " + channelId);
        if (!TextUtils.isEmpty(channelId)) {
            MiPushReceiver.notify(this, channelId);
        }

        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserve<Long>(BlankActivity.this) {
                    @Override
                    public void onNext(Long value) {
                        startActivity(new Intent(BlankActivity.this, HomeActivity.class));
//                        ContextCompat.startActivity(BlankActivity.this,new Intent(BlankActivity.this, HomeActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(BlankActivity.this).toBundle());
                        finish();
//                        overridePendingTransition(0,0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)//设置动画效果
//                .replace(android.R.id.content, new Splash())
//                .commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(action);
        }
        RxLifeUtils.getInstance().remove(this);
    }
}
