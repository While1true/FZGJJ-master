package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Bean.Access;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.List;
import java.util.concurrent.TimeUnit;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vange on 2017/12/1.
 */

public class ChangePassword extends BaseTitleActivity {


    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.confirmPassword)
    EditText confirmPassword;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.tvtitle)
    TextView tvtitle;
    @Bind(R.id.send)
    Button send;
    private String user;
    private String phone;

    @Override
    protected int getContentLayoutId() {
        return R.layout.changepassword_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("找回密码");
        user = getIntent().getStringExtra("user");
        phone = getIntent().getStringExtra("phone");
        ApiService.QueryAccount(new RequestParams()
                .add("gjjaccount", user), new MyObserve<List<AccountBean>>() {
            @Override
            public void onNext(List<AccountBean> bean) {
                AccountBean value = bean.get(0);
                tvtitle.setText("验证码已发送到：" + value.getMobile() + "\n如果不是您的手机号请到省直单位住房公积金修改手机号");
            }

            @Override
            public void onError(Throwable e) {
            }
        });
        doCount();
    }

    @OnClick({R.id.send, R.id.change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                doCount();
                break;
            case R.id.change:
                String pass = etPassword.getText().toString();
                String confirmPasswordx = confirmPassword.getText().toString();
                if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPasswordx)) {
                    new MyToast(ChangePassword.this, "请输入新密码", 1);
                    return;
                }
                if (!pass.equals(confirmPasswordx)) {
                    new MyToast(ChangePassword.this, "两次输入的密码不一致", 1);
                    return;
                }
                final String code = etCode.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    new MyToast(ChangePassword.this, "请输入验证码", 1);
                    return;
                }
                final String mdsPass = com.lecheng.hello.fzgjj.Utils.EncryptUtils.md5(pass).toUpperCase();
                        ApiService.WJMM(new MyObserve<Access>(this) {
                            @Override
                            public void onNext(Access value) {
                                if(1==value.getStatus()) {
                                    new MyToast(ChangePassword.this, value.getData(), 0);
                                    if("密码修改成功".equals(value.getData())) {
                                        finish();
                                    }
                                }else{
                                    new MyToast(ChangePassword.this, value.getMsg(), 0);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }, new RequestParams()
                                .add("username", user)
                                .add("newPassword", mdsPass)
                                .add("mobile", phone)
                                .add("yzm", code));
                break;
        }
    }

    private void doCount() {
        ApiService.ZHMM(new MyObserve<Access>(this) {
            @Override
            public void onNext(Access value) {
                if(value.getStatus()==1&&!TextUtils.isEmpty(value.getData())) {
                    new MyToast(ChangePassword.this, "发送成功", 0);
                }else{
                    new MyToast(ChangePassword.this, "发送失败", 0);
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError--------------------------");
            }
        },user);
        send.setEnabled(false);
        send.setText(60 + "s后获取");
        send.setBackgroundColor(getResources().getColor(R.color.colorGray));
        Observable.interval(1, TimeUnit.SECONDS)
                .take(60)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserve<Long>() {
                    @Override
                    public void onNext(Long value) {
                        if (value == 59) {
                            send.setBackgroundResource(R.drawable.button_back_selector);
                            send.setEnabled(true);
                            send.setText("获取验证码");
                        } else {
                            send.setText((59 - value) + "s后获取");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        send.setEnabled(true);
                        send.setText("获取验证码");
                    }
                });
    }
}
