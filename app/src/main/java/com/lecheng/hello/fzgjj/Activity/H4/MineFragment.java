package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.Login;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.FragUtil;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.UpdateUtils;

import java.util.Arrays;
import java.util.List;

import RxWeb.BaseBean;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

public class MineFragment extends Fragment {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private String[] arrayLv = {"个人中心", "在线客服", "消息推送设置",
            "平台搜索", "在线问答", "疑难解答", "投诉建议", "在线调查", "民意征集","检查更新"};
    private int[] arrImgRes2 = {R.drawable.ic_d2, R.drawable.ic_d3,
            R.drawable.ic_d8, R.drawable.ic_d6, R.drawable.ic_d3,
            R.drawable.ic_d5, R.drawable.ic_d2, R.drawable.ic_d7, R.drawable.ic_d6
            ,R.drawable.ic_update};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.home4, c, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        List<String> list2 = Arrays.asList(arrayLv);
        RecyclerView recyclerView=refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SAdapter sAdapter=new SAdapter<>(list2)
                .addType(R.layout.item1_2, new ItemHolder<String>() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, String s, final int i) {
                        simpleViewHolder.setText(R.id.tv1, s);
                        simpleViewHolder.setImageResource(R.id.iv1, arrImgRes2[i]);
                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(i==9){
                                    ApiService.checkVersion(new MyObserve<BaseBean<UpdateBean>>() {
                                        @Override
                                        public void onNext(BaseBean<UpdateBean> value) {
                                            try {
                                                if(value!=null&&value.getStatus()==1&&Integer.parseInt(value.getData().getUpdateNumber())> getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(),0).versionCode) {
                                                    UpdateUtils.checkUpdate(getActivity(), value.getData());
                                                }else {
                                                    new MyToast(getContext(),"已经是最新的了",0);
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                new MyToast(getContext(),"已经是最新的了",0);
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            new MyToast(getContext(),"已经是最新的了",0);
                                        }
                                    });
                                    return;
                                }
                                if (MySP.loadData(getActivity(), "username", "").toString().equals(""))
                                    FragUtil.startFragmentFullScreen(getActivity(),Login.getInstance());
                                else switch (i) {
                                    case 0:
                                        startActivity(new Intent(getActivity(), PersonalHome.class));
                                        break;
                                    case 1:
                                        startActivity(new Intent(getActivity(), Help.class));
                                        break;
                                    case 2:
                                        startActivity(new Intent(getActivity(), PushSetting.class));
                                        break;
                                    case 3:
                                        startActivity(new Intent(getActivity(), AllSearch.class));
                                        break;
                                    case 4:
                                        startActivity(new Intent(getActivity(), OnlineCommunicate.class));
                                        break;
                                    case 5:
                                        startActivity(new Intent(getActivity(), ProblemsListActivity.class));
                                        break;
                                    case 6:
                                        startActivity(new Intent(getActivity(), Suggest.class));
                                        break;
                                    case 7:
                                        startActivity(new Intent(getActivity(), SurveyActivity.class));
                                        break;
                                    case 8:
                                        startActivity(new Intent(getActivity(), PeopleSurveyActivity.class));
                                        break;
                                }
                            }
                        });
                    }

                    @Override
                    public boolean istype(String s, int i) {
                        return true;
                    }
                });
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(sAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
