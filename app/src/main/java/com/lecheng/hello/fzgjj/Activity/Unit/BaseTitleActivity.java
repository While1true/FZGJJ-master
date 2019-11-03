package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.R;

import RxWeb.RxLifeUtils;


/**
 * Created by vange on 2017/9/12.
 */

public abstract class BaseTitleActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private TextView tv_title;
    private ImageView iv_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titlebar_layout);
        LinearLayout content = (LinearLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        tv_title = (TextView) findViewById(R.id.title);
        iv_menu = (ImageView) findViewById(R.id.menu);
        setSupportActionBar(toolbar);
        findViewById(R.id.ic_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationClicked();
            }
        });
        if (getContentLayoutId() != 0)
            getLayoutInflater().inflate(getContentLayoutId(), content, true);
        initView(savedInstanceState);

    }

    protected abstract int getContentLayoutId();

    /**
     * 初始化组件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 中间title
     *
     * @return
     */
    protected void setTitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 右上角菜单
     *
     * @param resId
     * @param listener
     */
    protected void setMenu(int resId, View.OnClickListener listener) {
        iv_menu.setVisibility(View.VISIBLE);
        if (resId != 0)
            iv_menu.setImageResource(resId);
        iv_menu.setOnClickListener(listener);
    }

    /**
     * 右上角menu菜单
     *
     * @param menuid
     * @param listener
     */
    protected void inflateMenu(int menuid, Toolbar.OnMenuItemClickListener listener) {
        toolbar.inflateMenu(menuid);
        toolbar.setOnMenuItemClickListener(listener);
    }

    /**
     * 导航返回键按钮
     */
    protected void onNavigationClicked() {
        finish();
    }

    protected void replaceRoot(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.root,fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLifeUtils.getInstance().remove(this);
    }
}
