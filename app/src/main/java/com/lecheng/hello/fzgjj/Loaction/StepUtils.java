package com.lecheng.hello.fzgjj.Loaction;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;

import com.baidu.mapapi.search.core.VehicleInfo;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vange on 2017/9/11.
 */

public class StepUtils {
    /**
     * 获取车次
     * @param transitRouteLine
     * @return
     */
    public static String getBusStepTitle(TransitRouteLine transitRouteLine){
        String title="";
        for (TransitRouteLine.TransitStep transitStep : transitRouteLine.getAllStep()) {
            VehicleInfo vehicleInfo = transitStep.getVehicleInfo();
            String instructions = transitStep.getInstructions();
            TransitRouteLine.TransitStep.TransitRouteStepType stepType = transitStep.getStepType();
            if(stepType.equals(TransitRouteLine.TransitStep.TransitRouteStepType.BUSLINE)
                    ||stepType.equals(TransitRouteLine.TransitStep.TransitRouteStepType.SUBWAY)){
                if(vehicleInfo!=null)
                    title+="转"+vehicleInfo.getTitle()+"经过"+vehicleInfo.getPassStationNum()+"个站点\n";
            }
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "onGetTransitRouteResult: "+"vehicleInfo: "+stepType+(vehicleInfo!=null?(vehicleInfo.getTitle()):"")+"instructions: "+instructions);
        }
        return title.substring(1,title.length()-1);
    }

    /**
     * 获取详细地址
     * @param transitRouteLine
     * @return
     */
    public static CharSequence getBusStepInfo(Context context,TransitRouteLine transitRouteLine){
        SpannableStringBuilder builder=new SpannableStringBuilder();
        SpannableString string=new SpannableString("根据您选择的路线如下：");
        string.setSpan(new TextAppearanceSpan(context, R.style.Color535353),0,string.length(),SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        builder.append(string);
        builder.append("\n");
        for (TransitRouteLine.TransitStep transitStep : transitRouteLine.getAllStep()) {
            VehicleInfo vehicleInfo = transitStep.getVehicleInfo();
            String instructions = transitStep.getInstructions();
            TransitRouteLine.TransitStep.TransitRouteStepType stepType = transitStep.getStepType();
            if(vehicleInfo!=null)
                builder.append(vehicleInfo.getTitle()+"/");
            builder.append(instructions);
            builder.append("\n");
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "onGetTransitRouteResult: "+"vehicleInfo: "+stepType+(vehicleInfo!=null?(vehicleInfo.getTitle()):"")+"instructions: "+instructions);
        }
        return builder.subSequence(0,builder.length()-1);
    }

    /**
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
