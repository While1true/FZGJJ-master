package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class Test extends Fragment implements IWSListener {

    @Bind(R.id.iv1)
    ImageView iv1;
    @Bind(R.id.cv2)
    CardView cv2;
    @Bind(R.id.tv1)
    TextView tv1;
    private HttpGo httpGo = new HttpGo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.test, c, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().post("中心简介");//改变anctionbar的标题
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cv2, R.id.tv1, R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv2:
                httpGo.httpWebService(getActivity(), this, "zxlyList", new String[]{}, new String[]{});
                break;
            case R.id.tv1:
//                getFragmentManager().popBackStack();
                break;
            case R.id.btn1:

                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        tv1.setText(s + "");
    }


}
