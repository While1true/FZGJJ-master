package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.RegexUtils;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.ck.hello.nestrefreshlib.View.RefreshViews.SRecyclerView;
import com.lecheng.hello.fzgjj.Bean.TQBean;
import com.lecheng.hello.fzgjj.Bean.YYResult;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.ProgressUtils;
import com.lecheng.hello.fzgjj.Utils.RequestParams;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import RxWeb.BaseFragment;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

/**
 * Created by vange on 2017/11/1.
 */

public class YuYueFragment extends BaseFragment {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    private SAdapter adapter;
    private String date;

    @Override
    protected void initView(Bundle savedInstanceState) {

//        sendMessage(null,null,null,null);
    }

    private void showdata(List<YuYueBean.DataBean> list) {
        adapter = new SAdapter<YuYueBean.DataBean>(list)
                .addType(R.layout.item3, new ItemHolder<YuYueBean.DataBean>() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, final YuYueBean.DataBean dataBean, int i) {
                        simpleViewHolder.setText(R.id.tv1, "预约时间：" + dataBean.getShowtime());
//                yydate.substring(0,4)+"-"+yydate.substring(4,6)+"-"+yydate.substring(6,8)
                        simpleViewHolder.setText(R.id.tv3, date);
                        simpleViewHolder.setText(R.id.tv4, "还可预约" + dataBean.getNumber() + "人");
                        simpleViewHolder.setVisible(R.id.tv2, false);
                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String username = MySP.loadData(getContext(), "username", "") + "";
                                if (null != (YuYueActivity2.type == 1 ? PreHandlerActivity.tq : PreHandlerActivity.dk)) {
                                    showAlreadyYY();
                                } else {
                                    showConfirmYY(dataBean, username);
                                }
                            }
                        });
                    }

                    @Override
                    public boolean istype(YuYueBean.DataBean dataBean, int i) {
                        return true;
                    }
                });
        RecyclerView recyclerView = refreshLayout.getmScroll();
        refreshLayout.getLayoutParams().height=ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void showConfirmYY(final YuYueBean.DataBean dataBean, final String username) {
        new AlertDialog.Builder(getContext())
                .setTitle("您确认预约吗?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定预约", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Dialog dialog1 = ProgressUtils.showProgressDialog(getContext());
                ApiService.YYCommit(new RequestParams()
                        .add("yylx", YuYueActivity2.type)
                        .add("bookingDate", date + " " + dataBean.getShowtime())
                        .add("bookingTime", dataBean.getBookingtime())
//                                .add("bookingBusitype", 0)
                        .add("acc_code", username), new MyObserve<YYResult>(getContext()) {
                    @Override
                    public void onNext(YYResult value) {
                        dialog1.dismiss();

                        final YYResult.DataBean data = value.getData();
                        boolean save = new TQBean(date + " " + dataBean.getShowtime(), data.getMsg(), username,
                                dataBean.getBookingtime(), null, YuYueActivity2.type).save();
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + value.getData().toString());

//                                        new MyToast(getContext(), "预约成功", 1);
                        if (!RegexUtils.isMobileExact(data.getMobile())) {
                            showPhoneDialog(data);
                        } else {
                            sendMessage(data.getMobile(), data.getUsername(), YuYueActivity2.type, data.getBookingDate(), data.getWorktime(), null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog1.dismiss();
                        new MyToast(getContext(), "预约失败", 1);
                    }
                });
            }
        }).create().show();
    }

    private void showAlreadyYY() {
        new android.app.AlertDialog.Builder(getContext())
                .setItems(new CharSequence[]{"您已经有过历史预约了，如果要重新预约，请取消以前的预约"}, null)
                .setCancelable(true)
                .setPositiveButton("取消预约", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreHandlerActivity.cancelYY(getContext(), new MyCallback() {
                            @Override
                            public void call(Object o) {
                                if (YuYueActivity2.type == 1) {
                                    PreHandlerActivity.tq = null;
                                } else {
                                    PreHandlerActivity.dk = null;
                                }
                                loadData(date);
                            }
                        }, YuYueActivity2.type);
                    }
                }).create().show();
    }

    private void showPhoneDialog(final YYResult.DataBean data) {
        View inflate = View.inflate(getContext(), R.layout.phone_dialog, null);
        View send = inflate.findViewById(R.id.send);
        final EditText et = (EditText) inflate.findViewById(R.id.editText);
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("请输入手机号")
                .setView(inflate)
                .setCancelable(false)
                .create();
        alertDialog.show();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!RegexUtils.isMobileExact(et.getText())) {
                    new MyToast(getContext(), "请输入正确的手机号", 1);
                    return;
                }
                sendMessage(et.getText().toString(), data.getUsername(), YuYueActivity2.type, data.getBookingDate(), data.getWorktime(), null);
                alertDialog.dismiss();
            }
        });
    }

    private void sendMessage(final String phone, final String name, int type, final String date, String time, String code) {
        final JSONObject jsonObject = new JSONObject();
        try {
            String replace = date.replace("-", "");
            jsonObject.put("jyrq", replace);
            jsonObject.put("jysj", time.replace("-", ""));
            jsonObject.put("sjh", phone);
            jsonObject.put("dxnr", String.format(Api.TEMP_MESSAGE, name, type == 1 ? "提取" : "贷款", date + "，" + time));
            System.out.println(String.format(Api.TEMP_MESSAGE, name, type == 1 ? "提取" : "贷款", date + "，" + time));
//            (Integer.parseInt(time.substring(0, 2)) > 12 ? "下午" : "上午") +
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendMessage(phone, String.format(Api.TEMP_MESSAGE, name, type == 1 ? "提取" : "贷款", date + "，" + time), new MyObserve<String>() {
            @Override
            public void onNext(String value) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + value);
                if (value.contains("\"status\":\"1\"")) {
                    ActivityManager.get().removeAll();
                    getActivity().finish();
                    new MyToast(getContext(), "预约成功，短信已送到" + phone, 1);
                } else {
                    loadData(date);
                    new MyToast(getContext(), "预约失败", 1);
                }
            }

            @Override
            public void onError(Throwable e) {
                loadData(date);
                new MyToast(getContext(), "预约失败", 1);
            }
        });
    }

    @Override
    protected void loadLazy() {

    }

    public void loadData(String date) {
        this.date = date;
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "loadData: " + date);
        ApiService.YYList(new RequestParams()
                .add("yylx", YuYueActivity2.type)
                .add("yydate", date), new MyObserve<YuYueBean>(this) {
            @Override
            public void onNext(YuYueBean list) {
                if (list != null && list.getData() != null) {
                    if (Constance.DEBUGTAG)
                        Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + list.getData().size());
                    if (list.getData().size() < 0) {
                        new MyToast(getContext(), "获取数据失败", 0);
                        return;
                    }
                    showdata(list.getData());

                }
            }

            @Override
            public void onError(Throwable e) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onError: " + e.getMessage());
                new MyToast(getContext(), "获取数据失败", 0);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.refreshlayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
