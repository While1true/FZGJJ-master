package com.lecheng.hello.fzgjj.Activity.Unit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.InputUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

//import android.support.v4.app.Fragment;
//v4版Aty的xml中直接引用会报错

public class ActionBar extends Fragment {

    @Bind(R.id.llBack)
    View vBack;
    @Bind(R.id.tvTitle)
    TextView tvTittle;
    @Bind(R.id.llMenu)
    View tvMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.unit_actionbar, c, false);
        ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);//注册事件监听器
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
//        EventBus.getDefault().unregister(this);//注销事件监听器

    }

    public void setMenuVisable(){
        tvMenu.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.llBack, R.id.tvTitle, R.id.llMenu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llBack:
                try {
                    InputUtils.hideKeyboard(getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getFragmentManager().popBackStack();
//                if (MainActivity.instance.getSupportFragmentManager().getBackStackEntryCount() == 1)
//                    EventBus.getDefault().post("首页");//改变anctionbar的标题
                getActivity().onBackPressed();//调用退出方法
                break;
            case R.id.tvTitle:
                break;
            case R.id.llMenu:
                Menu menu = new Menu(getActivity());
                menu.showPopupWindow(view);
                break;
        }
    }
//
//    public void onEvent(String titleText) {
//        tvTittle.setText(titleText);
//    }

    public void setTitle(String title){
        tvTittle.setText(title);
    }
}
