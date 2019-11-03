package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanGrdk4;
import com.lecheng.hello.fzgjj.Bean.BeanGrjcmxcx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import de.greenrobot.event.EventBus;

public class TQRepaymentInfo extends AppCompatActivity implements IWSListener {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4; @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.lv1)
    RefreshLayout lv1;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    ActionBar frag;
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2e);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("提取还款业务信息");
        String[] k = {"gjjaccount"};
        String[] v = {MySP.loadData(this, "username", "") + ""};
        new HttpGo().httpWebService(this, this, "dkcxtqhcxxlbcx", k, v);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);
        tv1.setText("提取时间");
        tv2.setText("提取原因");
        tv3.setText("提取金额");
        tv4.setText("余额");
        tv5.setVisibility(View.GONE);
    }

    @Override
    public void onWebServiceSuccess(String s) {

        try {
            BeanGrdk4 bean = GsonUtil.GsonToBean(s, BeanGrdk4.class);
            if(bean.getData().size()==0){
                throw new NullPointerException("没有数据");
            }
            RecyclerView recyclerView=lv1.getmScroll();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new SAdapter(bean.getData()).addType(R.layout.item5, new ItemHolder<BeanGrdk4.DataBean>() {
                @Override
                public void onBind(SimpleViewHolder helper, BeanGrdk4.DataBean item, int i) {
                    helper.setText(R.id.tv1, item.getREAL_DRAW_DATE());
                    helper.setText(R.id.tv2, item.getDRAW_REA());
                    helper.setText(R.id.tv3, item.getDRAWMONEY() + "");
                    helper.setText(R.id.tv4, item.getBAL() + "");
                    helper.setVisible(R.id.tv5,false);
                }

                @Override
                public boolean istype(BeanGrdk4.DataBean item, int i) {
                    return true;
                }
            }));

        } catch (Exception e) {
            new MyToast(this, "没有查到信息！", 1);
            action = new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            };
            tv1.postDelayed(action, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv1.removeCallbacks(action);
    }
}
