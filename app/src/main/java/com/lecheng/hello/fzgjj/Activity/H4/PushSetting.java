package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PushSetting extends Activity implements IWSListener {
    @Bind(R.id.tg1)
    ToggleButton tg1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4a);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("消息推送设置");
        try {
            boolean pushenable = (boolean) MySP.loadData(PushSetting.this, "pushenable", true);
            tg1.setChecked(pushenable);
            tg1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        MySP.saveData(PushSetting.this, "pushenable", true);
                        MiPushClient.resumePush(PushSetting.this, null);
                    } else {
                        MiPushClient.pausePush(PushSetting.this, null);
                        MySP.saveData(PushSetting.this, "pushenable", false);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv2, R.id.tv3, R.id.tv4})
    public void onClick(View view) {
        Intent intent = new Intent(this, PushMessageActivity.class);
        ;
        switch (view.getId()) {
            case R.id.tv2:
                intent = intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.tv3:
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.tv4:
                Timer timer = new Timer();
                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();  // 需要做的事:发送消息
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                };
                timer.schedule(task2, 2000);
                break;
        }
    }

    private Handler handler = new Handler() {   //消息接收器
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                new MyToast(PushSetting.this, "推送", 1);
                NotificationManager manager = (NotificationManager) PushSetting.this.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(PushSetting.this)
                        .setSmallIcon(R.drawable.ic_home1)
                        .setContentTitle("推送")
                        .setContentText("推送内容")
                        .setTicker("推送内容2");
                Notification notification2 = mBuilder.build();
                manager.notify(1, notification2);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onWebServiceSuccess(String s) {
        new MyToast(this, s, 1);
    }
}