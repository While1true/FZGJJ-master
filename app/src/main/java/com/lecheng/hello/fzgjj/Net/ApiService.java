package com.lecheng.hello.fzgjj.Net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.lecheng.hello.fzgjj.Activity.H2.YuYueBean;
import com.lecheng.hello.fzgjj.Bean.Access;
import com.lecheng.hello.fzgjj.Bean.AccountBean;
import com.lecheng.hello.fzgjj.Bean.CalculateBean;
import com.lecheng.hello.fzgjj.Bean.SurveyCategory;
import com.lecheng.hello.fzgjj.Bean.SurveySubBean;
import com.lecheng.hello.fzgjj.Bean.TQInfo;
import com.lecheng.hello.fzgjj.Bean.TypeBean;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Bean.WtBean;
import com.lecheng.hello.fzgjj.Bean.YYCode;
import com.lecheng.hello.fzgjj.Bean.YYResult;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Utils.IpUtils;
import com.lecheng.hello.fzgjj.Utils.MyApplication;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.RequestParams;


import org.litepal.crud.DataSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import RxWeb.BaseBean;
import RxWeb.MyObserve;
import RxWeb.WebUtils;

/**
 * Created by vange on 2017/9/12.
 */

public class ApiService {


    /**
     * 1.传入的MyObserve构造要传入tag 可以是Basefragment 可以是Baseactivity 这样distroy会取消订阅
     * .
     */


    /**
     * String method
     */
    public static void Simple1(RequestParams params, MyObserve<String> observe) {
        String method = "";
        WebUtils.doSoapNet(method, params).subscribe(observe);
    }

    /**
     * String method
     */
    public static void Simple2(RequestParams params, MyObserve<BaseBean> observe) {
        String method = "";
        WebUtils.doSoapNet(BaseBean.class, method, params).subscribe(observe);
    }


    /**
     * 计算贷款额度
     *
     * @param params
     * @param observe
     */
    public static void CalCulateDeposite(RequestParams params, MyObserve<String> observe) {
        WebUtils.doSoapNet(Api.CALCULATE_DKED, params).subscribe(observe);
    }

    /**
     * 计算还款
     *
     * @param params
     * @param observe
     */
    public static void CalculateRePay(RequestParams params, MyObserve<CalculateBean> observe) {

        WebUtils.doSoapNet(CalculateBean.class, Api.CALCULATE_DKHK, params).subscribe(observe);
    }


    /**
     * 查询个人资料
     *
     * @param params
     * @param observe
     */
    public static void QueryAccount(RequestParams params, MyObserve observe) {
        Type type = new TypeToken<List<AccountBean>>() {
        }.getType();
        WebUtils.doSoapNet(type, Api.QUIRY_ACCOUNT, params).subscribe(observe);
    }

    /**
     * 疑难解答
     *
     * @param params
     * @param observe
     */
    public static void resolveProblem(RequestParams params, MyObserve<String> observe) {

        WebUtils.doSoapNet(Api.HANDLE_PROMBLEM, params).subscribe(observe);
    }

    /**
     * 在线调查目录获取
     *
     * @param observe
     */
    public static void getSurveyList(MyObserve observe) {
        Type type = new TypeToken<List<SurveyCategory>>() {
        }.getType();
        RequestParams params = new RequestParams();
        params.put("user_id", MyApplication.getUseId());
        WebUtils.doSoapNet(type, Api.ZXDC, params).subscribe(observe);
    }

