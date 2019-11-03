package com.lecheng.hello.fzgjj.Utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.lecheng.hello.fzgjj.R;

/**
 * Created by vange on 2017/9/21.
 */

public class NotificationUtils {
    public static final int NOTIFICATION_REQUESTID=100;

    public static void CreatNotification(Context context, String title,String subTitle,Intent clickIntent){
         Notification build = new NotificationCompat.Builder(context)
                 .setAutoCancel(false)
                 .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(PendingIntent.getActivity(context, NOTIFICATION_REQUESTID, clickIntent, PendingIntent.FLAG_ONE_SHOT))
                .setContentTitle(title)
                .setContentText(subTitle)
                .setShowWhen(true)
                .build();
        NotificationManagerCompat.from(context).notify(NOTIFICATION_REQUESTID,build);
    }
}
