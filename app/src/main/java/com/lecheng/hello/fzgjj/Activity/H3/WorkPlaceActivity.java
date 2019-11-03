package com.lecheng.hello.fzgjj.Activity.H3;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Loaction.LocationService;
import com.lecheng.hello.fzgjj.Loaction.RoutePlanDemo;
import com.lecheng.hello.fzgjj.Loaction.StepUtils;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/9/8.
 */

public class WorkPlaceActivity extends AppCompatActivity {
    LocationService locationService;
    private CoordType mCoordType;
    BDLocation locationa;
    private String permissionInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        mCoordType = SDKInitializer.getCoordType();//获取全局设置的坐标类型

        setContentView(R.layout.workplace_activity);
        ButterKnife.bind(this);
        getPersimmions();
        initLocation();
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 127);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initLocation() {
        locationService = new LocationService(getApplicationContext());
    }


    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListener = null;
        locationa = null;
        BaiduMapNavigation.finish(this);
        BaiduMapRoutePlan.finish(this);
        BaiduMapPoiSearch.finish(this);
        SDKInitializer.setCoordType(mCoordType);//设置为之前的坐标类型，保证此activity不对其他有影响。
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                locationa = location;
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onReceiveLocation: " + location.getAltitude() + "--" + location.getLatitude());
            }
        }
    };


    /**
     * 启动百度地图公交路线规划
     */
    public void startRoutePlanTransit() {
        if (locationa == null) {
            new MyToast(this, "正在获取定位信息，稍后再试", 0);
            return;
        }
        if(locationa.getLatitude()==0&&locationa.getLongitude()==0){
            new MyToast(this, "请在设置中开启定位权限，以获取坐标", 0);
            return;
        }
        //白湖亭坐标用于测试
        LatLng ptStart = new LatLng(locationa.getLatitude(), locationa.getLongitude());

        LatLng ptEnd = new LatLng(26.102658, 119.30289);

        // 构建 route搜索参数
        RouteParaOption para = new RouteParaOption()
                .startPoint(ptStart)
                .startName("我的位置")
                .endPoint(ptEnd)
                .endName("福建省直单位住房公积金管理中心")
                .busStrategyType(RouteParaOption.EBusStrategyType.bus_recommend_way);
        try {
            if (StepUtils.isAvilible(this, "com.baidu.BaiduMap"))
                BaiduMapRoutePlan.openBaiduMapTransitRoute(para, this);
            else {
                Intent intent = new Intent(this, RoutePlanDemo.class);
                intent.putExtra("position", ptStart);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showDialog();
        }

    }

    /**
     * 提示未安装百度地图app或app版本过低
     */
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                OpenClientUtil.getLatestBaiduMapApp(WorkPlaceActivity.this);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


    @OnClick({R.id.iv_back, R.id.imageView2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.imageView2:
                startRoutePlanTransit();
                break;
        }
    }
}
