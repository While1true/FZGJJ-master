package com.lecheng.hello.fzgjj.Utils;

import android.app.Activity;
import android.util.Log;

import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import RxWeb.MyObserve;

/**
 * Created by vange on 2017/11/3.
 */

public class MessageUtils {
    public static void sendMessage(final Activity context,String phone, String message, final MyCallback callback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("jyrq", new SimpleDateFormat("yyyyMMdd").format(new Date()));
            jsonObject.put("jysj", new SimpleDateFormat("HHmmss").format(new Date()));
            jsonObject.put("sjh", phone);
            jsonObject.put("dxnr",message );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiService.sendMessage(phone,message, new MyObserve<String>() {
            @Override
            public void onNext(String value) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: " + value);
                if(callback!=null){
                    callback.call(value);
                }
            }

            @Override
            public void onError(Throwable e) {
                new MyToast(context, "失败", 1);
            }
        });



    }
}
