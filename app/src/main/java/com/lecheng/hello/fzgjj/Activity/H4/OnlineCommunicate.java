package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.BaseAdapter;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.DefaultStateListener;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.PositionHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanZxlyList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

public class OnlineCommunicate extends Activity implements IWSListener {
    @Bind(R.id.lv1)
    RefreshLayout lv1;
    private int page = 1;
    private SAdapter sAdapter;
    List<BeanZxlyList.DataBeanX.DataBean> datalist = new ArrayList<>();

    private boolean nomore = false, isloading = false;
    private String sum = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_communicate);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("在线问答");
        initAdapter();
        getdata(page);
    }

    private void initAdapter() {
        sAdapter = new SAdapter(1)
                .addType(R.layout.online_ask_item, new PositionHolder() {
                    @Override
                    public void onBind(SimpleViewHolder helper, int position) {
                        BeanZxlyList.DataBeanX.DataBean item = datalist.get(position);
                        helper.setText(R.id.title, (position + 1) + "  " + item.getTitle());
//                          helper.setText(R.id.tv1, item.getName());
                        helper.setText(R.id.ask, item.getTwnr());
                        helper.setText(R.id.answer, item.getHfnr());
                        try {
                            helper.setText(R.id.time_ask, "日期：" + item.getCreatedate().substring(2, 11));
                            helper.setText(R.id.time_answer, "日期：" + item.getUpdatedate().substring(2, 11));
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public boolean istype(int i) {
                        return i < datalist.size();
                    }
                })
                .addType(R.layout.pre_next, new PositionHolder() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, int i) {
                        Button pre = simpleViewHolder.getView(R.id.pre);
                        Button next = simpleViewHolder.getView(R.id.next);
                        final TextView pagex = simpleViewHolder.getView(R.id.page);
                        if (page <= 1) {
                            page = 1;
                            pre.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            pre.setBackgroundResource(R.drawable.button_lightback_selector);
                        }
                        if (page >= Integer.parseInt(sum)) {
                            next.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            next.setBackgroundResource(R.drawable.button_lightback_selector);
                        }
                        pagex.setText("当前第" + page + "页/共" + sum + "页");
                        pre.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!isloading && page > 1) {
                                    isloading = true;
                                    nomore = false;
                                    getdata(--page);
                                }
                            }
                        });
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!isloading && page < Integer.parseInt(sum)) {
                                    isloading = true;
                                    getdata(++page);
                                }
                            }
                        });
                    }

                    @Override
                    public boolean istype(int i) {
                        return true;
                    }
                }).setStateListener(new DefaultStateListener() {
                    @Override
                    public void netError(Context context) {
                        getdata(page);
                    }
                });
        sAdapter.showStateNotNotify(BaseAdapter.SHOW_LOADING, "");
        RecyclerView recyclerView = lv1.getmScroll();
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(OnlineCommunicate.this));
        recyclerView.setAdapter(sAdapter);
    }

    private void getdata(int page) {
        new HttpGo().httpWebService(this, this, "zxlyList", new String[]{"pageSize", "pageNum"}, new String[]{2 + "", page + ""});
    }

    @OnClick({R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, Comments.class));
                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        isloading = false;
        try {
            final BeanZxlyList bean = GsonUtil.GsonToBean(s, BeanZxlyList.class);
            List<BeanZxlyList.DataBeanX.DataBean> data = bean.getData().getData();
            sum = bean.getData().getSum();
            if (data == null || data.isEmpty()) {
                if (page == 1) {
                    sAdapter.showEmpty();
                }
            } else {
                datalist.clear();
                datalist.addAll(data);
                sAdapter.setCount(datalist.size() + 1);
                sAdapter.showItem();
            }

        } catch (Exception e) {
            if (page == 1) {
                sAdapter.ShowError();
            }
            sAdapter.notifyDataSetChanged();
            new MyToast(this, "数据解析失败", 0);
        }
    }
}
