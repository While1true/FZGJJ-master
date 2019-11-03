package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Bean.BeanGrjcmxcx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * 林思晗 15:50:51
 * WxQueryService.java
 * 个人提取查询，个人账号查询等查询
 * sql改了
 * 在这里面，
 * 记得同步最新代码
 **/
//WxQueryService.java 后台项目方法
//http://10.0.110.119:8080/gjj/views/wechat/wxGjjQuery.jsp
//openid : oCpdkwP06B3KPs_D03YTHhrAfny4
public class DepositedQuery extends Activity implements IWSListener {
    @Bind(R.id.lv1)
    RefreshLayout lv1;
    ActionBar frag;
    private BeanGrjcmxcx bean = new BeanGrjcmxcx();
    private UnityAdapter<BeanGrjcmxcx.DataBean> adpt;
    private String subperacct = "";
    private boolean getBt = false;
    private Runnable action;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2e);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人缴存查询");
        httpGet(MySP.loadData(this, "username", "") + "", "0");
    }

    public void httpGet(String acc, String type) {
        String[] k = {"gjjaccount", "type"};
        String[] v = {acc, type};
        new HttpGo().httpWebService(this, this, "grjcmxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            bean = GsonUtil.GsonToBean(s, BeanGrjcmxcx.class);
            if(bean.getData().size()==0){
                new MyToast(this, "无相关信息", 1);
            }
            if (!getBt) subperacct = bean.getData().get(0).getPSN_ACCT();
            getBt = true;
            RecyclerView recyclerView=lv1.getmScroll();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new SAdapter(bean.getData()).addType(R.layout.item5, new ItemHolder<BeanGrjcmxcx.DataBean>() {
                @Override
                public void onBind(SimpleViewHolder simpleViewHolder, BeanGrjcmxcx.DataBean item, int i) {
                    simpleViewHolder.setText(R.id.tv1, item.getPSN_ACCT());
                    simpleViewHolder.setText(R.id.tv2, item.getBANK_DATE());
                    simpleViewHolder.setText(R.id.tv3, item.getCR_AMT() + "");
                    simpleViewHolder.setText(R.id.tv4, item.getBUSI_BRIEF());
                    simpleViewHolder.setText(R.id.tv5, item.getBAL() + "");
                }

                @Override
                public boolean istype(BeanGrjcmxcx.DataBean item, int i) {
                    return true;
                }
            }));
        } catch (Exception e) {
//            action = new Runnable() {
//                @Override
//                public void run() {
//                    finish();
//                }
//            };
//            lv1.postDelayed(action,1500);
            new MyToast(this, "获取失败,请重试", 1);
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        lv1.removeCallbacks(action);
    }
    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                httpGet(MySP.loadData(this, "username", "") + "", "0");
                break;
            case R.id.btn2:
                if (TextUtils.isEmpty(subperacct))
                    new MyToast(this, "暂无补贴账户信息", 1);
                else
                    httpGet(subperacct, "1");
                break;
        }
    }


}
