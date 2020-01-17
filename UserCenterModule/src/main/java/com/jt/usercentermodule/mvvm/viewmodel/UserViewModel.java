package com.jt.usercentermodule.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.usercentermodule.mvvm.repository.UserRepository;
import com.jt.usercentermodule.mvvm.view.bean.UserBean;

/**
 * @author 贾婷
 * @date 2020/1/15.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class UserViewModel extends ViewModel {
    UserRepository userRepository = null;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<BaseRespEntity<UserBean>> register(UserBean userBean){
        return userRepository.register(userBean);
    }

    public LiveData<BaseRespEntity<UserBean>> login(String uname,String pwd){
        return userRepository.login(uname,pwd);
    }
}
