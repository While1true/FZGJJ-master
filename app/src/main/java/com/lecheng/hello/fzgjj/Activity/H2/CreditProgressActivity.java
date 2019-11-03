package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.RefreshViews.SScrollview;
import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vange on 2017/11/4.
 */

public class CreditProgressActivity extends BaseTitleActivity {
    //    @Bind(R.id.titleDeacript)
//    TextView titleDeacript;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.time1)
    TextView time1;
    @Bind(R.id.description1)
    TextView description1;
    @Bind(R.id.line1)
    View line1;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.time2)
    TextView time2;
    @Bind(R.id.description2)
    TextView description2;
    @Bind(R.id.line2)
    View line2;
    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.time3)
    TextView time3;
    @Bind(R.id.description3)
    TextView description3;
    @Bind(R.id.line3)
    View line3;
    @Bind(R.id.line6)
    View line6;
    @Bind(R.id.imageView6)
    ImageView imageView6;
    @Bind(R.id.time4)
    TextView time4;
    @Bind(R.id.description4)
    TextView description4;
    @Bind(R.id.line4)
    View line4;
    @Bind(R.id.imageView7)
    ImageView imageView7;
    @Bind(R.id.time5)
    TextView time5;
    @Bind(R.id.description5)
    TextView description5;
    @Bind(R.id.line5)
    View line5;
    @Bind(R.id.imageView8)
    ImageView imageView8;
    @Bind(R.id.time6)
    TextView time6;
    @Bind(R.id.description6)
    TextView description6;
    @Bind(R.id.sscrollview)
    SScrollview sScrollview;
    //处理中  已完成 退件
    @Bind(R.id.textView23)
    TextView textView23;
    @Bind(R.id.textView24)
    TextView textView24;
    @Bind(R.id.textView25)
    TextView textView25;
    @Bind(R.id.textView26)
    TextView textView26;
    @Bind(R.id.textView28)
    TextView textView28;
    @Bind(R.id.state1)
    TextView state1;
    @Bind(R.id.state2)
    TextView state2;
    @Bind(R.id.state3)
    TextView state3;
    @Bind(R.id.state4)
    TextView state4;
    @Bind(R.id.state5)
    TextView state5;
    @Bind(R.id.state6)
    TextView state6;
    private CreditListActivity.TitleBean bean;
    private JSONArray jsonArray;

    @Override
    protected int getContentLayoutId() {
        return R.layout.debit_progress;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("贷款进度查询");
        sScrollview.setPullRate(2).setRefreshMode(true, true, false, false);
        setData();

    }

    private void setData() {
        try {
            bean = (CreditListActivity.TitleBean) getIntent().getSerializableExtra("bean");
            jsonArray = new JSONArray(getIntent().getStringExtra("data"));
            switch (bean.getStep()) {
                case "贷款退件 ":
                    try {
                        HandlerTKTJ();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "贷款受理":
                    try {
                        HandleDKSL();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "资信调查":
                    try {
                        HandleZXDC();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "审核审批":
                    try {
                        handlerSHSP();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "合同签约":
                    try {
                        HanderHTQY();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "抵押登记":
                    try {
                        HanderDYDJ();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "放款":
                    try {
                        HanderFD();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:

                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void HanderHTQY() throws Exception {

        handlerSHSP();
        state3.setText(getType("0"));
        imageView5.setImageResource(R.drawable.green);

        JSONObject jsonObject4 = jsonArray.getJSONObject(5);
        QYbean qYbean = GsonUtil.GsonToBean(jsonObject4.toString(), QYbean.class);
        imageView6.setImageResource(getColor(bean.getCurState()));
        time4.setText(qYbean.getContract_date());
        state4.setText(getType(bean.getCurState()));
        description4.setText(qYbean.getBankPhone() + "\n通知时间：" + qYbean.getContract_notify_date() + "\n签约信息：" + qYbean.getContract_remark());

    }

    private void HanderDYDJ() throws Exception {
        HanderHTQY();
        state4.setText(getType("0"));
        imageView6.setImageResource(R.drawable.green);
        JSONObject jsonObject5 = jsonArray.getJSONObject(6);
        DYDKbean dyDKbean = GsonUtil.GsonToBean(jsonObject5.toString(), DYDKbean.class);
        imageView7.setImageResource(getColor(bean.getCurState()));
        time5.setText(dyDKbean.getPledge_date());
        state5.setText(getType(bean.getCurState()));
        description5.setText(dyDKbean.getBankPhone() + "\n抵押送抵时间：" + dyDKbean.getPledge_send_date() + "\n抵押信息：" + dyDKbean.getPledge_remark());
    }

    private void HanderFD() throws Exception {
        HanderDYDJ();

        state5.setText(getType("0"));
        imageView7.setImageResource(R.drawable.green);
        JSONObject jsonObject6 = jsonArray.getJSONObject(7);
        FDbean fDbean = GsonUtil.GsonToBean(jsonObject6.toString(), FDbean.class);
        imageView8.setImageResource(getColor(bean.getCurState()));
        time6.setText(fDbean.getLoan_date());
        state6.setText(getType(bean.getCurState()));
        description6.setText(fDbean.getBankPhone() + "\n放款信息：" + fDbean.getLoan_remark());


    }

    private void HandleZXDC() throws Exception {
        HandleDKSL();
        state1.setText(getType("0"));
        imageView3.setImageResource(R.drawable.green);

        JSONObject jsonObject2 = jsonArray.getJSONObject(3);
        String bankPhone = jsonObject2.getString("bankPhone");
        String bank_approve_date = jsonObject2.getString("bank_approve_date");
        String bank_approve_remark = jsonObject2.getString("bank_approve_remark");
        String enddate2 = jsonObject2.getString("enddate");
        String zxlxdh = jsonObject2.getString("zxlxdh");
        String remark = jsonObject2.getString("remark");

        imageView4.setImageResource(getColor(bean.getCurState()));
        time2.setText(bank_approve_date);
        state2.setText(getType(bean.getCurState()));
        description2.setText(zxlxdh + "\n中心调查信息：" + remark + "\n中心完成时间：" + enddate2 + "\n"+bankPhone+"\n银行调查信息：" + bank_approve_remark );


    }

    private void HandlerTKTJ() throws Exception {
        JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length() - 1);
        String tjhj = jsonObject.getString("tjhj");
        int i = Integer.parseInt(tjhj);
        String enddate = jsonObject.getString("enddate");
        String remark = jsonObject.getString("remark");
        String lxdh = jsonObject.getString("lxdh");
        switch (i) {
            case 1:
                imageView3.setImageResource(R.drawable.black);
                time1.setText(enddate);
                state1.setText(getType(bean.getCurState()));
                description1.setText(lxdh + "\n" + remark);
//                titleDeacript.setText("商业贷款金额："+bean.getBank_loan_prin()+"\n公积金贷款金额；"+bean.getBank_loan_period());
                break;
            case 2:
                HandleDKSL();
                state1.setText(getType("0"));
                imageView4.setImageResource(R.drawable.black);
                imageView3.setImageResource(R.drawable.green);
                time2.setText(enddate);
                state2.setText(getType(bean.getCurState()));
                description2.setText(lxdh + "\n" + remark);
//                titleDeacript.setText("商业贷款金额："+bean.getBank_loan_prin()+"\n公积金贷款金额；"+bean.getBank_loan_period());
                break;
            case 4:
                handlerSHSP();
                state2.setText(getType("0"));
                imageView5.setImageResource(R.drawable.green);
                imageView6.setImageResource(R.drawable.black);
                time4.setText(enddate);
                state4.setText(getType(bean.getCurState()));
                description4.setText(lxdh + "\n" + remark);
//                titleDeacript.setText("商业贷款金额："+bean.getBank_loan_prin()+"\n公积金贷款金额；"+bean.getBank_loan_period());
                break;
            case 5:
                HanderHTQY();
                state3.setText(getType("0"));
                imageView6.setImageResource(R.drawable.green);
                imageView7.setImageResource(R.drawable.black);
                time5.setText(enddate);
                state5.setText(getType(bean.getCurState()));
                description5.setText(lxdh + "\n" + remark);
//                titleDeacript.setText("商业贷款金额："+bean.getBank_loan_prin()+"\n公积金贷款金额；"+bean.getBank_loan_period());
                break;
            case 6:
                HanderDYDJ();
                state5.setText(getType("0"));
                imageView7.setImageResource(R.drawable.green);
                imageView8.setImageResource(R.drawable.black);
                time6.setText(enddate);
                state6.setText(getType(bean.getCurState()));
                description6.setText(lxdh + "\n" + remark);
//                titleDeacript.setText("商业贷款金额："+bean.getBank_loan_prin()+"\n公积金贷款金额；"+bean.getBank_loan_period());
                break;
        }

    }

    private void handlerSHSP() throws Exception {

        HandleZXDC();
        state2.setText(getType("0"));
        imageView4.setImageResource(R.drawable.green);

        JSONObject jsonObject3 = jsonArray.getJSONObject(4);
        String bank_loan_period = jsonObject3.getString("bank_loan_period");
        String bank_loan_prin = jsonObject3.getString("bank_loan_prin");
        String loan_period = jsonObject3.getString("loan_period");
        String chkdate = null;
        try {
            chkdate = jsonObject3.getString("chkdate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String loan_prin = jsonObject3.getString("loan_prin");
        String lxdh3 = jsonObject3.getString("lxdh");
        String remark3 = jsonObject3.getString("appr_idea");

        imageView5.setImageResource(getColor(bean.getCurState()));
        time3.setText(chkdate);
        state3.setText(getType(bean.getCurState()));
        description3.setText(lxdh3 + "\n商业贷款金额：" + bank_loan_prin + "元\n公积金贷款金额:" + loan_prin + "元\n审批期限：" + loan_period + "月\n审核信息：" + remark3);

//        titleDeacript.setText("商业贷款金额："+bank_loan_prin+"元\n公积金贷款金额:"+loan_prin+"元\n审批期限："+bank_loan_period);
    }

    private void HandleDKSL() throws Exception {
        JSONObject jsonObject = jsonArray.getJSONObject(2);
        String enddate = jsonObject.getString("enddate");
        String lxdh = jsonObject.getString("lxdh");
        imageView3.setImageResource(getColor(bean.getCurState()));
        time1.setText(enddate);
        state1.setText(getType(bean.getCurState()));
        description1.setText(lxdh);

    }


    private String getType(String type) {
        if (type.equals("0"))
            return "（已处理）";
        if(type.equals("1"))
            return "（待处理）";
        if (type.equals("2"))
            return "（处理中）";
        if (type.equals("3"))
            return "（退件）";
        if (type.equals("4"))
            return "（退件）";

        return null;
    }

    public int getColor(String type){
        if(type.equals("1"))
            return R.drawable.red;
        if (type.equals("2"))
            return R.drawable.yellow;
        if (type.equals("0"))
            return R.drawable.green;
        if (type.equals("4")||type.equals("3"))
            return R.drawable.black;

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class QYbean {

        /**
         * bankPhone : 87722632,88597926,88597927
         * contract_date : 20170515
         * contract_notify_date : 20170511
         * contract_remark : 2017.5.11己通知，预约5.15签约。
         */

        private String bankPhone;
        private String contract_date;
        private String contract_notify_date;
        private String contract_remark;

        public String getBankPhone() {
            return bankPhone;
        }

        public void setBankPhone(String bankPhone) {
            this.bankPhone = bankPhone;
        }

        public String getContract_date() {
            return contract_date;
        }

        public void setContract_date(String contract_date) {
            this.contract_date = contract_date;
        }

        public String getContract_notify_date() {
            return contract_notify_date;
        }

        public void setContract_notify_date(String contract_notify_date) {
            this.contract_notify_date = contract_notify_date;
        }

        public String getContract_remark() {
            return contract_remark;
        }

        public void setContract_remark(String contract_remark) {
            this.contract_remark = contract_remark;
        }
    }

    class DYDKbean {

        /**
         * bankPhone : 87722632,88597926,88597927
         * loan_remark :
         * pledge_date :
         * pledge_send_date :
         */

        private String bankPhone;
        private String pledge_remark;
        private String pledge_date;
        private String pledge_send_date;

        public String getBankPhone() {
            return bankPhone;
        }

        public String getPledge_remark() {
            return pledge_remark;
        }

        public void setPledge_remark(String pledge_remark) {
            this.pledge_remark = pledge_remark;
        }

        public void setBankPhone(String bankPhone) {
            this.bankPhone = bankPhone;
        }

        public String getPledge_date() {
            return pledge_date;
        }

        public void setPledge_date(String pledge_date) {
            this.pledge_date = pledge_date;
        }

        public String getPledge_send_date() {
            return pledge_send_date;
        }

        public void setPledge_send_date(String pledge_send_date) {
            this.pledge_send_date = pledge_send_date;
        }
    }

    class FDbean {

        /**
         * bankPhone : 87722632,88597926,88597927
         * loan_date :
         * pledge_remark :
         */

        private String bankPhone;
        private String loan_date;
        private String loan_remark="";

        public String getBankPhone() {
            return bankPhone;
        }

        public void setBankPhone(String bankPhone) {
            this.bankPhone = bankPhone;
        }

        public String getLoan_date() {
            return loan_date;
        }

        public void setLoan_date(String loan_date) {
            this.loan_date = loan_date;
        }

        public String getLoan_remark() {
            return loan_remark;
        }

        public void setLoan_remark(String loan_remark) {
            this.loan_remark = loan_remark;
        }
    }
}
