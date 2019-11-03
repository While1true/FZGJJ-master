package com.lecheng.hello.fzgjj.Activity.H3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.DefaultStateListener;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Activity.Unit.InfoList;
import com.lecheng.hello.fzgjj.Bean.BeanWzcdList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

public class GuideInfo extends AppCompatActivity implements IWSListener {
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.llSearch)
    LinearLayout llSearch;
    private int i = 1;
    private List<BeanWzcdList.DataBean> list = new ArrayList<>();
    private SAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3c);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("办事指南");
        initAdapter();
        httpPost(this, "BSZN", "2");//发起网络请求
        llSearch.setVisibility(View.GONE);
        ((ViewGroup.MarginLayoutParams)refreshLayout.getLayoutParams()).topMargin= SizeUtils.dp2px(8);
    }

    private void initAdapter() {
        refreshLayout.setCanFooter(false);
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter = new SAdapter<BeanWzcdList.DataBean>(list)
                .addType(R.layout.item0d, new ItemHolder<BeanWzcdList.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder holder, final BeanWzcdList.DataBean item, int position) {
                        holder.setText(R.id.tv1, item.getTitle());
                        holder.setText(R.id.tv2, "");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(GuideInfo.this, InfoList.class)
                                        .putExtra("gjz", item.getCode())
                                        .putExtra("gjz2", item.getTitle()));
                            }
                        });
                    }

                    @Override
                    public boolean istype(BeanWzcdList.DataBean item, int position) {
                        return true;
                    }
                }).setStateListener(new DefaultStateListener() {
                    @Override
                    public void netError(Context context) {
                        httpPost(context, "BSZN", "2");
                    }
                });
        recyclerView.setAdapter(sAdapter);
        refreshLayout.setListener(new RefreshLayout.Callback1<RefreshLayout.State>() {
            @Override
            public void call(RefreshLayout.State state) {
                if(state== RefreshLayout.State.REFRESHING){
                    httpPost(GuideInfo.this, "BSZN", "2");
                }
            }
        });
    }

    private void httpPost(Context c, String code, String lv) {
        String[] key = {"code", "levels"};
        String[] value = {code, lv};
        new HttpGo().httpWebService(c, this, "wzcdList", key, value);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        list.clear();
        refreshLayout.NotifyCompleteRefresh0();
        try {
            final BeanWzcdList bean = GsonUtil.GsonToBean(s, BeanWzcdList.class);
            List<BeanWzcdList.DataBean> data = bean.getData();
            if (data == null || data.size() < 1) {
                sAdapter.showEmpty();
                return;
            }
            list.addAll(data);
            sAdapter.showItem();
        } catch (Exception e) {
            new MyToast(GuideInfo.this, "加载失败,请重试!", 1);
        }
    }
}
