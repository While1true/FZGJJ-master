package com.lecheng.hello.fzgjj.Utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by vange on 2017/11/14.
 */

public class ProgressUtils {
    public static Dialog showProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("请稍后...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
}
