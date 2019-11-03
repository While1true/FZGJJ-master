package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.R;

import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * Created by vange on 2017/12/27.
 */

public class RepaymentActivity extends BaseTitleActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.refreshlayout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RefreshLayout refreshLayout= (RefreshLayout) findViewById(R.id.refreshLayout);
        RecyclerView recyclerView=refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SAdapter sAdapter=new SAdapter<>();
        recyclerView.setAdapter(sAdapter);

    }
}
