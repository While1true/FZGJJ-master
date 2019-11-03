package com.lecheng.hello.fzgjj.Utils;

import com.lecheng.hello.fzgjj.Activity.H2.YuYueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vange on 2017/9/18.
 */

public class TestUtils {

    public static List<String> provideList(int size){
        List<String> list=new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add("这是第"+i+"项");
        }

        return list;
    }
    public static List<YuYueBean.DataBean> provide(int size){
        List<YuYueBean.DataBean> list=new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            YuYueBean.DataBean bean=new YuYueBean.DataBean();
            bean.setNumber(i+"");
            bean.setShowtime("12:15");
            list.add(bean);
        }

        return list;
    }
}
