package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.BeanGrgjjxxcx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class PersonalAccountQuery extends AppCompatActivity implements IWSListener {

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
    @Bind(R.id.tv19)
    TextView tv19;
    @Bind(R.id.tv20)
    TextView tv20;
    @Bind(R.id.tv21)
    TextView tv21;
    ActionBar frag;

    private int type = 0;
    private Runnable action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2h);
        ButterKnife.bind(this);
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人账户信息查询");
        httpGet(MySP.loadData(this, "username", "") + "", "0");
    }

    public void httpGet(String acc, String type) {
        String[] k = {"gjjaccount", "type"};
        String[] v = {acc, type};
        new HttpGo().httpWebService(this, this, "grgjjxxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanGrgjjxxcx bean = GsonUtil.GsonToBean(s, BeanGrgjjxxcx.class);
            tv1.setText(bean.getData().get(0).getName());
            tv2.setText(bean.getData().get(0).getPeracct());
            tv3.setText(getAccountState(bean.getData().get(0).getAccountstatus()));
            tv4.setText(getAccountType(bean.getData().get(0).getAccounttype()));
            tv5.setText(bean.getData().get(0).getBkcard_no());
            tv6.setText(bean.getData().get(0).getOpen_date());
            tv7.setText(bean.getData().get(0).getYear_d_amt());
            tv8.setText(bean.getData().get(0).getLst_year_bal());
            tv9.setText(bean.getData().get(0).getYear_c_amt());
            tv10.setText(bean.getData().get(0).getYears_d_amt());
            tv11.setText(bean.getData().get(0).getYears_c_amt());
            tv12.setText(bean.getData().get(0).getAccountbalance());
            tv13.setText(bean.getData().get(0).getPayto_date());
            tv14.setText(bean.getData().get(0).getIncome());
            tv15.setText(bean.getData().get(0).getUnitpayrates());
            tv16.setText(bean.getData().get(0).getPersonpayrates());
            tv17.setText(bean.getData().get(0).getUnitpay());
            tv18.setText(bean.getData().get(0).getPersonpay());
            tv19.setText(bean.getData().get(0).getMonthpay());
            tv20.setText(getFrozenType(bean.getData().get(0).getFrozenmarks()));
            tv21.setText(bean.getData().get(0).getComp_name());
        } catch (Exception e) {
            if (type == 0)
                new MyToast(this, "获取失败,请重试", 1);
            else
                new MyToast(this, "暂无补贴账户信息", 1);
            e.printStackTrace();

            action = new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            };
            tv1.postDelayed(action,1500);
        }
    }

    /**
     * 账户类型
     * @param accountType
     * @return
     */
    private String getAccountType(int accountType ){
        switch (accountType){
            case 1:
                return "公积金";
            case 2:
                return "公积金挂账";
            case 3:
                return "补贴";
            case 4:
                return "补贴挂账";
            case 5:
                return "维修基金";
            case 6:
                return "住房债券";
                default:return null;
        }

    }

    /**
     * 账户状态
     * @param accountstate
     * @return
     */
    private String getAccountState(int accountstate ){
        switch (accountstate){
            case 0:
                return "正常";
            case 1:
                return "封存";
            case 2:
                return "缓缴";
            case 9:
                return "销户";
            default:return null;
        }

    }

    private String getFrozenType(int frozen){
        switch (frozen){
            case 0:
                return "正常";
            case 1:
                return "只收不付";
            case 2:
                return "不收不付";
            case 3:
                return "部分冻结";
            default:return null;
        }
    }
    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                type = 0;
                httpGet(MySP.loadData(this, "username", "") + "", "0");
                break;
            case R.id.btn2:
                type = 1;
                httpGet(MySP.loadData(this, "username", "") + "", "1");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv1.removeCallbacks(action);
    }
}
