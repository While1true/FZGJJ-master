package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Utils.StateBarUtils;
import com.lecheng.hello.fzgjj.Utils.UpdateUtils;

import RxWeb.BaseAppCompatActivity;
import RxWeb.BaseBean;
import RxWeb.MyObserve;

/**
 * Created by 不听话的好孩子 on 2018/3/12.
 */

public class HomeActivity extends BaseAppCompatActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        StateBarUtils.performTransStateBar(getWindow());
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new HomeFragment()).commit();
        ApiService.checkVersion(new MyObserve<BaseBean<UpdateBean>>() {
            @Override
            public void onNext(BaseBean<UpdateBean> value) {
                try {
                    if (value != null && value.getStatus() == 1 && Integer.parseInt(value.getData().getUpdateNumber()) > getPackageManager().getPackageInfo(getPackageName(), 0).versionCode) {
                        UpdateUtils.checkUpdate(HomeActivity.this, value.getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        ApiService.load(this, null);
    }
}
