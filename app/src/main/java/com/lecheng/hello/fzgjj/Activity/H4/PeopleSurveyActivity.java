package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.ck.hello.nestrefreshlib.View.RefreshViews.SRecyclerView;
import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Bean.SurveyCategory;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.RefreshingListener;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Widget.MyDecorate;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import RxWeb.MyObserve;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * Created by vange on 2017/9/18.
 */

public class PeopleSurveyActivity extends BaseTitleActivity {

    private RefreshLayout refreshLayout;
    private List<SurveyCategory> lis;
    private SAdapter sAdapter;
    private RecyclerView recyclerView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.srecyclerview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("请选择一项调查");
        lis = new ArrayList<>(0);

        refreshLayout = (RefreshLayout) findViewById(R.id.srecyclerview);
        recyclerView = refreshLayout.getmScroll();
        recyclerView.addItemDecoration(new MyDecorate(0xfff0f0f0, SizeUtils.dp2px(10)));
        refreshLayout.setListener(new RefreshingListener() {
            @Override
            public void onRefreshing() {
                getData();
            }

            @Override
            public void onLoading() {

            }
        });
        refreshLayout.setRefreshing();

    }

    /**
     * 获取数据
     */
    private void getData() {
        ApiService.getMyzjList(new MyObserve<List<SurveyCategory>>(this) {
            @Override
            public void onNext(List<SurveyCategory> lists) {
                refreshLayout.NotifyCompleteRefresh0();
                if (lists != null && !lists.isEmpty()) {
                    Collections.sort(lists, new Comparator<SurveyCategory>() {
                        @Override
                        public int compare(SurveyCategory o1, SurveyCategory o2) {
                            int i = o1.getOnlines() == 2 ? 1 : 0;
                            return i;
                        }
                    });
                    sAdapter = new SAdapter(lists);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PeopleSurveyActivity.this));
                    recyclerView.setAdapter(sAdapter
                            .addType(R.layout.survey_item, new ItemHolder<SurveyCategory>() {
                                @Override
                                public void onBind(SimpleViewHolder holder, final SurveyCategory s, int i) {
                                    holder.setText(R.id.survey_title, s.getTitle());
                                    holder.setText(R.id.start_endTime, s.getTerm());
                                    if (Constance.DEBUGTAG)
                                        Log.i(Constance.DEBUG, "onNext: " + s.getTitle());
                                    String state = null;
                                    if (s.getOnlines() == 0)
                                        state = "已结束";
                                    else if (s.getOnlines() == 1)
                                        state = "未开始";
                                    else
                                        state = "进行中";

                                    holder.setText(R.id.survey_state, state);
                                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (s.getOnlines() != 2) {
                                                new MyToast(v.getContext(), "只能参与正在进行中的调查", 0);
                                            } else {
                                                Intent intent = new Intent(v.getContext(), SurveyCollect.class);
                                                intent.putExtra("id", s.getId());
                                                intent.putExtra("title", s.getTitle());
                                                startActivityForResult(intent, 200);
                                            }
                                        }
                                    });
                                }

                                @Override
                                public boolean istype(SurveyCategory surveyCategory, int i) {
                                    return true;
                                }
                            }));
                } else {
                    sAdapter = new SAdapter();
                    recyclerView.setLayoutManager(new LinearLayoutManager(PeopleSurveyActivity.this));
                    recyclerView.setAdapter(sAdapter);
                    sAdapter.showEmpty();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("debug", "onError: ", e);
                refreshLayout.NotifyCompleteRefresh0();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            refreshLayout.setRefreshing();
        }
    }
}
