package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.BeanGrdk3;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class LoanRepaymentInfo extends AppCompatActivity implements IWSListener {
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
    ActionBar frag;
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2gc);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("贷款还款业务信息");
        String[] k = {"gjjaccount"};
        String[] v = {MySP.loadData(this, "username", "") + ""};
        new HttpGo().httpWebService(this, this, "dkhkxxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanGrdk3 bean = GsonUtil.GsonToBean(s, BeanGrdk3.class);
            tv1.setText(bean.getData().get(0).getBank_date());
            tv2.setText(bean.getData().get(0).getBrief());
            tv3.setText(bean.getData().get(0).getAmt());
            tv4.setText(bean.getData().get(0).getPay_prin());
            tv5.setText(bean.getData().get(0).getPay_int());
            tv6.setText(bean.getData().get(0).getPay_ovd_prin());
            tv7.setText(bean.getData().get(0).getPay_ovd_int());
            tv8.setText(bean.getData().get(0).getPay_hst_int());
            tv9.setText(bean.getData().get(0).getPre_pay_prin());
            tv10.setText(bean.getData().get(0).getLoan_bal());
        } catch (Exception e) {
            new MyToast(this, "没有查到信息！", 1);
            action = new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            };
            tv10.postDelayed(action, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv10.removeCallbacks(action);
    }
}
