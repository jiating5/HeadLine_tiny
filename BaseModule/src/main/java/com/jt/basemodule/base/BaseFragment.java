package com.bawei6.basemodel.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.bawei6.basemodel.R;
import com.bawei6.basemodel.utils.ClassUtils;



public abstract class BaseFragment<VM extends ViewModel,D extends ViewDataBinding> extends Fragment {

    protected VM mViewModel;
    protected D mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = inflater.inflate(getLayoutId(), null);
        //获取ViewModel的泛型类型
        initView(view);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Class<VM> clazz= (Class<VM>) ClassUtils.getParameterizedClazz(this);
        //ViewModel实例化
        mViewModel= ViewModelProviders.of(this).get(clazz);
        initData();
        initEvent();
    }

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();


}