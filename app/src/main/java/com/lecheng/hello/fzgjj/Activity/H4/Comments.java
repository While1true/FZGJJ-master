package com.lecheng.hello.fzgjj.Activity.H4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Comments extends AppCompatActivity implements IWSListener {


    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.sp1)
    Spinner sp1;
    @Bind(R.id.etTitle)
    EditText etTitle;
    @Bind(R.id.etContent)
    EditText etContent;
    private int p = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4da);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("我要留言");
        List<String> array = new ArrayList<>();
        array.add("支取");
        array.add("贷款");
        array.add("缴存");
        array.add("其他");
        sp1.setAdapter(new UnityAdapter<String>(this, array, R.layout.item1) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.tv1, item);
            }
        });
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @OnClick({R.id.btn1, R.id.ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                String[] key = {"name", "mail", "type", "title", "twnr", "account"};
                String[] value = {etName.getText() + "", etEmail.getText() + "", p + "",
                        etTitle.getText() + "", etContent.getText() + "", etPhone.getText() + ""};
                if (etContent.getText() + "" != "" || etPhone.getText() + "" != "")
                    new HttpGo().httpWebService(this, this, "addzxly", key, value);
                else
                    new MyToast(this, "请输入内容/账号！", 1);
                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            JSONObject object = new JSONObject(s);
            if (object.getString("status").equals("1")) {
                new MyToast(this, "留言成功！感谢您的支持！", 1);
                finish();
            } else
                new MyToast(this, "留言失败！请重试！", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
