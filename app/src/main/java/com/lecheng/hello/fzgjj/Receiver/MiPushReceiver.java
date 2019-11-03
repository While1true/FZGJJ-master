package com.lecheng.hello.fzgjj.Receiver;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.lecheng.hello.fzgjj.Activity.H2.PreHandlerActivity;
import com.lecheng.hello.fzgjj.Activity.H4.PushMessageActivity;
import com.lecheng.hello.fzgjj.Activity.ReUI.HomeActivity;
import com.lecheng.hello.fzgjj.Bean.MessageBean;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.RequestParams;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

import RxWeb.GsonUtil;
import RxWeb.MyObserve;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by vange on 2017/9/21.
 */

public class MiPushReceiver extends PushMessageReceiver {
    //传递给融云处理了，我只拿到resid给服务器来进行自己的推送
    String mRegId;

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onCommandResult: " + mRegId);
            }
        }
        super.onCommandResult(context, message);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
                MySP.saveData(context, "channelId", mRegId);
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onReceiveRegisterResult: " + mRegId);
            }
        }
    }

    public static void notify(Context context, String mRegId) {

        String username = MySP.loadData(context, "username", "").toString();
        boolean pushenable = (boolean) MySP.loadData(context, "pushenable", true);
        if (!TextUtils.isEmpty(username) && pushenable) {
            ApiService.bindChannelID(new RequestParams()
                            .add("username", username)
                            .add("channelId", mRegId)
                    , new MyObserve<String>() {
                        @Override
                        public void onNext(String value) {
                            if (Constance.DEBUGTAG)
                                Log.i(Constance.DEBUG, "onNext:notify " + value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (Constance.DEBUGTAG)
                                Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "notifyERROR--", "onError: ");
                        }
                    });
        }
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        String mMessage = message.getContent();
        if (!TextUtils.isEmpty(message.getTopic())) {
            String mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            String mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            String mUserAccount = message.getUserAccount();
        }
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "onReceivePassThroughMessage: " + mMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        String mMessage = message.getContent();
        if (!TextUtils.isEmpty(message.getTopic())) {
            String mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            String mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            String mUserAccount = message.getUserAccount();
        }

        try {
            JSONObject object = new JSONObject(mMessage);
            int type = object.getInt("type");
            String content = object.getString("context");
            switch (type) {
                case 2:
                    MessageBean messageBean = GsonUtil.GsonToBean(content, MessageBean.class);
                    if (messageBean == null)
                        return;
                    Intent intent = new Intent();
                    intent.putExtra("type", messageBean.getType());
                    intent.setClass(context.getApplicationContext(), PushMessageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivities(new Intent[]{new Intent(context.getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), intent});
                    break;
                case 3:
                    Intent intent3 = new Intent();
                    intent3.setClass(context.getApplicationContext(), PreHandlerActivity.class);
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "onNotificationMessageClicked: " + mMessage);
    }


    /**
     * type:2:还款提示推送
     *  3:预约成功推送
     *
     * @param context
     * @param message
     */

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        String mMessage = message.getContent();
        if (!TextUtils.isEmpty(message.getTopic())) {
            String mTopic = message.getTopic();
        } else if (!TextUtils.isEmpty(message.getAlias())) {
            String mAlias = message.getAlias();
        } else if (!TextUtils.isEmpty(message.getUserAccount())) {
            String mUserAccount = message.getUserAccount();
        }
        try {
            JSONObject object = new JSONObject(mMessage);
            int type = object.getInt("type");
            String content = object.getString("context");
            switch (type) {
                case 2:
                    saveMessage(content);
                    break;
                case 3:
//                    YYCode yyCode = GsonUtil.GsonToBean(content, YYCode.class);
//                    MySP.saveData(context,"appcode",yyCode.getAppcode());


                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "onNotificationMessageArrived: " + mMessage);
    }

    private void saveMessage(String s2) {
        Observable.just(s2)
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull String s) throws Exception {
                        final MessageBean messageBean = GsonUtil.GsonToBean(s, MessageBean.class);
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "apply: " + messageBean.getName() + "----" + messageBean.getType());
                        return Observable.create(new ObservableOnSubscribe<Boolean>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                                List<MessageBean> messages = DataSupport.where("typeid = " + messageBean.getTypeid() + " and today = \"" + messageBean.getToday() + "\"").find(MessageBean.class);
                                if (messages == null || messages.size() > 0) {
                                    e.onNext(false);
                                    e.onComplete();
                                } else {
                                    e.onNext(messageBean.save());
                                    e.onComplete();
                                }
                            }
                        });
                    }
                }).cast(Boolean.class).subscribe(new MyObserve<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "保存推送消息到数据库: " + (aBoolean ? "成功！" : "失败！"));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
