package com.jt.usercentermodule.mvvm.view.activity;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jt.basemodule.base.BaseActivity;
import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.commonmodule.utils.ToastUtils;
import com.jt.usercentermodule.R;
import com.jt.usercentermodule.mvvm.view.bean.UserBean;
import com.jt.usercentermodule.mvvm.viewmodel.UserViewModel;

public class RegisterActivity extends BaseActivity<UserViewModel, ViewDataBinding> {

    LiveData<BaseRespEntity<UserBean>> register;

    private EditText registerEtPhonenumber;
    private EditText registerEtPwd2;
    private EditText registerEtAuthcode;
    private EditText registerEtPwd;
    private Button registerBtnGetauthcode;
    private Button registerBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvent() {
        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = registerEtPhonenumber.getText().toString();
                String authcode = registerEtAuthcode.getText().toString();
                String pwd = registerEtPwd.getText().toString();
                String pwd2 = registerEtPwd2.getText().toString();
                LiveData<BaseRespEntity<UserBean>> register = mViewModel.register(new UserBean(0, authcode, pwd2, "", ""));
                register.observe(RegisterActivity.this, new Observer<BaseRespEntity<UserBean>>() {
                    @Override
                    public void onChanged(BaseRespEntity<UserBean> userBeanBaseRespEntity) {
                            Log.d("123", "onChanged: " + userBeanBaseRespEntity.toString());
                        if (userBeanBaseRespEntity.getCode()!=200){
                            ToastUtils.showMsg(RegisterActivity.this,"创建失败"+userBeanBaseRespEntity.getMsg());
                            return;
                        }
                        ToastUtils.showMsg(RegisterActivity.this,"创建成功");
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        registerEtPhonenumber = (EditText) findViewById(R.id.register_et_phonenumber);
        registerBtnGetauthcode = (Button) findViewById(R.id.register_btn_getauthcode);
        registerEtPwd = (EditText) findViewById(R.id.register_et_pwd);
        registerEtPwd2 = (EditText) findViewById(R.id.register_et_pwd2);
        registerBtnRegister = (Button) findViewById(R.id.register_btn_register);
        registerEtAuthcode = (EditText) findViewById(R.id.register_et_authcode);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
