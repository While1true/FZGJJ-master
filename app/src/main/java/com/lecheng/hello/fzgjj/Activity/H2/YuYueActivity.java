package com.lecheng.hello.fzgjj.Activity.H2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vange on 2017/11/1.
 */
@Deprecated
public class YuYueActivity extends BaseTitleActivity {
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.vp)
    ViewPager vp;
    static int type=1;
    int preDays=5;
    int [] draws={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
   String[]datas;
    @Override
    protected int getContentLayoutId() {
        return R.layout.yuyue_layout;
    }
    YuYueFragment[]fragments=new YuYueFragment[5];
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("预约时间");
        ButterKnife.bind(this);
        datas=getDateListString(preDays);
        type=getIntent().getIntExtra("type",1);
        vp.setOffscreenPageLimit(5);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = fragments[position];
                if(fragment==null) {
                    YuYueFragment yuYueFragment = new YuYueFragment();
                    Bundle args = new Bundle();
                    args.putString("date", datas[position]);
                    args.putInt("position", position);
                    yuYueFragment.setArguments(args);
                    fragments[position]=yuYueFragment;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return preDays;
            }
        });
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datas));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(vp.getCurrentItem()!=position){
                    vp.setCurrentItem(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                  if(spinner.getSelectedItemPosition()!=position){
                      spinner.setSelection(position);
                  }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);
    }

    private String[] getDateListString(int amount) {
        String[]list=new String[amount];
        Calendar calendar = Calendar.getInstance();
        int count=0;
        for (;;) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "getDateListString: "+week);
            if(week==7||week==1)
                continue;
            Date date=calendar.getTime();
            list[count]=simpleDateFormat.format(date);
            count++;
            if(count==amount){
                break;
            }
        }
        return list;
    }
}
