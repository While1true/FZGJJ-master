package com.lecheng.hello.fzgjj.Activity.H3;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import RxWeb.BaseAppCompatActivity;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculateDepositeActivity extends BaseAppCompatActivity {

    @Bind(R.id.ye_et)
    EditText yeEt;
    @Bind(R.id.sex_spinner)
    Spinner sexSpinner;
    @Bind(R.id.age_spinner)
    Spinner ageSpinner;
    @Bind(R.id.js_et)
    EditText jsEt;
    @Bind(R.id.space_spinner)
    Spinner spaceSpinner;
    @Bind(R.id.hj_spinner)
    Spinner hjSpinner;
    @Bind(R.id.fz_et)
    EditText fzEt;
    @Bind(R.id.times_spinner)
    Spinner timesSpinner;
    @Bind(R.id.js_bt)
    Button jsBt;

    @Bind(R.id.wedding_spinner)
    Spinner weddingSpinner;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.ye_et2)
    EditText yeEt2;
    @Bind(R.id.ck_et2)
    EditText ckEt2;
    @Bind(R.id.sex_spinner2)
    Spinner sexSpinner2;
    @Bind(R.id.js_et2)
    EditText jsEt2;
    @Bind(R.id.age_spinner2)
    Spinner ageSpinner2;
    @Bind(R.id.space_spinner2)
    Spinner spaceSpinner2;
    @Bind(R.id.hj_spinner2)
    Spinner hjSpinner2;
    @Bind(R.id.ck_et)
    EditText ckEt;
    @Bind(R.id.aa)
    ConstraintLayout aa;



    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_calculate_deposite);
        ButterKnife.bind(this);

        weddingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    aa.setVisibility(View.VISIBLE);

                    //Visable
                } else {
                    //Invisable
                    aa.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.js_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.clear_bt:
//                yeEt.setText("");
//                jsEt.setText("");
//                fzEt.setText("");
//                tvResult.setText("");
//                tvIntroduce.setText("");
//                break;
            case R.id.js_bt:
                KeyboardUtils.hideSoftInput(this);
                doCalculate();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void doCalculate() {
        String ye = yeEt.getText().toString();
        String sex1 = sexSpinner.getSelectedItem().toString().equals("男")?"1":"0";
        String age1 = ageSpinner.getSelectedItem().toString();
        String space1 = spaceSpinner.getSelectedItem().toString();
        String js = jsEt.getText().toString();
        String hj1 = hjSpinner.getSelectedItem().toString().equals("福州户籍")?"0":"1";
        String fz = fzEt.getText().toString();
        String times = timesSpinner.getSelectedItem().toString().equals("首次")?"0":"1";
        String wedding = weddingSpinner.getSelectedItem().toString().equals("已婚")?"0":"1";
        String ck = ckEt.getText().toString();

        String ye2 = yeEt2.getText().toString();
        String sex2 = sexSpinner2.getSelectedItem().toString().equals("男")?"1":"0";
        String age2 = ageSpinner2.getSelectedItem().toString();
        String space2 = spaceSpinner2.getSelectedItem().toString().equals("福州地区")?"0":"1";
        String js2 = jsEt2.getText().toString();
        String hj2 = hjSpinner2.getSelectedItem().toString().equals("福州户籍")?"0":"1";
        String ck2 = ckEt2.getText().toString();

        if(TextUtils.isEmpty(ck)||TextUtils.isEmpty(ye)){
            new MyToast(this,"请输入月缴存款和账户余额",0);
            return;
        }

        RequestParams params = new RequestParams();
        params.add("sex", sex1)
                .add("age", age1)
                .add("monnum", js)
                .add("monbal", ck)
                .add("accbal", ye)
                .add("register", hj1)
                .add("mairragestate", wedding)

                .add("spousesex", sex2)
                .add("spouseage", age2)
                .add("spousemonnum", js2)
                .add("spousemonbal", ck2)
                .add("spousaccbal", ye2)
                .add("spouseDeposit",space2)
                .add("spouseregister", hj2)

                .add("liabilities", fz)
                .add("num",times);


        ApiService.CalCulateDeposite(params, new MyObserve<String>(this) {
            @Override
            public void onNext(String value) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onNext: "+value);
                tvResult.setText("最高贷款额度："+value+"万元");
                tvIntroduce.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {
                if (Constance.DEBUGTAG)
                    Log.e(Constance.DEBUG, "onError: "+e.getMessage());

            }
        });


    }

}
