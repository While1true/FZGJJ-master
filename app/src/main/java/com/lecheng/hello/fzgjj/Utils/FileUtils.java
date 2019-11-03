package com.lecheng.hello.fzgjj.Utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.lecheng.hello.fzgjj.Activity.H4.PushMessageActivity;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;
import java.net.URLConnection;

/**
 * Created by vange on 2017/9/15.
 */

public class FileUtils {

    /***根据文件后缀回去MIME类型****/

    public static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        return URLConnection.getFileNameMap().getContentTypeFor(file.toString());
    }

    public static boolean isDocFile(File file){
        String all="xlsx pptx html docx xml txt pdf";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return false;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex+1, fName.length()).toLowerCase();

        if(!TextUtils.isEmpty(end)&&all.contains(end)){
            return true;
        }
        return false;
    }
    public static void openBySystem(Context context,File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(context, intent, com.lecheng.hello.fzgjj.Utils.FileUtils.getMIMEType(file), file, true);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            new MyToast(context,"未找到打开该文档的应用,请下载手机版word",1);
        }
    }
    public static void openAssignFolder(Context context,String path){
        File file = new File(path);
        if(null==file || !file.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "file/*");
        try {
            context.startActivity(intent);
            context.startActivity(Intent.createChooser(intent,"选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void send(Context context,File file){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.addCategory(Intent.CATEGORY_DEFAULT);
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
        FileProvider7.setIntentData(context,share,file,true);
        share.setDataAndType(Uri.fromFile(file), "*/*");
        context.startActivity(Intent.createChooser(share, "分享到"));
    }
}
