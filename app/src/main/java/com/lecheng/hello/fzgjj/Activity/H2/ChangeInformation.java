package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeInformation extends Activity {

    @Bind(R.id.cb1)
    CheckBox cb1;
    @Bind(R.id.btn1)
    Button btn1;
    ActionBar frag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2a);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人资料变更");
    }

    @OnClick({R.id.btn1, R.id.ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (!cb1.isChecked())
                    new MyToast(this, "请勾选同意协议！", 1);
                else {
                    startActivity(new Intent(this, ChangeInformation_Sub.class));
                    finish();
                }
                break;
        }
    }
}
