package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.Login;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.FragUtil;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Widget.SettingView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

public class Services extends Fragment {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    // "在线预约",   , "个人业务指南", "单位业务指南"};
    private String[] array = {  "还款进度查询", "个人缴存查询",
            "个人提取查询", "个人贷款查询", "个人账户信息查询"};
//    "资料变更",R.drawable.ic_b2,
    private int[] arrImgRes = {
            R.drawable.ic_b5, R.drawable.ic_b6, R.drawable.ic_b7, R.drawable.ic_c2,
            R.drawable.ic_c1, R.drawable.ic_c7};
//    R.drawable.ic_b1,R.drawable.ic_b3,

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.home4, c, false);
        ButterKnife.bind(this, view);
        List<String> list = Arrays.asList(array);
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.list_divider_material));
        recyclerView.addItemDecoration(decor);
        SAdapter sAdapter = new SAdapter<>(list)
                .addType(R.layout.settingview, new ItemHolder<String>() {
                            @Override
                            public void onBind(SimpleViewHolder simpleViewHolder, String s, final int i) {
                                SettingView settingView=simpleViewHolder.getView(R.id.settingview);
                                settingView.setTitleText(s);
                                settingView.setTitledrawable(arrImgRes[i],0,0,0);
                                simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (MySP.loadData(getActivity(), "username", "").toString().equals(""))
                                            FragUtil.startFragmentFullScreen(getActivity(),Login.getInstance());
                                        else
                                            switch (i) {
                                                case 0:
//                                                    startActivity(new Intent(getActivity(), RepaymentActivity.class));
                                                    FragUtil.startFragmentFullScreen(getActivity(),new RepaymentCheck());
                                                    break;
                                                case 1:
                                                    startActivity(new Intent(getActivity(), DepositedQuery.class));
                                                    break;
                                                case 2:
                                                    startActivity(new Intent(getActivity(), QueryPersonTQ.class));
                                                    break;
                                                case 3:
                                                    startActivity(new Intent(getActivity(), PersonalDebitQuery.class));
                                                    break;
                                                case 4:
                                                    startActivity(new Intent(getActivity(), PersonalAccountQuery.class));
                                                    break;
//                                                case 5:
//                                                    startActivity(new Intent(getActivity(), Home2i.class));
//                                                    break;
//                                                case 6:
//                                                    startActivity(new Intent(getActivity(), Home2j.class));
//                                                    break;
                                            }
                                    }
                                });
                            }

                            @Override
                            public boolean istype(String s, int i) {
                                return true;
                            }
                        });
        recyclerView.setAdapter(sAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
