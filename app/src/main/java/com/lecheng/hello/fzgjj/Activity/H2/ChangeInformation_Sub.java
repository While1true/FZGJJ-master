package com.lecheng.hello.fzgjj.Activity.H2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Bean.YYResult;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MessageUtils;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import RxWeb.BaseAppCompatActivity;
import RxWeb.MyObserve;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class ChangeInformation_Sub extends BaseAppCompatActivity implements IWSListener {

    @Bind(R.id.et1)
    EditText et1;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.et3)
    EditText et3;
    @Bind(R.id.et4)
    EditText et4;
    @Bind(R.id.et5)
    EditText et5;
    @Bind(R.id.btn1)
    Button btn1;
    //    @Bind(R.id.btn2)
//    Button btn2;
    @Bind(R.id.btnSend)
    Button btnSend;
    @Bind(R.id.et6)
    EditText et6;
    @Bind(R.id.llOther)
    LinearLayout llOther;
    @Bind(R.id.llCode)
    LinearLayout llCode;
    ActionBar frag;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.tel)
    EditText tel;
    @Bind(R.id.workplace)
    EditText workplace;
    @Bind(R.id.worktel)
    EditText worktel;
    private int type = 0;
    private String YZM = "";
    private String[] btStr = {"确认信息", "资料修改"};


    @Override
    protected void initView(Bundle a) {
        setContentView(R.layout.home2aa);
        ButterKnife.bind(this);
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人资料变更");
        ApiService.QueryAccount(new RequestParams()
                .add("gjjaccount", MySP.loadData(this, "username", "") + ""), new MyObserve<List<AccountBean>>() {
            @Override
            public void onNext(List<AccountBean> bean) {
                AccountBean value = bean.get(0);
                et1.setText(value.getGjjaccount());
                et2.setText(value.getName());
                et3.setText(value.getCredit_type());
                et4.setText(value.getCredit_code());
                et5.setText(value.getMobile());

                etEmail.setText(value.getEmail());
                etAddress.setText(value.getFamily_address());
                tel.setText(value.getHomephone());
                worktel.setText(value.getUnitsphone());
                workplace.setText(value.getWorkunits());

            }

            @Override
            public void onError(Throwable e) {
                Log.e("aa", "onError: ", e);
            }
        });

    }

    @OnClick({R.id.btn1, R.id.btnSend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Observable.just(btn1.getText().toString())
                        .map(new Function<String, Boolean>() {
                            @Override
                            public Boolean apply(@NonNull String s) throws Exception {
                                return s.equals(btStr[0]);
                            }
                        }).flatMap(new Function<Boolean, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            type = 2;
                            if (TextUtils.isEmpty(et6.getText().toString())) {
                                throw new NullPointerException("请输入验证码");
                            }
                            if (TextUtils.isEmpty(et3.getText().toString())) {
                                throw new NullPointerException("证件类型不能为空");
                            }
                            if (!RegexUtils.isEmail(etEmail.getText().toString().trim())) {
                                throw new NullPointerException("email格式不正确");
                            }
                            if (RegexUtils.isMobileExact(tel.getText().toString())) {
                                throw new NullPointerException("电话号码格式不正确");
                            }

                            if ((YZM.equals(et6.getText().toString()))) {
                                return WebUtils.doSoapNet(Api.MODIFY_ACCOUNT, new RequestParams()
                                                .add("gjjaccount", et4.getText().toString())
//                                        .add("name", et2.getText().toString())
//                                        .add("credit_type", et3.getText().toString())
//                                        .add("credit_code", et4.getText().toString())

                                                .add("email", etEmail.getText().toString())
                                                .add("family_address", etAddress.getText().toString())
                                                .add("homephone", tel.getText().toString())
                                                .add("workunits", workplace.getText().toString())
                                                .add("unitsphone", worktel.getText().toString())
                                                .add("mobile", et5.getText().toString())

                                );
                            } else {
                                throw new NullPointerException("请输入正确的验证码");
                            }

                        } else {
                            btnSend.setTextColor(Color.WHITE);
                            btnSend.setEnabled(true);
//                            et2.setEnabled(true);
//                            et4.setEnabled(true);
//                            if(TextUtils.isEmpty(et5.getText())) {
//                                et5.setEnabled(true);
//                            }
                            et6.setEnabled(true);
                            llOther.setVisibility(View.VISIBLE);
                            llCode.setVisibility(View.VISIBLE);
                            btn1.setText(btStr[1]);
                            throw new NullPointerException();
                        }
                    }
                }).subscribe(new MyObserve<String>() {
                    @Override
                    public void onNext(String s) {
                        onWebServiceSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if (message != null) {
                            new MyToast(ChangeInformation_Sub.this, message, 0);
                        }
                    }
                });

                break;

            case R.id.btnSend:
                final String str = et5.getText().toString();
                if (!RegexUtils.isMobileExact(str)) {
                    showPhoneDialog();
                    return;
                }
                sendYZM(str);
                break;
        }
    }

    private void sendYZM(final String str) {
        type = 1;
        if (YZM.equals("")) {
            YZM = getYZM();
            btnSend.setEnabled(false);
            Observable.interval(1, TimeUnit.SECONDS)
                    .take(61)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserve<Long>() {
                        @Override
                        public void onNext(Long value) {
                            if (value == 60) {
                                YZM = "";
                                btnSend.setText("发送验证码");
                                btnSend.setEnabled(true);
                            } else {
                                btnSend.setText((60 - value) + "后再发送");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            YZM = "";
                            btnSend.setText("发送验证码");
                            btnSend.setEnabled(true);
                        }
                    });
            MessageUtils.sendMessage(this, str, "验证码是：" + YZM + "请不要泄露给别人", new MyCallback() {
                @Override
                public void call(Object o) {
                    if (Constance.DEBUGTAG)
                        Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "call: "+o);
                    if(o.toString().contains("\"status\":\"1\"")) {
                        new MyToast(ChangeInformation_Sub.this, "验证码已发送到" + str, 1);
                    }else{
                        new MyToast(ChangeInformation_Sub.this, "获取验证码失败", 1);
                    }
                }
            });
        }
    }

    private void showPhoneDialog() {
        View inflate = View.inflate(this, R.layout.phone_dialog, null);
        View send = inflate.findViewById(R.id.send);
        final EditText et = (EditText) inflate.findViewById(R.id.editText);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("请输入手机号")
                .setView(inflate)
                .setCancelable(false)
                .create();
        alertDialog.show();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!RegexUtils.isMobileExact(et.getText())) {
                    new MyToast(v.getContext(), "请输入正确的手机号", 1);
                    return;
                }
                sendYZM(et.getText().toString());
                alertDialog.dismiss();
            }
        });
    }
    private String getYZM() {    //随机生成6位数手机验证码
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != 6) {
            return getYZM();
        }
        return result;
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            switch (type) {
                case 0:
                    break;
                case 1:
//                    new MyToast(this, "验证码发送成功", 1);
                    break;
                case 2:
                    JSONObject json = new JSONObject(s);
                    if (json.getString("status").equals("1")) {
                        new MyToast(this, "资料修改成功！", 1);
                        btn1.setTextColor(Color.WHITE);
                        btn1.setText(btStr[0]);
//                        btn2.setTextColor(Color.GRAY);
                        btnSend.setTextColor(Color.GRAY);
                        btn1.setEnabled(true);
//                        btn2.setEnabled(false);
                        btnSend.setEnabled(false);
                        et1.setEnabled(false);
                        et3.setEnabled(false);
                        et4.setEnabled(false);
                        et5.setEnabled(false);
                        et6.setEnabled(false);
                        YZM = "";
                        llOther.setVisibility(View.GONE);
                        btnSend.setText("发送验证码");
                        llCode.setVisibility(View.GONE);
                    } else new MyToast(this, "资料修改失败，请重试", 1);
                    break;
            }
        } catch (Exception e) {
            new MyToast(this, "连接失败，请重试！", 1);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
