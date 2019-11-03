package com.lecheng.hello.fzgjj.Activity.H3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.BaseAdapter;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.DefaultStateListener;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Activity.Unit.Info;
import com.lecheng.hello.fzgjj.Bean.BeanInfoList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Interface.RefreshingListener;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.InputUtils;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.ArrayList;
import java.util.List;

import RxWeb.GsonUtil;

import RxWeb.MyObserve;
import RxWeb.RxLifeUtils;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;

public class PolicyDocs extends AppCompatActivity implements IWSListener {
    public static final String TAG = "PolicyDocs";
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.etSearch)
    EditText etSearch;
    int pager = 1;

    List<BeanInfoList.DataBean> beans = new ArrayList<>();

    boolean isloading = false;
    private SAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3c);
        ButterKnife.bind(this);
        initAdapter();
        try {
            ((ActionBar) getFragmentManager().findFragmentById(R.id.frag)).setTitle(getIntent().getStringExtra("gjz2"));
            http(1);
        } catch (Exception e) {
            new MyToast(this, "信息获取失败，请重试", 0);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLifeUtils.getInstance().remove(this);
    }

    private void initAdapter() {
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new ScaleInRightAnimator());
        sAdapter = new SAdapter<>(beans)
                .addType(R.layout.item0d_, new ItemHolder<BeanInfoList.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, final BeanInfoList.DataBean dataBean, int i) {
                        simpleViewHolder.setText(R.id.tv1, dataBean.getTitle());
                        simpleViewHolder.setText(R.id.tv2, dataBean.getUpdatedate().substring(2, 11));
                        String source = dataBean.getSource();
                        simpleViewHolder.setText(R.id.from, TextUtils.isEmpty(source)?"":("来源："+source));

                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(PolicyDocs.this, Info.class)
                                        .putExtra("time", dataBean.getUpdatedate())
                                        .putExtra("info", dataBean.getContent())
                                        .putExtra("title", dataBean.getTitle())
                                        .putExtra("source", dataBean.getSource()));
                            }
                        });
                    }

                    @Override
                    public boolean istype(BeanInfoList.DataBean dataBean, int i) {
                        return true;
                    }
                }).setStateListener(new DefaultStateListener() {
                    @Override
                    public void netError(Context context) {
                        http(1);
                    }
                });
        refreshLayout.setListener(new RefreshingListener() {
            @Override
            public void onRefreshing() {
                if (!isloading) {
                    isloading = true;
                    pager = 1;
                    refreshLayout.setCanFooter(true);
                    http(1);
                }
            }

            @Override
            public void onLoading() {
                if (!isloading) {
                    isloading = true;
                    pager++;
                    http(pager);
                }
            }
        });
        recyclerView.setAdapter(sAdapter);
        sAdapter.showStateNotNotify(SAdapter.SHOW_LOADING, null);
        sAdapter.notifyItemInserted(0);
    }

    private void http(final int pager) {
        WebUtils.doSoapNet("cxzcfg", new RequestParams()
                .add("key", etSearch.getText() + "")
                .add("pageSize", 20)
                .add("pageNum", pager))
                .subscribe(new MyObserve<String>(this) {
            @Override
            public void onNext(String value) {
                onWebServiceSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                if (pager == 1) {
                    sAdapter.ShowError();
                } else {
                    new MyToast(PolicyDocs.this, "信息获取失败，请重试", 0);
                }
            }
        });
    }

    @Override
    public void onWebServiceSuccess(String s) {
        refreshLayout.NotifyCompleteRefresh0();
        isloading = false;
        try {
            if (pager == 1) {
                int itemCount = sAdapter.getItemCount();
                beans.clear();
                sAdapter.notifyItemRangeRemoved(0, itemCount);
            }
            final BeanInfoList bean = GsonUtil.GsonToBean(s, BeanInfoList.class);
            List<BeanInfoList.DataBean> data = bean.getData();
            if (data.size() < 1 && pager == 1) {
                sAdapter.showEmpty();
                return;
            }
                int size = beans.size();
                beans.addAll(data);
                sAdapter.showStateNotNotify(data.size() < 20?BaseAdapter.SHOW_NOMORE:BaseAdapter.TYPE_ITEM, "没有更多了");
                sAdapter.notifyItemInserted(size);
                if(data.size()<20){
                    refreshLayout.setCanFooter(false);
                }
        } catch (Exception e) {
            new MyToast(this, "信息获取失败，请重试", 0);
        }
    }

    @OnClick({R.id.btnSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                refreshLayout.setCanFooter(true);
                http(pager=1);
                break;
        }
    }
}
