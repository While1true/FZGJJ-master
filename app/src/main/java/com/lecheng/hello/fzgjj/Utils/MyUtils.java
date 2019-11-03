package com.lecheng.hello.fzgjj.Utils;

/**
 * Created by 乐城 on 2017/3/24.
 */

public class MyUtils {
    public static String r(String str) {
        if (str.equals("0"))
            return "未审核";
        if (str.equals("1"))
            return "未通过";
        if (str.equals("2"))
            return "已通过";
        return "null";
    }

    public static String c(String str) {
        return str.equals("0") ? "未完成" : "已完成";
    }

    public static String company(String str) {
        return str.equals("1") ? "网格" : "国民";
    }

    public static String s(String str) {
        if (str.equals("Y"))
            return "已启用";
        if (str.equals("N"))
            return "已禁用";
        if (str.equals("Z"))
            return "暂停使用";
        return "null";
    }
}
