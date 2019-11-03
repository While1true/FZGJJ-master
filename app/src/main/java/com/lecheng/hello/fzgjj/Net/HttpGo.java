package com.lecheng.hello.fzgjj.Net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.I047Listener;
import com.lecheng.hello.fzgjj.Interface.I047Model;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Utils.MyToast;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
import java.util.Map;

import static com.lecheng.hello.fzgjj.Utils.MyApplication.getHttpQue;

/*
    http://120.77.63.172/gjjAndroid/services/GjjController?wsdl
    http://10.0.110.134:8080/gjjAndroid/services/GjjController?wsdl
    http://10.0.110.134:8080/TestgjjSample/sampleGjjControllerProxy/TestClient.jsp
    封装所有http请求2017-3-15
*/
public class HttpGo implements I047Model {
    //webservice 请求
    public void httpWebService(final Context c, final IWSListener lis, final String methodName,
                               final String[] key, final String[] value) {
        new AsyncTask<Void, Void, SoapObject>() {
            @Override
            protected SoapObject doInBackground(Void... params) {
                try {
                    return myMethod(methodName, key, value);
                } catch (Exception e) {
                    Log.i("HttpGO", "doInBackground: ");
                    new MyToast(c, "服务器未响应(CODE:500)", 1);//调试信息
                    return null;
                }
            }

            @Override
            protected void onPostExecute(SoapObject soapObject) {
                super.onPostExecute(soapObject);
                System.out.println("返回：\n" + soapObject);
//                new MyToast(c,"返回：" + soapObject,1);//调试信息
                lis.onWebServiceSuccess(soapToStr(soapObject, c));//调用数据解析方法
            }
        }.execute();
    }

    public String soapToStr(SoapObject s, Context c) {//Soap数据解析方法

        try {
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "soapToStr: "+s.toString());
            SoapObject s2 = (SoapObject) s.getProperty(0);
            SoapObject s3 = (SoapObject) s2.getProperty(0);
            String s4 = s3.getProperty(1) + "";
//            System.out.println("s4:\n" + s4);
            return s4;
//            String s2 = (String) s.getProperty(0);
//            return s2;
        } catch (Exception e) {
            new MyToast(c, "数据解析异常(E.SOAP)", 1);//调试信息
            e.printStackTrace();
        }
        return "";
    }

    //第一个
    private SoapObject myMethod(final String methodName, String[] key, String[] value) throws Exception {
        String NAMESPACE = Api.NAMESPACE_;
//        String URL = "http://10.0.110.134:8080/gjjAndroid/services/GjjController?wsdl";//我自己的WebService
//        String URL = "http://120.77.63.172:80/gjjAndroid/services/GjjController?wsdl";//教授的WebService
        String URL = Api.URL;//教授的WebService
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "myMethod: "+methodName);
        for (int i = 0; i < key.length; i++) {
            soapObject.addProperty(key[i], value[i]);
            Log.d("webService: ", key[i] + " : " + value[i]);
        }
        String soapAction = NAMESPACE + "/" + methodName;// SOAP Action
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.debug = true;
        envelope.setOutputSoapObject(soapObject);
        ht.call(soapAction, envelope);

//        Log.d("vivi", envelope.bodyIn.toString() + " hahah");
        if (envelope.getResponse() != null) {
            return (SoapObject) envelope.bodyIn;
        } else {
            return null;
        }
    }

    @Override     //Get请求
    public void http047Get(final Context c, String url, final I047Listener listener) {
        StringRequest request = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onSuccess(s);
                        System.out.println("Aty047-onResponse");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        listener.onError();
//                        new MyToast(c, "请求失败:\n" + e, 3000);
                    }
                });
        request.setTag("cancelGet");
        getHttpQue().add(request);
    }

    @Override               //Post请求
    public void http047Post(final Context c, String url, final HashMap<String, String> hashMap, final I047Listener listener) {
        StringRequest request2 = new StringRequest
                (Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listener.onSuccess(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
//                        new MyToast(c, "请求失败:\n" + e, 3000);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = hashMap;
                return map;
            }
        };
        request2.setTag("cancelPost");
        getHttpQue().add(request2);
    }

    @Override               //取消队列
    public void http047Cancel(String tag) {
        getHttpQue().cancelAll(tag);
    }

}