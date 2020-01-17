package com.jt.usercentermodule.mvvm.repository;

import androidx.lifecycle.LiveData;

import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.basemodule.net.retrofit.RetrofitFactory;
import com.jt.usercentermodule.mvvm.api.UserApi;
import com.jt.usercentermodule.mvvm.view.bean.UserBean;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class UserService {
    public LiveData<BaseRespEntity<UserBean>> register(UserBean userBean){
        LiveData<BaseRespEntity<UserBean>> result = RetrofitFactory.getInstance().create(UserApi.class).register(userBean);
        return result;
    }

    public LiveData<BaseRespEntity<UserBean>> login(String uname,String pwd){
        LiveData<BaseRespEntity<UserBean>> result = RetrofitFactory.getInstance().create(UserApi.class).login(uname,pwd);
        return result;
    }
}
