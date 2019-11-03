package com.lecheng.hello.fzgjj.Activity.H4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class ModifyPassword extends AppCompatActivity implements IWSListener {

    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.btnSend)
    Button btnSend;
    @Bind(R.id.etPwd1)
    EditText etPwd1;
    @Bind(R.id.etPwd2)
    EditText etPwd2;
    @Bind(R.id.btnCommit)
    Button btnCommit;
    @Bind(R.id.etOld)
    EditText etOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4bc);
        ActionBar bar = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        bar.setTitle("修改用户密码");
        ButterKnife.bind(this);

    }

    @OnClick({R.id.etOld, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCommit:
                if (etOld.getText().toString().equals("") || (etPwd2.getText().toString().equals(""))) {
                    new MyToast(this, "请输入密码", 1);
                    break;
                }
                if (etPwd1.getText().toString().equals(etPwd2.getText().toString())) {
                    String[] k = {"username", "password", "newpwd"};
                    String[] v = {MySP.loadData(this, "username", "") + "",
                            etOld.getText() + "", etPwd2.getText() + ""};
                    new HttpGo().httpWebService(this, this, "updtaePwd", k, v);
                    btnCommit.setEnabled(false);
                } else
                    new MyToast(this, "两次密码输入不一致", 1);
                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            if (new JSONObject(s).getString("status").equals("1")) {
                new MyToast(this, "密码修改成功", 1);
                finish();
            } else new MyToast(this, "密码修改失败，请重试！", 1);
        } catch (Exception e) {
            new MyToast(this, "密码修改失败，请重试！", 1);
        }
        btnCommit.setEnabled(true);
    }
}
