package com.lecheng.hello.fzgjj.Utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import android.widget.PopupWindow;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.Recorder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.H4.SurveyDetailActivity;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Receiver.MiPushUtils;
import com.lecheng.hello.fzgjj.Widget.AdjustUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tencent.smtt.sdk.QbSdk;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePalApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import coms.kxjsj.refreshlayout_master.MyRefreshWrap;
import coms.kxjsj.refreshlayout_master.RefreshLayout;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;

/**
 * Created by Cheng on 2016/3/16.
 */
public class MyApplication extends LitePalApplication {
    public static RequestQueue que;
    public static final double verionCode = 1.0;//版本号码
    public static final String UPDATE_URL = "http://raocheng.party/gjj.xlsx";//更新APP的url
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        RefreshLayout.init(new RefreshLayout.DefaultBuilder()
                .setBaseRefreshWrap(MyRefreshWrap.class)
                .setHeaderLayoutidDefault(R.layout.header_layout)
                .setFooterLayoutidDefault(R.layout.footer_layout)
                .setScrollLayoutIdDefault(R.layout.recyclerview));
        SAdapter.init(new Recorder.Builder().setEmptyRes(R.layout.empty_textview).setErrorRes(R.layout.network_error).build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
//        AdjustUtils.Adjust(this, AdjustUtils.TYEP_PT);
//        AdjustUtil.adjust(this);
        MiPushUtils.init(this);
        //增加这句话
        QbSdk.initX5Environment(this, null);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "accept: " + throwable.getMessage());
            }
        });
    }

    public static String getUseId() {
        return MySP.loadData(app, "username", "").toString();
    }

    private void init() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());//异常捕获实例化

        que = Volley.newRequestQueue(getApplicationContext());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)//UniversalImageLoader
                .memoryCacheExtraOptions(480, 800)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .discCacheExtraOptions(480, 800, null)
//                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                // Can slow ImageLoader, use it carefully (Better don't use
                // it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // You can pass your own memory cache
                // implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                // 缓存的文件数量
                .discCache(new UnlimitedDiscCache(new File(Environment
                        .getExternalStorageDirectory()
                        + "/android/appimgCache")))
                // 自定义缓存路径
                .defaultDisplayImageOptions(getDisplayOptions())
                .imageDownloader(
                        new BaseImageDownloader(this, 5 * 1000, 30 * 1000))
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建

        ImageLoader.getInstance().init(config);

        com.blankj.utilcode.util.Utils.init(MyApplication.this);

    }

    private DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.colorTextGray1) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.color.colorTextGray1)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.color.colorTextGray1) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成
        return options;
    }

    public static RequestQueue getHttpQue() {
        return que;
    }


}
