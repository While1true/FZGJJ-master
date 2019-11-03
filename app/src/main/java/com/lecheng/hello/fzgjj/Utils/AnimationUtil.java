package com.lecheng.hello.fzgjj.Utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.lecheng.hello.fzgjj.R;


public class AnimationUtil {
    private static final String TAG = AnimationUtil.class.getSimpleName();

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }


    public static void visibleAlphaAnim(Context c, View v) {
        if (v.getVisibility() != View.VISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(c, R.anim.alpha_visible));
            v.setVisibility(View.VISIBLE);
        }
    }

    public static void invisibleAlphaAnim(Context c, View v) {
        if (v.getVisibility() != View.INVISIBLE) {
            v.startAnimation(AnimationUtils.loadAnimation(c, R.anim.alpha_gone));
            v.setVisibility(View.INVISIBLE);
        }
    }

    /*
        liebiao列表删除动画。
        http://blog.csdn.net/lonely_fireworks/article/details/25777497
    * */
    public static void deleteCell(final View v, final int index) {
        Animation.AnimationListener al = new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
//                mAnimList.remove(index);
//                ViewHolder vh = (ViewHolder)v.getTag();
//                vh.needInflate = true;
//                mMyAnimListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        };
        collapse(v, al);
    }

    public static void collapse(final View view, Animation.AnimationListener al) {
        final int originHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1.0f) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = originHeight - (int) (originHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (al != null) {
            animation.setAnimationListener(al);
        }
        animation.setDuration(800);
        view.startAnimation(animation);
    }
}