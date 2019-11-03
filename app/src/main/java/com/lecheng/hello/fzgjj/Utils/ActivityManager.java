package com.lecheng.hello.fzgjj.Utils;

import android.app.Activity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vange on 2017/9/5.
 */

public class ActivityManager {
    LinkedHashMap<String, Activity> activityLinkedHashMap;

    private ActivityManager() {
        activityLinkedHashMap = new LinkedHashMap<>(3);
    }

    public static ActivityManager get() {
        return MangerHolder.manager;

    }

    public ActivityManager add(String tag, Activity activity) {
        activityLinkedHashMap.put(tag, activity);
        return this;
    }

    public ActivityManager add(Activity activity) {
        activityLinkedHashMap.put(activityLinkedHashMap.size() + "", activity);
        return this;
    }

    public ActivityManager remove(String tag) {
        if (activityLinkedHashMap.containsKey(tag))
            activityLinkedHashMap.remove(tag);
        return this;
    }

    public ActivityManager remove() {
        if (activityLinkedHashMap.size() > 0 && activityLinkedHashMap.containsKey(activityLinkedHashMap.size() + ""))
            activityLinkedHashMap.remove(activityLinkedHashMap.size() + "");
        return this;
    }

    public ActivityManager removeAll(){
        for (Map.Entry<String, Activity> activityEntry : activityLinkedHashMap.entrySet()) {
            Activity activity = activityEntry.getValue();
            if(activityEntry!=null&&activity!=null)
                activity.finish();
        }
        activityLinkedHashMap.clear();
        return this;
    }

    private static class MangerHolder {
        private static final ActivityManager manager = new ActivityManager();
    }
}
