package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.os.Bundle;
import android.view.View;

import com.lecheng.hello.fzgjj.Activity.H1.NewsFragment;
import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Activity.Unit.Menu;
import com.lecheng.hello.fzgjj.R;

/**
 * Created by vange on 2017/12/22.
 */

public class NewsActivity extends BaseTitleActivity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.news_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("政策资讯");
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new NewsFragment()).commit();
        setMenu(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu = new Menu(NewsActivity.this);
                menu.showPopupWindow(v);
            }
        });
    }
}
