package com.jt.commonmodule.utils;

import android.util.Log;
/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：log打印
 */
public class LogUtils {
    private final static String TAG="jiating";
    public static void i(String log){
        Log.i(TAG,log);
    }
    public static void d(String log){
        Log.d(TAG,log);
    }
    public static void w(String log){
        Log.w(TAG,log);
    }
    public static void e(String log){
        Log.e(TAG,log);
    }
}
