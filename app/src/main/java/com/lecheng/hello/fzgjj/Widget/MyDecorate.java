package com.lecheng.hello.fzgjj.Widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vange on 2017/9/18.
 */

public class MyDecorate extends RecyclerView.ItemDecoration {
    int color,height;
    Paint paint;
    public MyDecorate(int color,int height){
        this.color=color;
        this.height=height;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            int bottom = childAt.getBottom();
            int left = childAt.getLeft();
            int right = childAt.getRight();
            c.drawRect(left,bottom,right,bottom+height,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom=height;
    }
}
