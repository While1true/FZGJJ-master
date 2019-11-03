package com.lecheng.hello.fzgjj.Activity.H1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.Info;
import com.lecheng.hello.fzgjj.Activity.Unit.Menu;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanInfoList;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import RxWeb.BaseAppCompatActivity;
import RxWeb.GsonUtil;
import RxWeb.MyObserve;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//fragment返回值：http://blog.csdn.net/hxltech/article/details/51683599
public class MoreInfo extends BaseAppCompatActivity implements IWSListener {

    @Bind(R.id.lv1)
    ListView lv1;
    @Bind(R.id.tvTitle)
    TextView tvTitle;


    public void httpWebService(String code) {
        WebUtils.doSoapNet("infoList", new RequestParams()
                .add("code", code)
                .add("pcode")
                .add("page", 1))
                .subscribe(new MyObserve<String>(this) {
                    @Override
                    public void onNext(@NonNull String s) {
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "onNext:MoreInfo " + s);
                        onWebServiceSuccess(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "onError: " + e.getMessage());
                    }
                });
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            final BeanInfoList bean = GsonUtil.GsonToBean(s, BeanInfoList.class);
            lv1.setAdapter(new UnityAdapter<BeanInfoList.DataBean>(this, bean.getData(), R.layout.item0d) {
                @Override
                public void convert(ViewHolder helper, BeanInfoList.DataBean item, int position) {
                    helper.setText(R.id.tv1, item.getTitle());
//                    helper.setText(R.id.tv2, item.getFbt());
                    helper.setText(R.id.tv2, item.getUpdatedate());
//                    helper.setVisible(R.id.tv4, false);
                }
            });
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(MoreInfo.this, Info.class)
                            .putExtra("time", bean.getData().get(position).getUpdatedate())
                            .putExtra("info", bean.getData().get(position).getContent())
                            .putExtra("title", bean.getData().get(position).getTitle())
                            .putExtra("source", bean.getData().get(position).getSource()));
                }
            });
        } catch (Exception e) {
            new MyToast(this, "" + R.string.http_err, 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.home1more);
        ButterKnife.bind(this);
        httpWebService(Api.HOME_ZXJG);
    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                httpWebService(Api.HOME_ZXJG);
                tvTitle.setText("中心简介");
                break;
            case R.id.ll2:
                httpWebService("ZXLD");
                tvTitle.setText("中心领导");
                break;
            case R.id.ll3:
                httpWebService("JGSZ");
                tvTitle.setText("机构设置");
                break;
            case R.id.ll4:
                httpWebService("BMZN");
                tvTitle.setText("部门职能");
                break;
            case R.id.ll5:
                httpWebService("BGT");
                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_menu:
                Menu menu = new Menu(this);
                menu.showPopupWindow(view);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
