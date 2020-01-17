package com.jt.basemodule.net.retrofit;

import android.text.TextUtils;

import com.jt.basemodule.net.api.TokenApi;
import com.jt.basemodule.net.bean.TokenBean;
import com.jt.commonmodule.common.Config;
import com.jt.commonmodule.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：网络请求
 */

public class RetrofitFactory {

    private static volatile RetrofitFactory singleton;
    private Retrofit retrofit;

    private RetrofitFactory() {
        //初始化Retrofit
        initRetrofit();
    }

    public static RetrofitFactory getInstance() {
        if (singleton == null) {
            synchronized (RetrofitFactory.class) {
                if (singleton == null) {
                    singleton = new RetrofitFactory();
                }
            }
        }
        return singleton;
    }

    //初始化Retrofit
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Config.SERVER_URL)
                .client(createOkHttp())
                .build();
    }

    private OkHttpClient createOkHttp() {
        return  new OkHttpClient().newBuilder()
                .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(createLogInterceptor())
                .addInterceptor(createRequestHeaderInterceptor())
                .addInterceptor(createRequestInterceptor())
                .readTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 拦截器请求Token
     * @return
     */
    private Interceptor createRequestInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)){
                    String token=requestToken();
                    if (TextUtils.isEmpty(token)){
                        LogUtils.e("Error : token is null...");
                    }
                    //Request newRequest=chain.request();
                    Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);

                    Request newRequest=newBuilder.build();
                    return chain.proceed(newRequest);
                }
                return response;
            }
        };

        return interceptor;
    }

    /**
     * 获取Token的同步网络请求
     * @return
     */
    private String requestToken() {
        TokenApi tokenApi = create(TokenApi.class);
        try {
            Call<TokenBean> tokenService = tokenApi.getToken("password", Config.AUTHCODE, "");
            retrofit2.Response<TokenBean> result = tokenService.execute();
            if (result!=null&&result.body()!=null){
                return  result.body().getAccess_token();
            }
        } catch (IOException e) {
            LogUtils.e(e.getMessage());
        }
        return "";
    }

    /**
     * 判断HTTP CODE 是否401 —— TOKEN失效
     * @param response
     * @return
     */
    private boolean checkHttpCode401(Response response) {
        if (null==response){
            return false;
        }

        if (response.code()==401){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 创建请求拦截器
     * @return
     */
    private Interceptor createRequestHeaderInterceptor() {
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder().addHeader("Content-Type", "application-json")
                        .addHeader("charset", "utf-8").build();
//                        .addHeader("manufacturer", DeviceInfoConfig.getInstance().getMANUFACTURER())
//                        .addHeader("model", DeviceInfoConfig.getInstance().getModel())
//                        .addHeader("deviceid", DeviceInfoConfig.getInstance().getDeviceID())
//                        .addHeader("utdid", DeviceInfoConfig.getInstance().getUtdid())
//                        .addHeader("packagename", AppInfoConfig.getInstance().getPackageName())
//                        .addHeader("versioncode", AppInfoConfig.getInstance().getVersionCode())
//                        .addHeader("versionname", AppInfoConfig.getInstance().getVersionName())
//                        .addHeader("location", DeviceInfoConfig.getInstance().getLocation())
//                        .addHeader("macaddress",DeviceInfoConfig.getInstance().getMacAddress())
//                        .addHeader("display",DeviceInfoConfig.getInstance().getDisplay())
//                        .addHeader("osversion",DeviceInfoConfig.getInstance().getOSVersion()).build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    /**
     * 创建日志拦截器
     * @return
     */
    private Interceptor createLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * 添加token头信息
     * @param token
     */
    public void addTokenInterceptor(String token){
        new OkHttpClient().newBuilder()
                .addInterceptor(createTokenInterceptor(token));
    }

    /**
     * 创建Token拦截器
     * @param token
     * @return
     */
    private Interceptor createTokenInterceptor(final String token) {
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request.newBuilder().addHeader("Authorization","bearer "+"421971f414b11319515213512415b12419713919d17a1931");
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    /**
     * 创建服务器请求
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}