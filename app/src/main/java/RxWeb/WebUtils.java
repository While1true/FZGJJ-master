package RxWeb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.blankj.utilcode.util.Utils;
import com.lecheng.hello.fzgjj.AspectJ.CheckIfCanVisit;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.Api;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by vange on 2017/8/30.
 */

public class WebUtils {

    /**
     * String 结果
     *
     * @param methodName
     * @param maps
     * @return
     */
    public static Observable<String> doSoapNet(final String methodName, final LinkedHashMap<String, String> maps) {
        return Observable.create(new ObservableOnSubscribe<String>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<String> e) throws Exception {
                                         if (!isConnected(Utils.getApp())) {
                                             e.onError(new Throwable("网络错误"));
                                             return;
                                         }

                                         String result = dotNet(methodName, maps);
                                         Log.i("WebUtils", "subscribe: " + result);

                                         if(result!=null) {
                                             e.onNext(result);
                                         }else{
                                             e.onError(new Throwable("获取失败"));
                                         }

                                         e.onComplete();
                                     }
                                 }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }  /**
     * String 结果
     *
     * @param methodName
     * @param maps
     * @return
     */
    public static Observable<String> doSoapNet(final String url,final String Namespace,final String methodName, final LinkedHashMap<String, String> maps) {
        return Observable.create(new ObservableOnSubscribe<String>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<String> e) throws Exception {
                                         if (!isConnected(Utils.getApp())) {
                                             e.onError(new Throwable("网络错误"));
                                             return;
                                         }
                                         String result = dotNet(url,Namespace,methodName, maps);
                                         Log.i("WebUtils", "subscribe: " + result);

                                         e.onNext(result);

                                         e.onComplete();
                                     }
                                 }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * json 结果
     *
     * @param
     * @param methodName
     * @param maps
     * @param <T>
     * @return
     */
    public static <T> Observable<T> doSoapNet(final Type type, final String methodName, final LinkedHashMap<String, String> maps) {
        return Observable.create(new ObservableOnSubscribe<T>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<T> e) throws Exception {
                                         if (!isConnected(Utils.getApp())) {
                                             e.onError(new Throwable("网络错误"));
                                             return;
                                         }

                                         String result = dotNet(methodName, maps);
                                         Log.i("WebUtils", "subscribe: " + result);
                                         T t = GsonUtil.PaseJson(result, type);
                                         e.onNext(t);
                                         e.onComplete();
                                     }
                                 }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * json 结果
     *
     * @param clazz
     * @param methodName
     * @param maps
     * @param <T>
     * @return
     */
    public static <T> Observable<T> doSoapNet(final Class<T> clazz, final String methodName, final LinkedHashMap<String, String> maps) {
        return Observable.create(new ObservableOnSubscribe<T>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<T> e) throws Exception {
                                         if (!isConnected(Utils.getApp())) {
                                             e.onError(new Throwable("网络错误"));
                                             return;
                                         }

                                         String result = dotNet(methodName, maps);
                                         Log.i("WebUtils", "subscribe: " + result);
                                         if (clazz == String.class) {
                                             e.onNext((T) result);
                                         } else {
                                             T t = GsonUtil.GsonToBean(result, clazz);
                                             e.onNext(t);
                                         }
                                         e.onComplete();
                                     }
                                 }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * soap网络请求
     *
     * @param methodName
     * @param maps
     * @return
     * @throws Exception
     */
    private static String dotNet(String methodName, LinkedHashMap<String, String> maps) throws Exception {
       return dotNet(null,null,methodName,maps);
    }
    private static String dotNet(String url,String namespace,String methodName, LinkedHashMap<String, String> maps) throws Exception {
        String NAMESPACE = namespace==null?Api.NAMESPACE_:namespace;
        String URL = url==null?Api.URL:url;
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
        if (maps != null) {
            for (Map.Entry<String, String> objectEntry : maps.entrySet()) {
                soapObject.addProperty(objectEntry.getKey(), objectEntry.getValue());
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "dotNet: " + objectEntry.getKey() + "--" + objectEntry.getValue());
            }
        }

        String soapAction = NAMESPACE + "/" + methodName;// SOAP Action
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(URL, Api.TIME_OUT);
        ht.debug = false;
        envelope.setOutputSoapObject(soapObject);
        Log.i("TAAAG", "dotNet: " +URL+ soapAction.toString() + envelope.bodyOut);
        try {
            ht.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                return getString((SoapObject) envelope.bodyIn);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 结果解析，需要的内容
     *
     * @param s
     * @return
     * @throws Exception
     */
    @CheckIfCanVisit
    public static String getString(SoapObject s) throws Exception {//Soap数据解析方法
        if (Constance.DEBUGTAG)
            Log.i("WEBUtils", "getString: " + s);
        SoapObject s2 = (SoapObject) s.getProperty(0);
        SoapObject s3 = (SoapObject) s2.getProperty(0);
        String s4 = s3.getProperty(1) + "";

        return s4;
    }


    /**
     * 是否有网络
     *
     * @param context
     * @return
     */
    private static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo != null) {
            return activeInfo.isConnected();
        }
        return false;
    }















    private static String dotNet2(String url,String namespace,String methodName, LinkedHashMap<String, String> maps) throws Exception {
        String NAMESPACE = namespace==null?Api.NAMESPACE_:namespace;
        String URL = url==null?Api.URL:url;
        SoapObject soapObject = new SoapObject(NAMESPACE, methodName);
        if (maps != null) {
            for (Map.Entry<String, String> objectEntry : maps.entrySet()) {
                soapObject.addProperty(objectEntry.getKey(), objectEntry.getValue());
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "dotNet: " + objectEntry.getKey() + "--" + objectEntry.getValue());
            }
        }

        String soapAction = NAMESPACE + "/" + methodName;// SOAP Action
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(URL, Api.TIME_OUT);
        ht.debug = false;
        envelope.setOutputSoapObject(soapObject);
        Log.i("TAAAG", "dotNet: " +URL+ soapAction.toString() + envelope.bodyOut);
        try {
            ht.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                return ((SoapObject) envelope.bodyIn).getProperty(0).toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Observable<String> doSoapNet2(final String url,final String Namespace,final String methodName, final LinkedHashMap<String, String> maps) {
        return Observable.create(new ObservableOnSubscribe<String>() {
                                     @Override
                                     public void subscribe(ObservableEmitter<String> e) throws Exception {
                                         if (!isConnected(Utils.getApp())) {
                                             e.onError(new Throwable("网络错误"));
                                             return;
                                         }
                                         String result = dotNet2(url,Namespace,methodName, maps);
                                         Log.i("WebUtils", "subscribe: " + result);

                                         e.onNext(result);

                                         e.onComplete();
                                     }
                                 }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
