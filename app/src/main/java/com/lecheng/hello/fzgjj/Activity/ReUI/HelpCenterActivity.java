package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.os.Bundle;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;

/**
 * Created by 不听话的好孩子 on 2018/3/12.
 */

public class HelpCenterActivity extends BaseTitleActivity {
    @Override
    protected int getContentLayoutId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("在线咨询");
        replaceRoot(new HelpCenterFragment());
    }
}
