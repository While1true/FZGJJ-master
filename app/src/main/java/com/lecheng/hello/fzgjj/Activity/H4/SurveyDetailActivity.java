package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Property;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Bean.SurveySubBean;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;
import com.lecheng.hello.fzgjj.Utils.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/9/18.
 */

public class SurveyDetailActivity extends BaseTitleActivity {

    @Bind(R.id.pre)
    Button pre;
    @Bind(R.id.next)
    Button next;
    List<SurveySubBean> str;
    int pager = 0;

    SparseArray<List<String>> arr = new SparseArray<>();
    private SurveyFragment fragment;
    private String id;

    @Override
    protected int getContentLayoutId() {
        return R.layout.viewpager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Fragment savefragment = getSupportFragmentManager().findFragmentByTag("fragment");
        if (savefragment != null)
            this.fragment = (SurveyFragment) savefragment;
        else
            this.fragment = new SurveyFragment();

        if (savedInstanceState != null) {
            pager = savedInstanceState.getInt("pager");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment, "fragment").commit();
        id = getIntent().getStringExtra("id");

        ApiService.getSurveySubList(id, new MyObserve<List<SurveySubBean>>(this) {
            @Override
            public void onNext(List<SurveySubBean> value) {
                if (value != null) {
                    str = value;
                    setTitle("第" + (pager+1) + "页/总共：" + str.size() + "页");

                    changeData(pager);
                }

            }

            @Override
            public void onError(Throwable e) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onError: "+e.getMessage());

            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState.putInt("pager",pager);
//        savedInstanceState.putSparseParcelableArray("arr",arr);
    }

    @OnClick({R.id.pre, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pre:
                if (pager > 0) {
                    pager--;
                }
                if (pager == 0)
                    pre.setEnabled(false);
                if (pager < str.size() - 1) {
                    next.setText("下一个");
                }
                changeData(pager);
                break;
            case R.id.next:
                List<String> data = fragment.getData();

                if (data != null) {
                    List<String> copy = new ArrayList<>(data.size());
                    copy.addAll(data);
                    arr.put(pager, copy);
                }

                if (pager == str.size() - 1) {
                    doCommit();
                    return;
                }

                if (pager < str.size() - 1)
                    pager++;
                if (pager > 0) {
                    pre.setEnabled(true);
                }
                if (pager == str.size() - 1) {
                    next.setText("提交");
                }
                changeData(pager);
                break;
        }
    }

    private void doCommit() {
        new AlertDialog.Builder(this)
                .setTitle("确认提交吗？")
                .setPositiveButton("确认提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestParams requestParams=new RequestParams();
                        requestParams.put("pid",id);
                        StringBuilder sb=new StringBuilder(20);
                        boolean commmit=true;
                        for (int i = 0; i < str.size(); i++) {
                            sb.append(str.get(i).getId());
                            List<String> strings = arr.get(i);
                            if(strings!=null&&strings.size()>0)
                                sb.append(",");
                            else {
                                new MyToast(SurveyDetailActivity.this, "第" + (i + 1) + "题未选择答案", 1);
                                commmit=false;
                                break;
                            }
                            if(strings!=null){
                                for (int i1 = 0; i1 < strings.size(); i1++) {
                                    if(i1<strings.size()-1)
                                        sb.append(strings.get(i1)+",");
                                    else
                                        sb.append(strings.get(i1));
                                }

                            }
                            sb.append(";");
                        }
                        if(!commmit)
                            return;

                        String substring = sb.substring(0, sb.length() - 1);
                        requestParams.put("map",substring);
                        requestParams.put("user_id", MySP.loadData(SurveyDetailActivity.this, "username", "").toString());
                        ApiService.getSurveySubmit(requestParams, new MyObserve<String>(this) {
                            @Override
                            public void onNext(String value) {
                                if (Constance.DEBUGTAG)
                                    Log.i(Constance.DEBUG, "onNext: "+value);
                                if(value.equals("OK")){
                                    new MyToast(SurveyDetailActivity.this,"提交成功",0);
                                    setResult(RESULT_OK);
                                    SurveyDetailActivity.this.finish();
                                }else{
                                    new MyToast(SurveyDetailActivity.this,"提交失败:\n"+value,0);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    public void changeData(int pager) {
        if (str == null)
            return;

        setTitle("第" + (pager+1) + "页");
        List<String> datas = null;
        try {
            String[] split = str.get(pager).getChoose().split(",");
            datas = Arrays.asList(split);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> strings = arr.get(pager);
        fragment = fragment.setTitle(str.get(pager).getTitle()).setType(str.get(pager).getType()).reset(strings, datas);
    }
}
