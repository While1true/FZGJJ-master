package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanZjdc;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

@Deprecated
public class Home4ja extends Activity implements IWSListener {
    @Bind(R.id.lv1)
    ListView lv1;
    private HttpGo httpGo = new HttpGo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4ga);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("民意征集");
        httpGo.httpWebService(this, this, "zjdc", new String[]{}, new String[]{});
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanZjdc bean = GsonUtil.GsonToBean(s, BeanZjdc.class);
            lv1.setAdapter(new UnityAdapter<BeanZjdc.DataBean>(this, bean.getData(), R.layout.item6_zjdc) {
                @Override
                public void convert(ViewHolder helper, BeanZjdc.DataBean item, int position) {
                    helper.setText(R.id.tv1, item.getTitle());
                    helper.setCbtext(R.id.cbA, item.getChoosea());
                    helper.setCbtext(R.id.cbB, item.getChooseb());
                    helper.setCbtext(R.id.cbC, item.getChoosec());
                    helper.setCbtext(R.id.cbD, item.getChoosed());
                    helper.setText(R.id.tv2, item.getStarttime());
                }
            });
        } catch (Exception e) {
            new MyToast(this, "数据解析失败", 0);
        }
    }

    @OnClick({R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCommit:
                new MyToast(this, "提交成功！", 1);
                finish();
                break;
        }
    }
}
