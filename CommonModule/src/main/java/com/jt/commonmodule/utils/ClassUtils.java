package com.jt.commonmodule.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class ClassUtils {
    /**
     * 获取泛型类型
     * @param obj
     * @return
     */
    public static Class<?> getParameterizedClazz(Object obj){
        Type t= obj.getClass().getGenericSuperclass();
        ParameterizedType p=(ParameterizedType)t;
        Class c=(Class) p.getActualTypeArguments()[0];
        return c;
    }
}
