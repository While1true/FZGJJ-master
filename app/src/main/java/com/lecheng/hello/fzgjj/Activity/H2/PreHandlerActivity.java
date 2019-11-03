package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Bean.WtBean;
import com.lecheng.hello.fzgjj.Bean.YYCode;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.ProgressUtils;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/11/1.
 */

public class PreHandlerActivity extends BaseTitleActivity {
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.cancelYY)
    View cancelYY;
    @Bind(R.id.cancelDK)
    View cancelDK;
    @Bind(R.id.type1)
    TextView type1;
    @Bind(R.id.wait1)
    TextView wait1;
    @Bind(R.id.end1)
    TextView end1;
    @Bind(R.id.time1)
    TextView time1;
    @Bind(R.id.type2)
    TextView type2;
    @Bind(R.id.wait2)
    TextView wait2;
    @Bind(R.id.end2)
    TextView end2;
    @Bind(R.id.time2)
    TextView time2;
    private List<YYCode.DataBean> tqBeans;
    public static YYCode.DataBean tq;
    public static YYCode.DataBean dk;
    private static Dialog dialog;

    @Override
    protected int getContentLayoutId() {
        return R.layout.pre_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("选择预约类型");
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        ApiService.WTBL(new MyObserve<List<WtBean>>(this) {
            @Override
            public void onNext(List<WtBean> value) {
                WtBean a = value.get(0);
                WtBean b = value.get(1);
                type1.setText(a.getBooking_type()==1? "提取业务" : "贷款业务");
                type2.setText(b.getBooking_type()==1? "提取业务" : "贷款业务");

                wait1.setText(a.getWait());
                wait2.setText(b.getWait());

                end1.setText(a.getEnd());
                end2.setText(b.getEnd());

                time1.setText(a.getUpdate_time());
                time2.setText(b.getUpdate_time());

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        ApiService.yyQuery(new MyObserve<YYCode>(this) {
            @Override
            public void onNext(YYCode value) {
                dk = null;
                tq = null;
                if (value != null) {
                    tqBeans = value.getData();
                    for (YYCode.DataBean tqBean : tqBeans) {
                        if (tqBean.getBookingtype() == 1) {
                            tq = tqBean;
                        } else {
                            dk = tqBean;
                        }
                    }
                    if (tqBeans == null || tqBeans.size() == 0) {
                        description.setText("您当前还没有预约");
                        cancelYY.setVisibility(View.GONE);
                        cancelDK.setVisibility(View.GONE);
                    } else if (tqBeans.size() == 1) {
                        YYCode.DataBean tqBean = tqBeans.get(0);
                        int type = tqBean.getBookingtype();
                        if (type == 1) {
                            cancelYY.setVisibility(View.VISIBLE);
                            cancelDK.setVisibility(View.GONE);
                        } else {
                            cancelYY.setVisibility(View.GONE);
                            cancelDK.setVisibility(View.VISIBLE);
                        }
                        description.setText("您已预约:" + tqBean.getBookingdate() + (type == 1 ? "办理提取业务" : "办理贷款业务"));
                    } else {
                        cancelYY.setVisibility(View.VISIBLE);
                        cancelDK.setVisibility(View.VISIBLE);
                        String descript = "您已预约:";
                        for (YYCode.DataBean tqBean : tqBeans) {
                            descript += "\n" + tqBean.getBookingdate() + (tqBean.getBookingtype() == 1 ? "办理提取业务" : "办理贷款业务");
                        }
                        description.setText(descript);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                new MyToast(PreHandlerActivity.this, "获取信息错误", 1);

            }
        });

    }

    @OnClick({R.id.tiqu, R.id.daikaun, R.id.cancelYY, R.id.cancelDK})
    public void onViewClicked(View view) {

//        Intent intent = new Intent(this, Appointment.class);
        Intent intent = new Intent(this, YuYueActivity2.class);
        switch (view.getId()) {
            case R.id.tiqu:
                intent.putExtra("type", 1);
                MySP.saveData(this, "ywlx", 1);
                startActivityForResult(intent, 100);
                break;
            case R.id.daikaun:
                intent.putExtra("type", 2);
                MySP.saveData(this, "ywlx", 2);
                startActivityForResult(intent, 200);
                break;
            case R.id.cancelYY:
                cancel(1);
                break;
            case R.id.cancelDK:
                cancel(2);
                break;
        }
    }

    private void cancel(final int type) {
        YYCode.DataBean tqBea = null;
        for (YYCode.DataBean tqBean : tqBeans) {
            if (type == tqBean.getBookingtype()) {
                tqBea = tqBean;
                break;
            }
        }
        if (tqBea.getBookingdate() != null) {
            new AlertDialog.Builder(this)
                    .setItems(new CharSequence[]{"确定取消预约吗？"}, null)
                    .setCancelable(true)
                    .setPositiveButton("取消预约", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelYY(PreHandlerActivity.this, new MyCallback() {
                                @Override
                                public void call(Object o) {
                                    if (type == 1) {
                                        tq = null;
                                    } else {
                                        dk = null;
                                    }
                                    initData();
                                }
                            }, type);
                        }
                    }).create().show();

            return;
        }
    }

    public static void cancelYY(final Context context, final MyCallback callback, final int type) {
        String username = MySP.loadData(context, "username", "") + "";

        final YYCode.DataBean first = type == 1 ? tq : dk;
        if (first == null) {
            return;
        }
        dialog = ProgressUtils.showProgressDialog(context);
        ApiService.YYCancel(new RequestParams()
                .add("qxyylx", type)
                .add("bookingDate", first.getBookingdate())
                .add("bookingTime", first.getBookingtime())
                .add("acc_code", username
                ), new MyObserve<String>(context) {
            @Override
            public void onNext(String value) {
                dialog.dismiss();
                if (null == value)
                    return;
                ApiService.load(context, new MyCallback<AccountBean>() {
                    @Override
                    public void call(AccountBean accountBeanz) {
                        sendMessage(context, accountBeanz.getMobile(), accountBeanz.getName(), type, first.getBookingdate());
                    }
                });

//                MySP.saveData(context,"ywlx",1);
//                new MyToast(context, "取消预约成功！", 0);
                if (callback != null)
                    callback.call(null);

            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                new MyToast(context, e.getMessage(), 1);
            }
        });
    }

    private static void sendMessage(final Context context, final String phone, String name, int type, String date) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("jyrq", date.substring(0, 10).replace("-", ""));
            jsonObject.put("jysj", date.substring(11, date.length()).replace("-", ""));
            jsonObject.put("sjh", phone);
            jsonObject.put("dxnr", String.format(Api.TEMP_MESSAGE_CANCEl, name, type == 1 ? "提取" : "贷款", date.substring(0, 10) + "，" + date.substring(10, date.length())));
            System.out.println(String.format(Api.TEMP_MESSAGE_CANCEl, name, type == 1 ? "提取" : "贷款", date.substring(0, 10) + "，" + date.substring(10, date.length())));
//            (Integer.parseInt(time.substring(0, 2)) > 12 ? "下午" : "上午") +
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendMessage(phone, String.format(Api.TEMP_MESSAGE_CANCEl, name, type == 1 ? "提取" : "贷款", date.substring(0, 10) + "，" + date.substring(10, date.length())), new MyObserve<String>() {
            @Override
            public void onNext(String value) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + value);
                if (value.contains("\"status\":\"1\"")) {
                    new MyToast(context, "取消预约成功", 1);
                } else {
                    new MyToast(context, "取消预约失败", 1);
                }
            }

            @Override
            public void onError(Throwable e) {
                new MyToast(context, "取消失败", 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "onActivityResult: " + requestCode + "" + resultCode);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            initData();

        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            initData();
        } else {
            initData();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tq = null;
        dk = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
