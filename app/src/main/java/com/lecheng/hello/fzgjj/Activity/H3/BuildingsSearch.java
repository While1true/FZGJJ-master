package com.lecheng.hello.fzgjj.Activity.H3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanLpcx;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class BuildingsSearch extends Activity implements IWSListener {

    @Bind(R.id.et1)
    EditText et1;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.et3)
    EditText et3;
    @Bind(R.id.lv1)
    ListView lv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3e);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("备案楼盘查询");
    }

    public void httpPost() {
        String e1 = et1.getText().toString(), e2 = et2.getText().toString(), e3 = et3.getText().toString();
        if (e1.equals("") && e2.equals("") && e3.equals("")) {
            new MyToast(this, "查询条件不能全为空", 0);
            return;
        }
        String[] k = {"k1", "k2", "k3",};
        String[] v = {e1, e2, e3};
        new HttpGo().httpWebService(this, this, "lpcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanLpcx bean = GsonUtil.GsonToBean(s, BeanLpcx.class);
            lv1.setAdapter(new UnityAdapter<BeanLpcx.DataBean>(this, bean.getData(), R.layout.item3) {
                @Override
                public void convert(ViewHolder helper, BeanLpcx.DataBean item, int position) {
                    helper.setText(R.id.tv1, item.getLpname());
                    helper.setText(R.id.tv2, item.getPwcode());
                    helper.setText(R.id.tv3, item.getKfname());
                    helper.setVisible(R.id.tv4, false);
                }
            });
        } catch (Exception e) {
        }
    }

    @OnClick({R.id.btn1, R.id.ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                httpPost();
                break;
        }
    }
}
