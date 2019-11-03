package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Bean.BeanHkcx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import RxWeb.GsonUtil;

import RxWeb.MyObserve;
import RxWeb.RxLifeUtils;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepaymentCheck extends Fragment implements IWSListener {

    @Bind(R.id.tvMsg)
    TextView tvMsg;
    private Runnable action;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home2c, container, false);
        http();
        ButterKnife.bind(this, view);
        return view;
    }

    private void http() {
        WebUtils.doSoapNet("hkcx",new RequestParams()
        .add("gjjaccount",MySP.loadData(getActivity(), "username", "") + "")
       ).subscribe(new MyObserve<String>(this) {
            @Override
            public void onNext(String value) {
                onWebServiceSuccess(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {

            BeanHkcx bean = GsonUtil.GsonToBean(s, BeanHkcx.class);
            String acurterm_rtn_prin = bean.getData().get(0).getACURTERM_RTN_PRIN();
            String scurterm_rtn_amt = bean.getData().get(0).getSCURTERM_RTN_AMT();
            tvMsg.setText("当前应还金额: " +(TextUtils.isEmpty(scurterm_rtn_amt)?"0":scurterm_rtn_amt)  +
                    "元\n已还本期本金: " + (TextUtils.isEmpty(acurterm_rtn_prin)?"0":acurterm_rtn_prin) + "元");
        } catch (Exception e) {
            e.printStackTrace();
            new MyToast(getActivity(), "没有查询到数据", 1);
            tvMsg.setText("没有查询到数据！");
            action = new Runnable() {
                @Override
                public void run() {
                    getFragmentManager().popBackStack();
                }
            };
            tvMsg.postDelayed(action,2000);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvMsg.removeCallbacks(action);
        ButterKnife.unbind(this);
        RxLifeUtils.getInstance().remove(this);
    }

    @OnClick({R.id.tvMsg, R.id.llFrag})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMsg:
//                http();
                break;
            case R.id.llFrag:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
