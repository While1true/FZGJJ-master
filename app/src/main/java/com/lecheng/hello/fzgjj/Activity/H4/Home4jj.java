package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
@Deprecated
public class Home4jj extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4g);
        ActionBar actionBar= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        actionBar.setTitle("在线调查");
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                startActivity(new Intent(this, Home4ja.class));
                break;
            case R.id.tv2:
                startActivity(new Intent(this, Home4ja.class));
                break;
            case R.id.tv3:
                break;
        }
    }
}
