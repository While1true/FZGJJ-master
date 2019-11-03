package com.lecheng.hello.fzgjj.Activity.Unit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Bean.BeanInfoList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.ArrayList;
import java.util.List;

import RxWeb.GsonUtil;

import RxWeb.MyObserve;
import RxWeb.RxLifeUtils;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

public class InfoList extends AppCompatActivity implements IWSListener {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    List<BeanInfoList.DataBean> list = new ArrayList<>();
    boolean isloading = false;
    int pager = 1;
    private String gjz;
    private SAdapter sAdapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        gjz = intent.getStringExtra("gjz");
//        pager=1;
//        list.clear();
//        doHttp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_infolist);
        ButterKnife.bind(this);
        ActionBar bar = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("gjz2");
            bar.setTitle(title);
            gjz = intent.getStringExtra("gjz");

        }
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter = new SAdapter(list)
                .addType(R.layout.item0d_, new ItemHolder<BeanInfoList.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder holder, BeanInfoList.DataBean item, final int position) {
                        holder.setText(R.id.tv1, item.getTitle());
                        holder.setText(R.id.tv2, item.getUpdatedate().substring(0, 10));
                        String source = item.getSource();
                        holder.setText(R.id.from, TextUtils.isEmpty(source)?"":("来源："+source));
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(InfoList.this, Info.class)
                                        .putExtra("time", list.get(position).getUpdatedate())
                                        .putExtra("info", list.get(position).getContent())
                                        .putExtra("title", list.get(position).getTitle())
                                        .putExtra("source", list.get(position).getSource()));
                            }
                        });
                    }

                    @Override
                    public boolean istype(BeanInfoList.DataBean item, int position) {
                        return true;
                    }
                });
        recyclerView.setItemAnimator(new ScaleInBottomAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(sAdapter);
        refreshLayout.setListener(new RefreshLayout.Callback1<RefreshLayout.State>() {
            @Override
            public void call(RefreshLayout.State state) {
                if (!isloading) {
                    if (state == RefreshLayout.State.REFRESHING) {
                        refreshLayout.setCanFooter(true);
                        isloading = true;
                        pager = 1;
                        doHttp();
                    } else if (state == RefreshLayout.State.LOADING) {
                        isloading = true;
                        pager++;
                        doHttp();
                    }
                }

            }
        });
        sAdapter.showStateNotNotify(SAdapter.SHOW_LOADING,null);
        sAdapter.notifyItemInserted(0);
        doHttp();

    }

    private void doHttp() {
        WebUtils.doSoapNet("infoList",new RequestParams()
        .add("code",gjz)
        .add("pcode")
        .add("page",pager)).subscribe(new MyObserve<String>(this) {
            @Override
            public void onNext(String value) {
                onWebServiceSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                refreshLayout.NotifyCompleteRefresh0();
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
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "onWebServiceSuccess: " + s);
        isloading = false;
        refreshLayout.NotifyCompleteRefresh0();

        try {
            final BeanInfoList bean = GsonUtil.GsonToBean(s, BeanInfoList.class);
            if(bean.getData().size()<1){
               if(pager==1){
                   sAdapter.showEmpty();
               }else {
                   refreshLayout.setCanFooter(false);
                   sAdapter.showState(SAdapter.SHOW_NOMORE,"没有更多了");
               }
                return;
            }
            if(pager==1){
                int size = sAdapter.getItemCount();
                list.clear();
                sAdapter.notifyItemRangeRemoved(0,size);
            }
            int insert=list.size();
            list.addAll(bean.getData());
            sAdapter.showStateNotNotify(SAdapter.TYPE_ITEM,null);
            sAdapter.notifyItemInserted(insert);
        } catch (Exception e) {
            if(pager==1){
                sAdapter.ShowError();
            }
                refreshLayout.NotifyCompleteRefresh0();
            new MyToast(this, "信息获取失败，请重试", 0);
        }
    }
}
