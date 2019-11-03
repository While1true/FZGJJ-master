package com.lecheng.hello.fzgjj.Activity.H1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.DefaultStateListener;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.Info;
import com.lecheng.hello.fzgjj.Bean.BeanInfoList;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import RxWeb.BaseFragment;
import RxWeb.GsonUtil;
import RxWeb.MyObserve;
import RxWeb.WebUtils;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class NewsPager extends BaseFragment implements IWSListener {
    public static final String TAG = "NewsPager";
    RefreshLayout refreshLayout;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage, listPage = 1;
    private String code = "";
    private List<BeanInfoList.DataBean> dataBeans = new ArrayList<>();
    private SAdapter sAdapter;

    public NewsPager() {
        if (getArguments() == null)
            setArguments(new Bundle());
    }

    public static NewsPager newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NewsPager pageFragment = new NewsPager();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        setRetainInstance(true);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        initAdapter();
    }

    @Override
    protected void loadLazy() {
        switch (mPage) {
            case 2:
                code = "GZDT";
                break;
            case 3:
                code = "ZCFG";
                break;
            case 1:
                code = "TZGG";
                break;
            case 4:
//                code = "CJWT";
                code = "ZWGK";
                break;
        }
        sAdapter.showStateNotNotify(SAdapter.SHOW_LOADING, null);
        sAdapter.notifyItemInserted(0);
        httpPost(code, listPage);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.unit_fragment_listview;
    }

    private void httpPost(String code, int page) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(4);
//        map.put("code", code.equals("GZDT") ? "GZDT" : "YJCDCX");
        map.put("code", code.equals("ZCFG") ? "YJCDCX" : code);
        map.put("pcode", code);
        map.put("page", page + "");

        WebUtils.doSoapNet(String.class, Api.HOME_NEWS, map)
                .subscribe(new MyObserve<String>(this) {
                    @Override
                    public void onNext(String s) {
                        onWebServiceSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        refreshLayout.NotifyCompleteRefresh0();
                        if (listPage == 1) {
                            sAdapter.ShowError();
                        }
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            final BeanInfoList bean = GsonUtil.GsonToBean(s, BeanInfoList.class);
            refreshLayout.NotifyCompleteRefresh0();
            if (bean.getData().size() < 1) {
                if (listPage == 1) {
                    refreshLayout.setCanFooter(false);
                    sAdapter.showEmpty();
                } else {
                    refreshLayout.setCanFooter(false);
                    sAdapter.showState(SAdapter.SHOW_NOMORE, "没有更多了");
                }
                return;
            }

            if (listPage == 1) {
                int itemCount = sAdapter.getItemCount();
                dataBeans.clear();
                sAdapter.notifyItemRangeRemoved(0, itemCount);
            }
            int insert = dataBeans.size();
            dataBeans.addAll(bean.getData());
            sAdapter.showStateNotNotify(SAdapter.TYPE_ITEM, null);
            sAdapter.notifyItemInserted(insert);
        } catch (Exception e) {
            e.printStackTrace();
            refreshLayout.NotifyCompleteRefresh0();
            if (listPage == 1) {
                sAdapter.ShowError();
            }
//            httpPost(code, listPage);
        }

    }

    private void initAdapter() {

        sAdapter = new SAdapter<>(dataBeans)
                .addType(R.layout.item0d_, new ItemHolder<BeanInfoList.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder holder, BeanInfoList.DataBean item, final int position) {
                        holder.setText(R.id.tv1, item.getTitle());
                        holder.setText(R.id.tv2, item.getUpdatedate().substring(0, 11));
                        String source = item.getSource();
                        holder.setText(R.id.from, TextUtils.isEmpty(source) ? "" : ("来源：" + source));
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String lmdadd = dataBeans.get(position).getLmdadd();
                                if (TextUtils.isEmpty(dataBeans.get(position).getContent())) {
                                    new MyToast(getActivity(), "没有新闻内容", 1);
                                } else {
                                    startActivity(new Intent(getActivity(), Info.class)
                                            .putExtra("info", (lmdadd == null) ? "" : dataBeans.get(position).getContent())
                                            .putExtra("time", dataBeans.get(position).getUpdatedate().substring(0, 16))
                                            .putExtra("title", dataBeans.get(position).getTitle())
                                            .putExtra("source", dataBeans.get(position).getSource()));
                                }
                            }
                        });

                    }

                    @Override
                    public boolean istype(BeanInfoList.DataBean item, int position) {
                        return true;
                    }
                }).setStateListener(new DefaultStateListener() {

                    @Override
                    public void netError(Context context) {
                        listPage = 1;
                        httpPost(code, listPage);
                    }
                });

        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        refreshLayout.setListener(new RefreshLayout.Callback1<RefreshLayout.State>() {
            @Override
            public void call(RefreshLayout.State state) {
                if (state == RefreshLayout.State.REFRESHING) {
                    listPage = 1;
                    refreshLayout.setCanFooter(true);
                    httpPost(code, listPage);
                } else if (state == RefreshLayout.State.LOADING) {
                    httpPost(code, ++listPage);
                }
            }

            @Override
            public void call(RefreshLayout.State state, int scroll) {
                super.call(state, scroll);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sAdapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}