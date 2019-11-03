package com.lecheng.hello.fzgjj.Activity.H1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lecheng.hello.fzgjj.Adpt.AdptPager;
import com.lecheng.hello.fzgjj.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class NewsFragment extends Fragment {

    @Bind(R.id.vp1)
    ViewPager vp1;
    @Bind(R.id.tab)
    TabLayout tab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.home1, c, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().post("信息服务");//改变anctionbar的标题
        vp1.setAdapter(new AdptPager(getFragmentManager(), getActivity()));
        vp1.setOffscreenPageLimit(4);
        tab.setupWithViewPager(vp1);

//        tab.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
