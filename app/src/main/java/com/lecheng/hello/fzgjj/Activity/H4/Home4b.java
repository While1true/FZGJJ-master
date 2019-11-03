package com.lecheng.hello.fzgjj.Activity.H4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import de.greenrobot.event.EventBus;
@Deprecated
public class Home4b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home41);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("帐户明细");
    }
}
