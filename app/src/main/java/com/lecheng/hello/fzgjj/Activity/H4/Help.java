package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home43);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("在线客服");

    }

    @OnClick({R.id.llCall, R.id.tvInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCall:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "059112329"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tvInfo:
                break;
        }
    }
}
