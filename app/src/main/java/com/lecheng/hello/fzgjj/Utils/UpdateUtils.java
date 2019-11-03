package com.lecheng.hello.fzgjj.Utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Widget.MyDialogFragment;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by vange on 2017/12/15.
 */

public class UpdateUtils {

    private static long enqueue;
    private static DownloadManager downloadManager;
    private static String downloadLink;

    public static void checkUpdate(final FragmentActivity context,final UpdateBean updateBean) {
        checkUpdate(context,updateBean,null);
    }
    public static void checkUpdate(final FragmentActivity context, final UpdateBean updateBean, final MyCallback<String>callback) {
        new MyDialogFragment()
                .setBean(updateBean)
                .setLayout(R.layout.update_dialog)
                .setCallback(new MyCallback<MyDialogFragment>() {
                    @Override
                    public void call(MyDialogFragment dialogFragment) {
                        downloadManager = (DownloadManager) context.getApplication().getSystemService(DOWNLOAD_SERVICE);
                        downloadLink = updateBean.getDownloadLink();
                        Uri uri= Uri.parse(downloadLink);
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setAllowedOverRoaming(false);

                        //通知栏显示
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setTitle("福建省直单位住房公积金");
                        request.setDescription("正在下载中...");
                        request.setVisibleInDownloadsUi(true);

                        //设置下载的路径
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, downloadLink.substring(downloadLink.lastIndexOf("/")));
                        enqueue = downloadManager.enqueue(request);
                        context.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                        new MyToast(context,"已转到后台下载...",0);
                        dialogFragment.dismiss();
                        if(callback!=null){
                            callback.call("开始下载");
                        }
                    }
                })
                .show(context.getSupportFragmentManager(), "x");
    }
    private static BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkStatus(context,enqueue);
        }
    };


    public static void reset(Context context){
        context.unregisterReceiver(mReceiver);
        downloadManager=null;
    }
    /**
     * 检查下载状态
     */
    private static void checkStatus(Context context,long id) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downloadManager.query(query);
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                //下载暂停
                case DownloadManager.STATUS_PAUSED:
                    break;
                //下载延迟
                case DownloadManager.STATUS_PENDING:
                    break;
                //正在下载
                case DownloadManager.STATUS_RUNNING:
                    break;
                //下载完成
                case DownloadManager.STATUS_SUCCESSFUL:
                    if(downloadLink!=null) {
                        File file = new File(
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                , downloadLink.substring(downloadLink.lastIndexOf("/")));
                        installAPK(context, file);
                        reset(context);
                    }
                    break;
                //下载失败
                case DownloadManager.STATUS_FAILED:
                    reset(context);
                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        cursor.close();
    }

    /**
     * 7.0兼容
     */
    private static void installAPK(Context context,File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider7.getUriForFile(context, apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
