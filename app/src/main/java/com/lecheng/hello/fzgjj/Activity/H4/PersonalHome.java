package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.lecheng.hello.fzgjj.Activity.H2.ChangeInformation;
import com.lecheng.hello.fzgjj.Activity.ReUI.OpenMessage;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.UpdateUtils;
import com.lecheng.hello.fzgjj.Widget.SettingView;
import com.xiaomi.mipush.sdk.MiPushClient;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Field;

import RxWeb.BaseBean;
import RxWeb.MyObserve;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home42);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人中心");

    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,R.id.tv6,R.id.tv7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                startActivity(new Intent(PersonalHome.this, ChangeInformation.class));
                break;
            case R.id.tv2:
                startActivity(new Intent(PersonalHome.this, ModifyPassword.class));
                break;
            case R.id.tv3:
                new AlertDialog.Builder(this)
                        .setItems(new CharSequence[]{"是否要注销"}, null)
                        .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MySP.saveData(PersonalHome.this, "id", "");
                                MySP.saveData(PersonalHome.this, "rolelevel", "");
                                MySP.saveData(PersonalHome.this, "username", "");
                                MySP.saveData(PersonalHome.this, "channelId", "");
                                MySP.saveData(PersonalHome.this, "pushenable", true);
                                MiPushClient.unregisterPush(PersonalHome.this);
//                                PushManager.stopWork(PersonalHome.this);
                                DataSupport.deleteAll(AccountBean.class);
                                new MyToast(PersonalHome.this, "账号注销成功！", 0);
                                finish();
                            }
                        })
                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                    field.setAccessible(true);
                                    field.set(dialog, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).create().show();
                break;
            case R.id.tv4:
                startActivity(new Intent(this, PushSetting.class));
                break;
            case R.id.tv5:
                ApiService.checkVersion(new MyObserve<BaseBean<UpdateBean>>() {
                    @Override
                    public void onNext(BaseBean<UpdateBean> value) {
                        try {
                            if (value != null && value.getStatus() == 1 && Integer.parseInt(value.getData().getUpdateNumber()) > getPackageManager().getPackageInfo(getPackageName(), 0).versionCode) {
                                UpdateUtils.checkUpdate(PersonalHome.this, value.getData());
                            } else {
                                new MyToast(PersonalHome.this, "已经是最新的了", 0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            new MyToast(PersonalHome.this, "已经是最新的了", 0);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        new MyToast(PersonalHome.this, "已经是最新的了", 0);
                    }
                });
                break;
            case R.id.tv6:
                new MyToast(PersonalHome.this, "该功能正在测试中", 1);
                break;
            case R.id.tv7:
                startActivity(new Intent(this, OpenMessage.class));
                break;
        }
    }
}
