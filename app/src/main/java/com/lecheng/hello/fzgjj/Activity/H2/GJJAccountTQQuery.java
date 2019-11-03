package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.BeanGrgjjtqInfocx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class GJJAccountTQQuery extends AppCompatActivity implements IWSListener {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.tv6)
    TextView tv6;
    @Bind(R.id.tv7)
    TextView tv7;
    @Bind(R.id.tv8)
    TextView tv8;
    @Bind(R.id.tv9)
    TextView tv9;
    @Bind(R.id.tv10)
    TextView tv10;
    @Bind(R.id.tv11)
    TextView tv11;
    @Bind(R.id.tv12)
    TextView tv12;
    ActionBar frag;
    private String draw_no = "", type = "";
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2fa);
        ButterKnife.bind(this);
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("公积金账户提取明细");
        try {
            draw_no = getIntent().getStringExtra("draw_no");
            type = getIntent().getStringExtra("type");
            httpGet();
        } catch (Exception e) {
            e.printStackTrace();

            action = new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            };
            tv12.postDelayed(action, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv12.removeCallbacks(action);
    }

    public void httpGet() {
        String[] k = {"draw_no", "type"};
        String[] v = {draw_no, type};
        new HttpGo().httpWebService(this, this, "grgjjtqInfocx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanGrgjjtqInfocx bean = GsonUtil.GsonToBean(s, BeanGrgjjtqInfocx.class);
            tv1.setText(bean.getData().get(0).getPsn_acct());
            tv2.setText(bean.getData().get(0).getName());
            tv3.setText(bean.getData().get(0).getApprover());
            tv4.setText(bean.getData().get(0).getSjtqzje());
            tv5.setText(bean.getData().get(0).getReal_draw_prin());
            tv6.setText(bean.getData().get(0).getReal_draw_int());
            tv7.setText(bean.getData().get(0).getSjtqrq());
            tv8.setText(bean.getData().get(0).getZqywlx());
            tv9.setText(bean.getData().get(0).getZqyy());
            tv10.setText(bean.getData().get(0).getZqfs());
            tv11.setText(bean.getData().get(0).getAccounttype());
            tv12.setText(bean.getData().get(0).getBal());
        } catch (Exception e) {
            new MyToast(this, "获取失败,请重试", 1);
            e.printStackTrace();
        }
    }
}
