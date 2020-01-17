package com.jt.usercentermodule.mvvm.repository;

import androidx.lifecycle.LiveData;

import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.usercentermodule.mvvm.view.bean.UserBean;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：用户仓库
 */
public class UserRepository {
    UserService userService;

    public UserRepository() {
        userService = new UserService();
    }

    public LiveData<BaseRespEntity<UserBean>> register(UserBean userBean){
        return userService.register(userBean);
    }
    public LiveData<BaseRespEntity<UserBean>> login(String uname,String pwd){
        return userService.login(uname,pwd);
    }
}
