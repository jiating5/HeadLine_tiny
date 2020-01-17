package com.jt.basemodule.net.api;

import com.jt.basemodule.net.bean.TokenBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：请求Token
 */
public interface TokenApi {
    @POST("/token")
    @FormUrlEncoded
    Call<TokenBean> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