    /**
     * 在线调查目录获取
     *
     * @param observe
     */
    public static void getSurveySubList(String id, MyObserve observe) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        Type type = new TypeToken<List<SurveySubBean>>() {
        }.getType();
        WebUtils.doSoapNet(type, Api.ZXDC_SUB, params).subscribe(observe);
    }

    /**
     * 在线调查目录提交
     *
     * @param observe
     */
    public static void getSurveySubmit(RequestParams params, MyObserve observe) {
        WebUtils.doSoapNet(Api.ZXDC_SUBMIT, params).subscribe(observe);
    }

    /**
     * 绑定小米推送
     * @param params
     * @param observe
     */
    public static void bindChannelID(RequestParams params, MyObserve observe) {
        WebUtils.doSoapNet(Api.BIND_CHANNEL_ID, params).subscribe(observe);
    }

    /**
     * 提取类型
     * @param
     * @param observe
     */
    public static void getTQTYPE( MyObserve observe) {
        Type type = new TypeToken<List<TypeBean>>() {
        }.getType();
        WebUtils.doSoapNet(type,Api.TQ_TYPE, null).subscribe(observe);
    }

    /**
     * 在线提取提示信息获取
     *
     * @param observe
     */
    public static void getTQInfo(RequestParams params, MyObserve observe) {
        Type type = new TypeToken<List<TQInfo>>() {
        }.getType();
        WebUtils.doSoapNet(type,Api.GET_TQ_INFO, params).subscribe(observe);
    }

    /**
     * 预约提取
     */
    public static void YYCommit(RequestParams params, MyObserve observe) {
        WebUtils.doSoapNet(YYResult.class,Api.YYCOMMIT, params).subscribe(observe);
    }

    /**
     * 上传ip
     * UPLOADIP
     */
    public  static  void uploadIp(Context context, MyObserve observe){

        String ipAddress = IpUtils.getIPAddress(context);
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--"  + "--", "uploadIp: "+ipAddress);
        WebUtils.doSoapNet(Api.UPLOADIP,new RequestParams().add("addrIP", ipAddress))
                .subscribe(observe);
    }

    /**
     * 获取民意征集目录
     * @param observe
     */
    public  static  void getMyzjList(MyObserve observe){
        Type type = new TypeToken<List<SurveyCategory>>() {
        }.getType();
        WebUtils.doSoapNet(type, Api.MYZJLIST, null).subscribe(observe);
    }

    /**
     * 提交民意征集
     * @param json
     * @param observe
     */
    public static void myzjCommitList(String json,MyObserve observe){
        WebUtils.doSoapNet(Api.MYZJCOMMIT,new RequestParams().add("jsonstr",json)).subscribe(observe);
    }

    /**
     * 取消
     * @param observe
     */
    public static void YYCancel(RequestParams params,MyObserve observe){
        WebUtils.doSoapNet(Api.YYCANCEL,params).subscribe(observe);
    }
    /**
     * 发送短信
     * @param observe
     */
    public static void sendMessage(String mobile,String dxnr,MyObserve observe){
        WebUtils.doSoapNet(Api.URL,Api.NAMESPACE_,Api.SENDMESSAGE,new RequestParams().add("mobile",mobile).add("dxnr",dxnr)).subscribe(observe);
    }

    public static void YYList(RequestParams params,MyObserve observe){
        WebUtils.doSoapNet(YuYueBean.class,Api.YYLIST,params).subscribe(observe);
    }

    /**
     *
     * @param observe
     */
    public static void quiryCredit(MyObserve observe){
        RequestParams params=new RequestParams().add("gjjaccount", MySP.loadData(MyApplication.app, "username", "") + "");
        WebUtils.doSoapNet(Api.DKJDCX,params).subscribe(observe);
    }
    public static void accessControl(MyObserve observe){
        WebUtils.doSoapNet(Access.class,Api.accessControl,null).subscribe(observe);
    }
    /**
     *
     * @param observe
     */
    public static void yyQuery(MyObserve observe){
        RequestParams params=new RequestParams().add("gjjaccount", MySP.loadData(MyApplication.app, "username", "") + "");
        WebUtils.doSoapNet(YYCode.class,Api.YYQUERY,params).subscribe(observe);
    }

    /**
     *
     * @param observe
     */
    public static void ZHMM(MyObserve observe,String input){
        RequestParams params=new RequestParams().add("identity", input);
        WebUtils.doSoapNet(Access.class,Api.ZHMI,params).subscribe(observe);
    }

    /**
     *
     * @param observe
     */
    public static void WJMM(MyObserve observe, RequestParams params){
        WebUtils.doSoapNet(Access.class,Api.UPDATEPWD,params).subscribe(observe);
    }
    /**
     *网厅办理情况
     * @param observe
     */
    public static void WTBL(MyObserve observe){
        Type type = new TypeToken<List<WtBean>>() {
        }.getType();
        WebUtils.doSoapNet(type,Api.WTBLQK,null).subscribe(observe);
    }

    /**
     *
     * @param observe
     */
    public static void checkVersion(MyObserve observe){
        Type type=new TypeToken<BaseBean<UpdateBean>>(){}.getType();

        WebUtils.doSoapNet(type,Api.CHECK_VERSION,null).subscribe(observe);
    }


    public static void load(Context context, final MyCallback<AccountBean> callback){
        AccountBean first = DataSupport.findFirst(AccountBean.class);
        String username = MySP.loadData(context, "username", "") + "";
        if(first!=null&&callback!=null&&first.getGjjaccount().equals(username)){
            callback.call(first);
            return;
        }

        if (!TextUtils.isEmpty(username)) {
            loadOther(context,username,callback);
        }
    }

    public static void loadOther(Context context,String username, final MyCallback<AccountBean> callback){
        ApiService.QueryAccount(new RequestParams()
                .add("gjjaccount",username), new MyObserve<List<AccountBean>>() {
            @Override
            public void onNext(List<AccountBean> bean) {
                AccountBean value = bean.get(0);
                if (value != null) {
                    value.save();
                }
                if(callback!=null){
                    callback.call(value);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("aa", "onError: ", e);
            }
        });


    }
}
