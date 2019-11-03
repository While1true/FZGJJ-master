package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.DefaultStateListener;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Activity.Unit.Info;
import com.lecheng.hello.fzgjj.Bean.BeanPtss;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
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
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class AllSearch extends Activity implements IWSListener {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Bind(R.id.etSearch)
    EditText etSearch;
    List<BeanPtss.DataBean>dataBeans=new ArrayList<>();
    int pager=1;
    private SAdapter sAdapter;
    private String string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4b);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("平台搜索");
        iniAdapter();
        dorequest(pager);
    }

    private void iniAdapter() {
        RecyclerView recyclerView=refreshLayout.getmScroll();
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter = new SAdapter(dataBeans)
                .addType(R.layout.item0d_, new ItemHolder<BeanPtss.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder holder, BeanPtss.DataBean item, final int position) {
                        holder.setText(R.id.tv1, item.getTitle());
                        holder.setText(R.id.tv2, item.getUpdatedate().substring(0, 11));
                        String source = item.getSource();
                        holder.setText(R.id.from, TextUtils.isEmpty(source)?"":("来源："+source));

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String lmdadd = dataBeans.get(position).getLmdadd();
                                if (TextUtils.isEmpty(dataBeans.get(position).getContent())) {
                                    new MyToast(AllSearch.this, "没有新闻内容", 1);
                                } else {
                                    startActivity(new Intent(AllSearch.this, Info.class)
                                            .putExtra("info", (lmdadd == null) ? "" : dataBeans.get(position).getContent())
                                            .putExtra("time", dataBeans.get(position).getUpdatedate().substring(0, 16))
                                            .putExtra("title", dataBeans.get(position).getTitle())
                                            .putExtra("source", dataBeans.get(position).getSource()));
                                }
                            }
                        });

                    }

                    @Override
                    public boolean istype(BeanPtss.DataBean item, int position) {
                        return true;
                    }
                }).setStateListener(new DefaultStateListener() {

                    @Override
                    public void netError(Context context) {
                        dorequest(pager=1);
                    }
                });
        refreshLayout.setListener(new RefreshLayout.Callback1<RefreshLayout.State>() {
            @Override
            public void call(RefreshLayout.State state) {
                if (state == RefreshLayout.State.REFRESHING) {
                    pager = 1;
                    refreshLayout.setCanFooter(true);
                    dorequest(pager);
                } else if (state == RefreshLayout.State.LOADING) {
                   dorequest(++pager);
                }
            }

            @Override
            public void call(RefreshLayout.State state, int scroll) {
                super.call(state, scroll);
                System.out.println("call"+state+"--"+scroll);
            }
        });
        recyclerView.setAdapter(sAdapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        sAdapter.showStateNotNotify(SAdapter.SHOW_LOADING,null);
        sAdapter.notifyItemInserted(0);
    }

    @OnClick({R.id.btnSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
              String serachStr = etSearch.getText().toString();
                if(string!=serachStr) {
                    string=serachStr;
                    dorequest(pager = 1);
                }
                break;
        }
    }

    private void dorequest(int pager) {
        String key = etSearch.getText().toString().replace(" ", "").replace("\n", "");
        WebUtils.doSoapNet("ptss",new RequestParams()
        .add("key",key)
        .add("pageSize",20)
        .add("pageNum",pager))
                .subscribe(new MyObserve<String>(this) {
                    @Override
                    public void onNext(String value) {
                        onWebServiceSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLifeUtils.getInstance().remove(this);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        InputUtils.hideKeyboard(etSearch);
        try {
            final BeanPtss bean = GsonUtil.GsonToBean(s, BeanPtss.class);
            refreshLayout.NotifyCompleteRefresh0();
            if (bean.getData().size() < 1) {
                if(pager==1){
                    refreshLayout.setCanFooter(false);
                    sAdapter.showEmpty();
                }else {
                    refreshLayout.setCanFooter(false);
                    sAdapter.showState(SAdapter.SHOW_NOMORE,"没有更多了");
                }
                return;
            }

            if(pager==1){
                int itemCount = sAdapter.getItemCount();
                dataBeans.clear();
                sAdapter.notifyItemRangeRemoved(0, itemCount);
            }
            int insert=dataBeans.size();
            dataBeans.addAll(bean.getData());
            sAdapter.showStateNotNotify(SAdapter.TYPE_ITEM,null);
            sAdapter.notifyItemInserted(insert);
        } catch (Exception e) {
            e.printStackTrace();
            refreshLayout.NotifyCompleteRefresh0();
            if(pager==1){
                sAdapter.ShowError();
            }
//            httpPost(code, pager);
        }
    }
}
