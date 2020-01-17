package com.jt.basemodule.net.retrofit;

import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (!(returnType instanceof ParameterizedType)){
            throw new IllegalArgumentException("返回值必须为参数化类型");
        }
        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);
        if (rawType!= LiveData.class && rawType!= Call.class){
            throw new IllegalArgumentException("返回值必须为LiveData|Call类型");
        }
        Type type = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        if (returnType == Call.class){
            return new DefaultCallAdapter<>(type);
        }
        return new LiveDataCallAdapter<>(type);
    }

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }
}
