package com.lecheng.hello.fzgjj.Interface;

import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * Created by vange on 2017/12/20.
 */

public abstract class RefreshingListener extends RefreshLayout.Callback1<RefreshLayout.State> {
    public RefreshingListener() {
        super();
    }

    @Override
    public void call(RefreshLayout.State state) {
        if (state.ordinal() == 0)
            onRefreshing();
        else if (state.ordinal() == 1)
            onLoading();
    }

    public abstract void onRefreshing();

    public abstract void onLoading();
}
