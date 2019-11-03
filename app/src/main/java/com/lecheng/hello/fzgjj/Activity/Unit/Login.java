package com.lecheng.hello.fzgjj.Activity.Unit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.RegexUtils;
import com.lecheng.hello.fzgjj.Bean.BeanLogin;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Receiver.MiPushUtils;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import RxWeb.GsonUtil;

import RxWeb.MyObserve;
import RxWeb.RxLifeUtils;
import RxWeb.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用说明  ：http://p.codekk.com/detail/Android/tvbarthel/BlurDialogFragment
 * 在 fragment继承这几个类后，在oncreate方法中加入
 * this.debug(true);
 * this.setBlurRadius(4);
 * this.setDownScaleFactor(5.0f);
 * Created by Cheng on 2017/3/5.
 **/

public class Login extends DialogFragment implements IWSListener {

    @Bind(R.id.account)
    EditText etAccount;
    @Bind(R.id.password)
    EditText etPwd;
    @Bind(R.id.remember)
    CheckBox cbRemember;
    public boolean isGetInstance = false;
    public static Login login;
    @Bind(R.id.Login)
    Button btnLogin;
    @Bind(R.id.checkimg)
    ImageView checkimg;
    private HttpGo httpGo = new HttpGo();

    public static Login getInstance() {
//        if (login == null) {
            login = new Login();
//        }
        return login;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.login_layout, null, false);
        ButterKnife.bind(this, root);
        etAccount.setEnabled(true);
        etAccount.requestFocus();
        etAccount.setMaxEms(1);
        etPwd.setMaxEms(1);
        etPwd.setEnabled(true);
        String account = MySP.loadData(getActivity(), "account", "")+"";
        String password=MySP.loadData(getActivity(), "password", "")+"";
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkimg.setSelected(true);
                }else {
                    checkimg.setSelected(false);
                }
            }
        });
        if(!TextUtils.isEmpty(account)) {
            etAccount.setText(account);
            etPwd.setText(password);
            cbRemember.setChecked(true);
        }
//        etAccount.setText(MySP.loadData(getActivity(), "username", "") + "");
        return root;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        System.out.println("Login被销毁");
        RxLifeUtils.getInstance().remove(this);
        isGetInstance = false;
    }

    @OnClick({R.id.Login, R.id.forget,R.id.checkimg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login:
               final String account = etAccount.getText().toString();
               final String password = etPwd.getText().toString();
                if(!RegexUtils.isIDCard18(account)){
                    new MyToast(getActivity(), "请输入正确身份证号", 1);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    new MyToast(getActivity(), "密码不能为空", 1);
                    return;
                }
                WebUtils.doSoapNet("login",new RequestParams()
                .add("username",account)
                .add("password",password)
                .add("phoneId"))
                        .subscribe(new MyObserve<String>(this) {
                            @Override
                            public void onNext(String value) {

                                saveAccountAndPassword(account,password);
                                onWebServiceSuccess(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                new MyToast(getActivity(), "登录失败，请重试", 1);
                            }
                        });
                new MyToast(getActivity(), "正在登陆..." + etAccount.getText() + etPwd.getText(), 1);
                btnLogin.setEnabled(false);
                break;
            case R.id.forget:
                Intent intent = new Intent(getContext(), ConfirmPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.checkimg:
                cbRemember.setChecked(!cbRemember.isChecked());
                break;
        }
    }

    private void saveAccountAndPassword(String account,String password) {
        if(cbRemember.isChecked()){
            MySP.saveData(getActivity(), "account", account);
            MySP.saveData(getActivity(), "password", password);
        }else{
            MySP.saveData(getActivity(), "account", "");
            MySP.saveData(getActivity(), "password", "");
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            final BeanLogin bean = GsonUtil.GsonToBean(s, BeanLogin.class);
            MySP.saveData(getActivity(), "id", bean.getData().getId());
            MySP.saveData(getActivity(), "username", bean.getData().getUsername());
            MySP.saveData(getActivity(), "vc", bean.getData().getVerioncode());

            new MyToast(getActivity(), "登录成功！", 1);
            getFragmentManager().popBackStack();
            MiPushUtils.init(getActivity().getApplication());
//            PushManager.startWork(getActivity().getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, MainActivity.PUSH_API_KEY);//百度推送注册
//            PushManager.resumeWork(getActivity());
        } catch (Exception e) {
            new MyToast(getActivity(), "登录失败，请重试", 1);
        }
        btnLogin.setEnabled(true);
    }
}
