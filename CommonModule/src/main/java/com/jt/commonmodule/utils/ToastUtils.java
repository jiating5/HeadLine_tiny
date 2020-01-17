package com.jt.commonmodule.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：吐司工具类
 */
public class ToastUtils {
    /**
     * 显示消息
     * @param context
     * @param msg
     */
    public static void showMsg(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示消息
     * @param msg
     */
    public static void showMsg(String msg){
        LogUtils.d(msg);
    }
}
