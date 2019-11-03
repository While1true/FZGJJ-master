package com.lecheng.hello.fzgjj.Receiver;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by vange on 2017/9/25.
 */

public class MiPushUtils {
    public static final String APP_ID = "2882303761517620122";
    public static final String APP_KEY = "5451762060122";
    public static final String TAG = "MiPushUtils";

    public static void init(Application application) {
        //初始化push推送服务
        if (shouldInit(application)) {
            MiPushClient.registerPush(application, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(application, newLogger);
    }

    private static boolean shouldInit(Application application) {
        ActivityManager am = ((ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = application.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
