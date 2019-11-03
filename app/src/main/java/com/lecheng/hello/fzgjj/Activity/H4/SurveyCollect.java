package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SurveyCollect extends AppCompatActivity{

    @Bind(R.id.btnCommit)
    Button btnCommit;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etTitle)
    EditText etTitle;
    @Bind(R.id.etContent)
    EditText etContent;
    private String title;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4k);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("民意征集");
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        etTitle.setText(title);
        id = intent.getStringExtra("id");
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        String tel = etPhone.getText().toString();
        String name = etName.getText().toString();
        String content = etContent.getText().toString();
        if(TextUtils.isEmpty(tel)||tel.length()!=11){
            new MyToast(this,"请输入正确手机号",1);
            return;
        }
        if(TextUtils.isEmpty(name)){
            new MyToast(this,"请输入称呼",1);
            return;
        }
        if(TextUtils.isEmpty(content)){
            new MyToast(this,"请输入您的看法",1);
            return;
        }
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("pid",id);
            jsonObject.put("userid", MySP.loadData(this, "username", "") + "");
            jsonObject.put("title",title);
            jsonObject.put("tel",tel);
            jsonObject.put("content",content);
            jsonObject.put("name",name);
            ApiService.myzjCommitList(jsonObject.toString(), new MyObserve<String>(this) {
                @Override
                public void onNext(String value) {
                    if (Constance.DEBUGTAG)
                        Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: "+value);
                    if("提交成功".equals(value)) {
                        setResult(RESULT_OK);
                        finish();
                    }
                    new MyToast(SurveyCollect.this, value, 1);
                }

                @Override
                public void onError(Throwable e) {
                    new MyToast(SurveyCollect.this, "提交失败", 1);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
