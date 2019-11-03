package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.BeanGrdk1;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class PersonalDebitInfo extends Activity implements IWSListener {
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
    ActionBar frag;
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2ga);
        ButterKnife.bind(this);
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人贷款办理信息");
        String[] k = {"gjjaccount"};
        String[] v = {MySP.loadData(this, "username", "") + ""};
        new HttpGo().httpWebService(this, this, "grdkblxxcx", k, v);

    }

    @Override
    public void onWebServiceSuccess(String s) {

        try {
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onWebServiceSuccess: " + s);
            BeanGrdk1 bean = GsonUtil.GsonToBean(s, BeanGrdk1.class);
            tv1.setText(bean.getData().get(0).getBor_cert_code());
            tv2.setText(bean.getData().get(0).getName());
            tv3.setText(bean.getData().get(0).getMontincome());
            tv4.setText(bean.getData().get(0).getApply_amt());
            tv5.setText(bean.getData().get(0).getApply_period());
            tv6.setText(bean.getData().get(0).getBank_loan_prin());
            tv7.setText(bean.getData().get(0).getBank_loan_period());
            tv8.setText(bean.getData().get(0).getEnt_date());
            tv9.setText(bean.getData().get(0).getEnt_oper());
            tv10.setText(bean.getData().get(0).getCur_step());
            tv11.setText(bean.getData().get(0).getAuth_stat());
        } catch (Exception e) {
            new MyToast(this, "获取失败，请重试！", 1);

            action = new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            };
            tv11.postDelayed(action, 1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv11.removeCallbacks(action);
    }
}
