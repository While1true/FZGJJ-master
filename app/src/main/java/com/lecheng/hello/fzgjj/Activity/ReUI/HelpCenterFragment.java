package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.H4.AllSearch;
import com.lecheng.hello.fzgjj.Activity.H4.Help;
import com.lecheng.hello.fzgjj.Activity.H4.OnlineCommunicate;
import com.lecheng.hello.fzgjj.Activity.H4.PeopleSurveyActivity;
import com.lecheng.hello.fzgjj.Activity.H4.PersonalHome;
import com.lecheng.hello.fzgjj.Activity.H4.ProblemsListActivity;
import com.lecheng.hello.fzgjj.Activity.H4.PushSetting;
import com.lecheng.hello.fzgjj.Activity.H4.Suggest;
import com.lecheng.hello.fzgjj.Activity.H4.SurveyActivity;
import com.lecheng.hello.fzgjj.Activity.Unit.Login;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.FragUtil;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.UpdateUtils;
import com.lecheng.hello.fzgjj.Widget.SettingView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

public class HelpCenterFragment extends Fragment {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    //    private String[] arrayLv = {"在线客服",
//            "平台搜索", "在线问答", "疑难解答", "投诉建议", "在线调查", "民意征集"};
//    private int[] arrImgRes2 = { R.drawable.ic_d3, R.drawable.ic_d6, R.drawable.ic_d3,
//            R.drawable.ic_d5, R.drawable.ic_d2, R.drawable.ic_d7, R.drawable.ic_d6
//            };
    private String[] arrayLv = {"在线问答","常见问题", "疑难解答","投诉建议","在线调查","民意征集","在线客服"};
    private int[] arrImgRes2 = {R.drawable.ic_online_ask,R.drawable.ic_problems,R.drawable.ic_puzzle,R.drawable.ic_suggest,R.drawable.ic_online_survey,R.drawable.ic_collect_suggest,R.drawable.online_services};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.home4, c, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        List<String> list2 = Arrays.asList(arrayLv);
        RecyclerView recyclerView = refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getContext(), LinearLayout.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.list_divider_material));
        recyclerView.addItemDecoration(decor);
        SAdapter sAdapter = new SAdapter<>(list2)
                .addType(R.layout.settingview, new ItemHolder<String>() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, String s, final int i) {
                        SettingView settingView=simpleViewHolder.getView(R.id.settingview);
                        settingView.setTitleText(s);
                        settingView.setTitledrawable(arrImgRes2[i],0,0,0);
                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (MySP.loadData(getActivity(), "username", "").toString().equals(""))
                                    FragUtil.startFragmentFullScreen(getActivity(), Login.getInstance());
                                else
                                    doclick(i);
                            }
                        });
                    }

                    @Override
                    public boolean istype(String s, int i) {
                        return true;
                    }
                });
        recyclerView.setAdapter(sAdapter);

    }
    private void doclick(int i) {
        switch (i) {
            case 0:
                startActivity(new Intent(getActivity(), OnlineCommunicate.class));
                break;
            case 1:
                Intent intent = new Intent(getActivity(), ProblemsListActivity.class);
                intent.putExtra("iscommon",true);
                startActivity(intent);
                break;
            case 2:
                startActivity(new Intent(getActivity(), ProblemsListActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), Suggest.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), SurveyActivity.class));
                break;
            case 5:
                startActivity(new Intent(getActivity(), PeopleSurveyActivity.class));
                break;
            case 6:
                startActivity(new Intent(getActivity(), Help.class));
                break;

        }
    }
    @Deprecated
    private void doclickOld(int i) {
        switch (i) {
        case 0:
            startActivity(new Intent(getActivity(), Help.class));
            break;
        case 1:
            startActivity(new Intent(getActivity(), AllSearch.class));
            break;
        case 2:
            startActivity(new Intent(getActivity(), OnlineCommunicate.class));
            break;
        case 3:
            startActivity(new Intent(getActivity(), ProblemsListActivity.class));
            break;
        case 4:
            startActivity(new Intent(getActivity(), Suggest.class));
            break;
        case 5:
            startActivity(new Intent(getActivity(), SurveyActivity.class));
            break;
        case 6:
            startActivity(new Intent(getActivity(), PeopleSurveyActivity.class));
            break;
    }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
