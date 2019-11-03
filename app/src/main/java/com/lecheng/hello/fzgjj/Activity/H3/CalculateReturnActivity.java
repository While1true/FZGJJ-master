package com.lecheng.hello.fzgjj.Activity.H3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.lecheng.hello.fzgjj.Bean.CalculateBean;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RxWeb.MyObserve;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class CalculateReturnActivity extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.year_spinner)
    Spinner yearSpinner;
    @Bind(R.id.ze_editText)
    EditText zeEditText;
    @Bind(R.id.lv_spinner)
    Spinner lvSpinner;


    @Bind(R.id.textView18)
    TextView textView18;
    @Bind(R.id.result_title)
    TextView resultTitle;
    @Bind(R.id.result_content)
    TextView resultContent;
    @Bind(R.id.hkze)
    TextView hkze;
    @Bind(R.id.zflx)
    TextView zflx;
    @Bind(R.id.sqfk)
    TextView sqfk;
    @Bind(R.id.dkys)
    TextView dkys;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.gjjze_editText)
    EditText gjjzeEditText;
    @Bind(R.id.syze_editText)
    TextView syzeEditText;
    @Bind(R.id.sy_spinner)
    Spinner sySpinner;
    @Bind(R.id.result_title2)
    TextView resultTitle2;
    @Bind(R.id.hkze2)
    TextView hkze2;
    @Bind(R.id.zflx2)
    TextView zflx2;
    @Bind(R.id.sqfk2)
    TextView sqfk2;
    @Bind(R.id.dkys2)
    TextView dkys2;
    @Bind(R.id.result_content2)
    TextView resultContent2;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_return);
        ButterKnife.bind(this);
        adapter1 = new ArrayAdapter<>(CalculateReturnActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.businessrate));
        adapter = new ArrayAdapter<>(CalculateReturnActivity.this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(new String[]{"按公积金贷款利率计算"}));
        lvSpinner.setAdapter(adapter);
        sySpinner.setAdapter(adapter1);
        zeEditText.addTextChangedListener(this);
        gjjzeEditText.addTextChangedListener(this);
    }

    @OnClick({R.id.iv_back, R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        KeyboardUtils.hideSoftInput(this);
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.button:
                doRepay(0);
                break;
            case R.id.button2:
                doRepay(1);
                break;
        }
    }

    /**
     * 0:等额本息 1：等额本金
     *
     * @param i
     */
    private void doRepay(final int i) {
        String total = zeEditText.getText().toString();
        if (TextUtils.isEmpty(total)) {
            new MyToast(this, "请输入总金额", 1);
            return;
        }
        int ze = Integer.parseInt(zeEditText.getText().toString());
        int gjj = Integer.parseInt(gjjzeEditText.getText().toString());
        if (ze < gjj) {
            new MyToast(this, "公积金贷款金额不能大于贷款总金额", 1);
            return;
        }
        button.setSelected(i == 0 ? true : false);
        button2.setSelected(i == 0 ? false : true);
        Observable.zip(getGJJResult(), getSYResult(), new BiFunction<CalculateBean, CalculateBean, List<CalculateBean>>() {
            @Override
            public List<CalculateBean> apply(CalculateBean calculateBean, CalculateBean calculateBean2) throws Exception {
                List<CalculateBean> beans = new ArrayList<>();
                beans.add(calculateBean);
                beans.add(calculateBean2);
                return beans;
            }
        }).subscribe(new MyObserve<List<CalculateBean>>() {
            @Override
            public void onNext(List<CalculateBean> value) {
                if (value.size() != 2) {
                    new MyToast(CalculateReturnActivity.this, "计算出错", 0);
                    return;
                }
                onResult(value, i);
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d("--", e.getMessage());
            }
        });

    }

    /**
     * //0:公积金
     *
     * @return
     */
    public Observable<CalculateBean> getGJJResult() {
        RequestParams requestParams = new RequestParams();
        String gjjTotal = gjjzeEditText.getText().toString();
        requestParams.add("gjjbal", gjjTotal);
        requestParams.add("repaymentBal", gjjTotal);
        requestParams.add("repaymentYears", yearSpinner.getSelectedItemPosition()+1);
        return WebUtils.doSoapNet(CalculateBean.class, Api.CALCULATE_DKHK, requestParams);
    }

    /**
     * 商业贷款
     *
     * @return
     */
    public Observable<CalculateBean> getSYResult() {
        RequestParams requestParams = new RequestParams();
        int year = yearSpinner.getSelectedItemPosition() + 1;
        String total = syzeEditText.getText().toString();
        int loanPercentage = sySpinner.getSelectedItemPosition();
        requestParams.add("businessrate", loanPercentage);
        requestParams.add("gjjbal", total);
        requestParams.add("repaymentYears", year);
        requestParams.add("repaymentBal", total);
        return WebUtils.doSoapNet(CalculateBean.class, Api.CALCULATE_DKHK, requestParams);
    }

    private void onResult(List<CalculateBean> beans, int i) {
//        scrollView.smoothScrollTo(0, 0);
        CalculateBean bean = beans.get(0);
        CalculateBean bean2 = beans.get(1);

        String segment = i == 0 ? "等额本息\n计算结果：（元）" : "等额本金\n计算结果：（元）";
        resultTitle.setText("公积金按" + segment);
        resultTitle2.setText("商业贷款按" + segment);
        hkze.setText("还款总额：" + (i == 0 ? bean.getGjjdebx().getHkze() : bean.getGjjdebj().getHkze()));
        zflx.setText("支付利息：" + (i == 0 ? bean.getGjjdebx().getZflxk() : bean.getGjjdebj().getZflxk()));
        sqfk.setText("首期付款：" + (i == 0 ? bean.getGjjdebx().getSqfk() : bean.getGjjdebj().getSqfk()));
        dkys.setText("贷款月数：" + (i == 0 ? bean.getGjjdebx().getDkys() : bean.getGjjdebj().getDkys()));
        List<String> strings = i == 0 ? bean.getGjjdebx().getYhje() : bean.getGjjdebj().getYhje();
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string + "\n");
        }
        resultContent.setText(builder.toString());

        hkze2.setText("还款总额：" + (i == 0 ? bean2.getSydebx().getHkze() : bean2.getSydebj().getHkze()));
        zflx2.setText("支付利息：" + (i == 0 ? bean2.getSydebx().getZflxk() : bean2.getSydebj().getZflxk()));
        sqfk2.setText("首期付款：" + (i == 0 ? bean2.getSydebx().getSqfk() : bean2.getSydebj().getSqfk()));
        dkys2.setText("贷款月数：" + (i == 0 ? bean2.getSydebx().getDkys() : bean2.getSydebj().getDkys()));
        List<String> strings2 = i == 0 ? bean2.getSydebx().getYhje() : bean2.getSydebj().getYhje();
        StringBuilder builder2 = new StringBuilder();
        for (String string : strings2) {
            builder2.append(string + "\n");
        }
        resultContent2.setText(builder2.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        int ze = 0;
        int gjj =0;
        try {
            ze=Integer.parseInt(zeEditText.getText().toString());
            gjj=Integer.parseInt(gjjzeEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        syzeEditText.setText(Math.max(0, ze - gjj) + "");
        if (ze < gjj) {
            new MyToast(this, "公积金贷款金额不能大于贷款总金额", 1);
        }
    }
}
