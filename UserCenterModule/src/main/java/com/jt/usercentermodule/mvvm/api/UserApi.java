package com.jt.usercentermodule.mvvm.api;

import androidx.lifecycle.LiveData;

import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.usercentermodule.mvvm.view.bean.UserBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：用户中心API
 */
public interface UserApi {
    /**
     * 注册用户方法
     * @param userBean
     * @return
     */
    @POST("api/User/register")
    LiveData<BaseRespEntity<UserBean>> register(@Body UserBean userBean);

    /**
     * 用户登录方法
     * @param
     * @return
     */
    @POST("api/User/login")
    LiveData<BaseRespEntity<UserBean>> login(@Query("username")String uname,@Query("pwd") String pwd);
}
