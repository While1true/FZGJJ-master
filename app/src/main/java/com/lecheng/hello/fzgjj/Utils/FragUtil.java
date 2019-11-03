package com.lecheng.hello.fzgjj.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.lecheng.hello.fzgjj.Activity.Unit.Login;
import com.lecheng.hello.fzgjj.R;

/**
 * Created by 乐城 on 2017/4/7.
 */

public class FragUtil {

    public static void startFragmentFullScreen(FragmentActivity activity,Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)  //将当前fragment加入到返回栈中
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)//设置动画效果
                .replace(android.R.id.content, fragment)
                .commit();
    }

//    private static void start(Fragment fragment) {
//        MainActivity.instance
//                .getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                .setCustomAnimations(R.anim.bottom_open, R.anim.alpha_gone,
//                        R.anim.alpha_visible, R.anim.bottom_close)
//                .replace(R.id.frag, fragment)
//                .commit();
//    }

//    public static void startLoginFrag() {
//        if (Login.getInstance().isGetInstance == false) {
//            startFragment(Login.getInstance());
//            Login.getInstance().isGetInstance = true;
//        } else {
//            startFragmentNotAddToBackStack(Login.getInstance());
//        }
//    }

//    public static void startFragmentNotAddToBackStack(Fragment fragment) {
//        MainActivity.instance
//                .getSupportFragmentManager()
//                .beginTransaction()
////                .addToBackStack(null)  //将当前fragment加入到返回栈中
//                .setCustomAnimations(R.anim.bottom_open, R.anim.alpha_gone,
//                        R.anim.alpha_visible, R.anim.bottom_close)
//                .replace(R.id.frag, fragment)
//                .commit();
//    }

}
