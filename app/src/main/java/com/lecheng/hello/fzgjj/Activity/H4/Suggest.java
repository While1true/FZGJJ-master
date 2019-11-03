package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class Suggest extends Activity implements IWSListener {

    @Bind(R.id.etTitle)
    EditText etTitle;
    @Bind(R.id.etContent)
    EditText etContent;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.etName)
    EditText etName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4f);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("投诉建议");

    }

    @OnClick({R.id.btn1, R.id.ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                String[] key = {"title", "content", "name", "mail", "address", "telephone"};
                String text = etTitle.getText().toString();
                String etPhones = etPhone.getText().toString();
                String name = etName.getText().toString();
                String content = etContent.getText().toString();
                if(TextUtils.isEmpty(text)){
                    new MyToast(this,"标题不能为空",0);
                    return;
                }
                if(TextUtils.isEmpty(content)){
                    new MyToast(this,"投诉内容不能为空",0);
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    new MyToast(this,"姓名不能为空",0);
                    return;
                }
                if(TextUtils.isEmpty(etPhones)||etPhones.length()!=11){
                    new MyToast(this,"情输入有效的手机号码",0);
                    return;
                }

                String[] value = {text, content, name,
                        etEmail.getText() + "", etAddress.getText() + "", etPhones};
                new HttpGo().httpWebService(this, this, "tsjy", key, value);
                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            if (new JSONObject(s).getString("status").equals("1")) {
                new MyToast(this, "提交成功", 1);
                finish();
            } else
                new MyToast(this, "提交失败,请重试", 1);
        } catch (JSONException e) {
            new MyToast(this, "提交失败,请重试", 1);
        }
    }
}
