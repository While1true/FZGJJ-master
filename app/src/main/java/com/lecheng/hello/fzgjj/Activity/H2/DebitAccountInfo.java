package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.BeanGrdk2;
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

public class DebitAccountInfo extends AppCompatActivity implements IWSListener {
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
    @Bind(R.id.tv13)
    TextView tv13;
    @Bind(R.id.tv14)
    TextView tv14;
    @Bind(R.id.tv15)
    TextView tv15;
    @Bind(R.id.tv16)
    TextView tv16;
    @Bind(R.id.tv17)
    TextView tv17;
    @Bind(R.id.tv18)
    TextView tv18;
    ActionBar frag;
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2gb);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("贷款账户基本信息");
        String[] k = {"gjjaccount"};
        String[] v = {MySP.loadData(this, "username", "") + ""};
        new HttpGo().httpWebService(this, this, "dkzhjbxxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onWebServiceSuccess: "+s);
            BeanGrdk2 bean = GsonUtil.GsonToBean(s, BeanGrdk2.class);
            tv1.setText(bean.getData().get(0).getLoan_type());
            tv2.setText(bean.getData().get(0).getLoan_amt());
//            tv3.setText(bean.getData().get(0).);//期限频率
            tv4.setText(bean.getData().get(0).getLoan_terms());
            tv5.setText(bean.getData().get(0).getCont_due_date());
            tv6.setText(bean.getData().get(0).getRtn_type());
            tv7.setText(bean.getData().get(0).getLoan_bgn_date());
            tv8.setText(bean.getData().get(0).getLoan_end_date());
            tv9.setText(bean.getData().get(0).getMon_pay_amt());
            tv10.setText(bean.getData().get(0).getLoan_irate());
            tv11.setText(bean.getData().get(0).getCur_term());
            tv12.setText(bean.getData().get(0).getRemain_terms());
            tv13.setText(bean.getData().get(0).getScurterm_rtn_prin());
            tv14.setText(bean.getData().get(0).getScurterm_rtn_inte());
            tv15.setText(bean.getData().get(0).getAcurterm_rtn_prin());
            tv16.setText(bean.getData().get(0).getAcurterm_rtn_inte());
            tv17.setText(bean.getData().get(0).getLoan_bal());
            tv18.setText(bean.getData().get(0).getAcct_state());
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
