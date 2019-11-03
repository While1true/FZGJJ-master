package com.lecheng.hello.fzgjj.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.lecheng.hello.fzgjj.R;

/**
 * Created by Cheng on 2016/8/15.
 */
public class MyToast {
    private static Toast toastStart;
    private TextView tvMsg;
    private ImageView ivIcon;

    public MyToast(Context c, String text, int duration) {
        try {


            //加载Toast布局
            View toastRoot = LayoutInflater.from(c).inflate(R.layout.unit_toast, null);
            //初始化布局控件
            tvMsg = (TextView) toastRoot.findViewById(R.id.tvMsg);
//        ivIcon = (ImageView) toastRoot.findViewById(R.id.ivIcon);
            //为控件设置属性
            tvMsg.setText("提示: " + text);
//        ivIcon.setImageResource(R.mipmap.ic_launcher);

            //Toast的初始化
            if (toastStart == null) {
                toastStart = new Toast(c);
                //获取屏幕高度
                WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
                int height = wm.getDefaultDisplay().getHeight();
                int width = wm.getDefaultDisplay().getWidth();
//            toastStart.setGravity(Gravity.TOP, 0, height * 4 / 5);
                toastStart.setGravity(Gravity.CENTER, 0, -height / 2);
                toastStart.setDuration(duration);
                toastStart.setView(toastRoot);
            } else {
                toastStart.setDuration(duration);
                toastStart.setView(toastRoot);
            }
            toastStart.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
