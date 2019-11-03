package com.lecheng.hello.fzgjj.Interface;

import android.content.Context;

import java.util.HashMap;

public interface I047Model {
    void http047Get(Context c, String url, I047Listener listener);

    void http047Post(Context c, String url, HashMap<String, String> hs, I047Listener l);

    void http047Cancel(String tag);

//    void httpWebService(Context c, IWSListener l, String mn,
//                        String[] key, String[] value);
}