package com.lecheng.hello.fzgjj.Widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/12/15.
 */

public class MyDialogFragment extends DialogFragment {
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.iv_mzt)
    View iv_mzt;
    @Bind(R.id.iv_gjj)
    View iv_gjj;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.size)
    TextView size;
    private View inflate;
    private MyCallback<MyDialogFragment> callback;
    UpdateBean bean;

    public MyDialogFragment setCallback(MyCallback<MyDialogFragment> callback) {
        this.callback = callback;
        return this;
    }

    private int layout;

    public MyDialogFragment setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public MyDialogFragment setBean(UpdateBean bean) {
        this.bean = bean;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        DialogInterface.OnKeyListener keylistener= new DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode== KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

                    return true;

                } else {

                    return false;

                }

            }

        };

        dialog.setOnKeyListener(keylistener);
        return dialog;
    }

    private void initView() {
        size.setText("文件大小：" + bean.getAppSize());
        version.setText("版本号：" + bean.getVersionNumber());
        info.setText(bean.getUpdateInformation().replace("\\n", "\n"));
        iv_mzt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Uri uri = Uri.parse("https://mztapp.fujian.gov.cn:8190/mztAppWeb/app/downpage/index.jsp");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        });
        iv_gjj.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Uri uri = Uri.parse("http://cx.fjszgjj.com:7010/appfront/apk/fjgjj.apk");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                ActivityUtils.finishAllActivities();
//                dismiss();
                break;
            case R.id.confirm:
                if (callback != null) {
                    callback.call(this);
                }
                break;
        }
    }
}
