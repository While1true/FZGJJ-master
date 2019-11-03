package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDebitQuery extends Activity {


    ActionBar frag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2g);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人贷款查询");
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4,R.id.tv5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                startActivity(new Intent(this, PersonalDebitInfo.class));
                break;
            case R.id.tv2:
                startActivity(new Intent(this, DebitAccountInfo.class));
                break;
            case R.id.tv3:
                startActivity(new Intent(this, LoanRepaymentInfo.class));
                break;
            case R.id.tv4:
                startActivity(new Intent(this, TQRepaymentInfo.class));
                break;
            case R.id.tv5:
                startActivity(new Intent(this, CreditListActivity.class));
                break;
        }
    }


}
