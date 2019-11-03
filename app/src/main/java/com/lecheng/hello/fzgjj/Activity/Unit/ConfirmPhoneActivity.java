package com.lecheng.hello.fzgjj.Activity.Unit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.lecheng.hello.fzgjj.Bean.Access;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/12/8.
 */

public class ConfirmPhoneActivity extends BaseTitleActivity {
    @Bind(R.id.account)
    EditText account;
    @Bind(R.id.info)
    TextView info;

    @Override
    protected int getContentLayoutId() {
        return R.layout.confirm_account_phone;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("发送验证码");

    }

    @OnClick(R.id.send)
    public void onViewClicked() {
       final String input = account.getText().toString();
        if(!RegexUtils.isIDCard18(input)){
            new MyToast(this,"请输入正确的身份证",0);
            return;
        }
        ApiService.ZHMM(new MyObserve<Access>(this) {
            @Override
            public void onNext(Access value) {
                if(value.getStatus()==1&&!TextUtils.isEmpty(value.getData())) {
                    new MyToast(ConfirmPhoneActivity.this, "验证成功", 0);
                    Intent intent = new Intent(ConfirmPhoneActivity.this, ChangePassword.class);
                    intent.putExtra("user",input);
                    intent.putExtra("phone",value.getData());
                    startActivity(intent);
                    finish();
                }else{
                    info.setVisibility(View.VISIBLE);
                    new MyToast(ConfirmPhoneActivity.this, "验证失败", 0);
                }
                System.out.println(value+"--------------------------");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError--------------------------");
            }
        },input);

    }
}
