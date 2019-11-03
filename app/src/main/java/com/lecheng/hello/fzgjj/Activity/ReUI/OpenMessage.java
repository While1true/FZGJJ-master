package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.lecheng.hello.fzgjj.Activity.H2.ChangeInformation_Sub;
import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.Bean.Access;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MessageUtils;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.List;
import java.util.Random;
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

public class OpenMessage extends BaseTitleActivity {


    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;
    @Bind(R.id.send)
    Button send;
    private String YZM="";
    private String SENDPHONE="";

    @Override
    protected int getContentLayoutId() {
        return R.layout.openmessage;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("开通短信");
    }

    @OnClick({R.id.send, R.id.change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || !RegexUtils.isMobileSimple(phone)) {
                    new MyToast(OpenMessage.this, "请输入正确的手机号", 1);
                    return;
                }
                SENDPHONE=phone;
                sendYZM(phone);
                break;
            case R.id.change:
                String phonex = etPhone.getText().toString();
                if (TextUtils.isEmpty(phonex) || !RegexUtils.isMobileSimple(phonex)) {
                    new MyToast(OpenMessage.this, "请输入正确的手机号", 1);
                    return;
                }
                final String code = etCode.getText().toString();
                if (TextUtils.isEmpty(code)||!code.equals(YZM)||!phonex.equals(SENDPHONE)) {
                    new MyToast(OpenMessage.this, "请输入正确的验证码", 1);
                    return;
                }
                new MyToast(this,"开通短信成功！",1);
                finish();
                break;
        }
    }
    private void sendYZM(final String str) {
        if (YZM.equals("")) {
            YZM = getYZM();
            send.setEnabled(false);
            Observable.interval(1, TimeUnit.SECONDS)
                    .take(61)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserve<Long>() {
                        @Override
                        public void onNext(Long value) {
                            if (value == 60) {
                                YZM = "";
                                send.setText("发送验证码");
                                send.setEnabled(true);
                            } else {
                                send.setText((60 - value) + "后再发送");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            YZM = "";
                            send.setEnabled(true);
                        }
                    });
            MessageUtils.sendMessage(this, str, "验证码是：" + YZM + "请不要泄露给别人", new MyCallback() {
                @Override
                public void call(Object o) {
                    if (Constance.DEBUGTAG)
                        Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "call: "+o);
                    if(o.toString().contains("\"status\":\"1\"")) {
                        new MyToast(OpenMessage.this, "验证码已发送到" + str, 1);
                    }else{
                        new MyToast(OpenMessage.this, "获取验证码失败", 1);
                    }
                }
            });
        }
    }
    private String getYZM() {    //随机生成6位数手机验证码
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != 6) {
            return getYZM();
        }
        return result;
    }
}
