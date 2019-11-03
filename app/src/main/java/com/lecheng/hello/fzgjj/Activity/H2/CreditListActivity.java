package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import RxWeb.GsonUtil;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * Created by vange on 2017/11/4.
 */

public class CreditListActivity extends BaseTitleActivity {
    @Bind(R.id.srecyclerview)
    RefreshLayout refreshLayout;

    List<TitleBean>list=new ArrayList<>();
    Runnable action=new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    @Override
    protected int getContentLayoutId() {
        return R.layout.srecyclerview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("贷款记录");
        ApiService.quiryCredit(new MyObserve<String>(this) {
            @Override
            public void onNext(String value) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(value.toString());
                    JSONArray jsons = jsonObject.getJSONArray("data");

                    Parse(jsons);

                } catch (Exception e) {
                    e.printStackTrace();
                    refreshLayout.postDelayed(action,1500);
                }

                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext22: " + value);
            }

            @Override
            public void onError(Throwable e) {
                refreshLayout.postDelayed(action,1500);
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onError: " + e.getMessage());

            }
        });
    }

    private void Parse(final JSONArray jsons) throws Exception{
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "Parse: "+jsons.length());
        for (int i = 0; i < jsons.length(); i++) {
            JSONArray jsonArray = jsons.getJSONArray(i);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String type = jsonObject.getString("type");
            String curState = jsonObject.getString("curState");
            JSONObject jsonObject1 = jsonArray.getJSONObject(1);
            TitleBean titleBean = GsonUtil.GsonToBean(jsonObject1.toString(), TitleBean.class);
            titleBean.setStep(type);
            titleBean.setCurState(curState);
            list.add(titleBean);
        }
        RecyclerView recyclerView=refreshLayout.getmScroll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<TitleBean>(this,R.layout.credit_item,list) {
            @Override
            protected void convert(ViewHolder holder, final TitleBean titleBean, final int position) {
                holder.setText(R.id.survey_title,titleBean.getPsn_name()+"--"+titleBean.getEnddate());
                String bld_no = titleBean.getBld_no();
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "convert: "+("房屋地址："+titleBean.getBuy_house_addr()+"\n楼盘编号："+ (bld_no==null?"":bld_no)));
                holder.setText(R.id.start_endTime, "房屋地址："+titleBean.getBuy_house_addr()+"\n楼盘编号："+ (bld_no==null?"":bld_no)+
                        "\n商业贷款金额："+titleBean.getBank_loan_prin()+"元"+"\n商业贷款期限："+titleBean.bank_loan_period+"月"
                        /*+"\n公积金贷款金额："+titleBean.getBank_loan_period()+"元"*/);
                holder.setText(R.id.survey_state, titleBean.getStep());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(v.getContext(),CreditProgressActivity.class);
                        intent.putExtra("bean",titleBean);
                        try {
                            intent.putExtra("data",jsons.getJSONArray(position).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                });
            }
        });
    }


    public static class TitleBean implements Serializable{
        String step;
        String bank_loan_period;
        String bank_loan_prin;
        String bld_no;
        String bor_cert_code;
        String buy_house_addr;
        String psn_name;
        String enddate;
        String curState="";

        public String getCurState() {
            return curState;
        }

        public void setCurState(String curState) {
            this.curState = curState;
        }

        public String getBank_loan_period() {
            return bank_loan_period;
        }

        public void setBank_loan_period(String bank_loan_period) {
            this.bank_loan_period = bank_loan_period;
        }

        public String getBank_loan_prin() {
            return bank_loan_prin;
        }

        public void setBank_loan_prin(String bank_loan_prin) {
            this.bank_loan_prin = bank_loan_prin;
        }

        public String getBld_no() {
            return bld_no;
        }

        public void setBld_no(String bld_no) {
            this.bld_no = bld_no;
        }

        public String getBor_cert_code() {
            return bor_cert_code;
        }

        public void setBor_cert_code(String bor_cert_code) {
            this.bor_cert_code = bor_cert_code;
        }

        public String getBuy_house_addr() {
            return buy_house_addr;
        }

        public void setBuy_house_addr(String buy_house_addr) {
            this.buy_house_addr = buy_house_addr;
        }

        public String getPsn_name() {
            return psn_name;
        }

        public void setPsn_name(String psn_name) {
            this.psn_name = psn_name;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshLayout.removeCallbacks(action);
    }
}
