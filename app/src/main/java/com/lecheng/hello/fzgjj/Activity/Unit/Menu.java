package com.lecheng.hello.fzgjj.Activity.Unit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanWzcdList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import RxWeb.GsonUtil;

/**
 * http://www.cnblogs.com/bky1225987336/p/6008538.html
 */
public class Menu extends PopupWindow implements IWSListener {
    private View root;
    private ListView lv1;
    private Context context;
    private int i = 0;
    private UnityAdapter<BeanWzcdList.DataBean> adpt;
    private BeanWzcdList bean;

    public Menu(Activity context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.unit_menu, null);
        lv1 = (ListView) root.findViewById(R.id.lv1);
        i = 0;
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(root);        // 设置SelectPicPopupWindow的View
//        this.setWidth(w / 3 * 2 - 30);        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);// 设置SelectPicPopupWindow弹出窗体的高
        this.setFocusable(true);        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setOutsideTouchable(true);
        this.update();        // 刷新状态
        ColorDrawable dw = new ColorDrawable(0000000000);//实例化一个ColorDrawable颜色为半透明
        this.setBackgroundDrawable(dw);//点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener.设置其他控件变化等操作
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);//设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimationPreview);
        httpPost(context, "", "1");//发起网络请求
    }

    private void httpPost(Context c, String code, String lv) {
        String[] key = {"code", "levels"};
        String[] value = {code, lv};
        new HttpGo().httpWebService(c, this, "wzcdList", key, value);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            final BeanWzcdList bean = GsonUtil.GsonToBean(s, BeanWzcdList.class);
            this.bean = bean;
            lv1.setAdapter(new UnityAdapter<BeanWzcdList.DataBean>(context, bean.getData(), R.layout.item0a) {
                @Override
                public void convert(ViewHolder helper, BeanWzcdList.DataBean item, int position) {
                    helper.setText(R.id.tv1, item.getTitle());
                }
            });
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (i > 1) {
                        dismiss();
                        context.startActivity(new Intent(context, InfoList.class)
                                .putExtra("gjz", bean.getData().get(position).getCode())
                                .putExtra("gjz2", bean.getData().get(position).getTitle()));
                    } else
                        httpPost(context, bean.getData().get(position).getCode(), "2");//发起网络请求
                }
            });
            i++;
        } catch (Exception e) {
            new MyToast(context, "加载失败,请重试!", 1);
        }
    }

    public void showPopupWindow(View parent) {//显示popupWindow
        if (!this.isShowing())
            this.showAsDropDown(parent);// 以下拉方式显示popupwindow
        else
            this.dismiss();
    }
}