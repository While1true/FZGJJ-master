package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.PositionHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.H2.PreHandlerActivity;
import com.lecheng.hello.fzgjj.Activity.H3.BuildingsSearch;
import com.lecheng.hello.fzgjj.Activity.H3.GuideInfo;
import com.lecheng.hello.fzgjj.Activity.H3.WorkPlaceActivity;
import com.lecheng.hello.fzgjj.Activity.H4.PersonalHome;
import com.lecheng.hello.fzgjj.Activity.Unit.Login;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.FragUtil;
import com.lecheng.hello.fzgjj.Utils.MySP;

import RxWeb.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

/**
 * Created by vange on 2017/12/22.
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    String[] titles = {"政策资讯", "业务查询", "预约办理", "网点查询", "便民工具", "在线咨询", "办事指南", "楼盘查询", "个人中心"};
    int[] res = {R.drawable.xxfw1, R.drawable.xxfw2, R.drawable.xxfw3,
            R.drawable.xxfw4, R.drawable.xxfw5, R.drawable.gj_service, R.drawable.gj3, R.drawable.gj6, R.drawable.xxfw6};
    private RecyclerView recyclerView;
    private SAdapter sAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        loadLazy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    protected void loadLazy() {
        recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemAnimator(new FlipInTopXAnimator());
        int i = SizeUtils.dp2px(12);
        refreshLayout.setPadding(i, 0, i, 0);
        View inflate = getLayoutInflater().inflate(R.layout.item_home, refreshLayout,false);
        inflate.measure(0,0);
        final int wrapmeasuredHeight = inflate.getMeasuredHeight();
        sAdapter = new SAdapter(res.length)
                .addType(R.layout.item_home, new PositionHolder() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, final int i) {
                        simpleViewHolder.setImageResource(R.id.iv1, res[i]);
                        simpleViewHolder.setText(R.id.tv1, titles[i]);

                        int measuredHeight = refreshLayout.getHeight();
                        int eachSpace = Math.max((measuredHeight -3*wrapmeasuredHeight)/4,SizeUtils.dp2px(5));

                        int heightx= i<3? (int) (wrapmeasuredHeight + eachSpace * 1.5) :wrapmeasuredHeight+eachSpace;
                        LinearLayout linearLayout = simpleViewHolder.getView(R.id.root);
                        linearLayout.setPadding(0,i<3?eachSpace:eachSpace/2,0,eachSpace/2);
                        simpleViewHolder.itemView.getLayoutParams().height = heightx;


                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (i) {
                                    case 0:
                                        startActivity(new Intent(v.getContext(), NewsActivity.class));
                                        break;
                                    case 1:
                                        startActivity(new Intent(v.getContext(), QueryActivity.class));
                                        break;
                                    case 2:
                                        if (MySP.loadData(getActivity(), "username", "").toString().equals("")) {
                                            FragUtil.startFragmentFullScreen(getActivity(), Login.getInstance());
                                            return;
                                        }
                                        startActivity(new Intent(getActivity(), PreHandlerActivity.class));
                                        break;
                                    case 3:
                                        startActivity(new Intent(getActivity(), WorkPlaceActivity.class));
                                        break;
                                    case 4:
                                        startActivity(new Intent(getActivity(), ToolActivity.class));
                                        break;
                                    case 5:
                                        startActivity(new Intent(getActivity(), HelpCenterActivity.class));
                                        break;
                                    case 6:
                                        startActivity(new Intent(getActivity(), GuideInfo.class));
                                        break;
                                    case 7:
                                        startActivity(new Intent(getActivity(), BuildingsSearch.class));
                                        break;
                                    case 8:
                                        if (MySP.loadData(getActivity(), "username", "").toString().equals("")) {
                                            FragUtil.startFragmentFullScreen(getActivity(), Login.getInstance());
                                            return;
                                        }
                                        startActivity(new Intent(getActivity(), PersonalHome.class));
                                        break;

                                }
                            }
                        });

                    }

                    @Override
                    public boolean istype(int i) {
                        return true;
                    }
                });
        sAdapter.showStateNotNotify(SAdapter.TYPE_ITEM, null);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(sAdapter);
                sAdapter.notifyItemRangeInserted(0, sAdapter.getItemCount());
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
