package com.jt.homemodule.mvvm.view.fragment;


import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.jt.basemodule.base.BaseFragment;
import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.commonmodule.utils.ToastUtils;
import com.jt.homemodule.R;
import com.jt.homemodule.mvvm.bean.RecommendNewsBean;
import com.jt.homemodule.mvvm.view.adapter.ReCommentAdapter;
import com.jt.homemodule.mvvm.viewmodel.HomeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends BaseFragment<HomeViewModel, ViewDataBinding> {

    RecyclerView rv_recommend;
    LiveData<BaseRespEntity<List<RecommendNewsBean>>> allType;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        allType = mViewModel.getAllType("2");
        allType.observe(getActivity(), new Observer<BaseRespEntity<List<RecommendNewsBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<RecommendNewsBean>> listBaseRespEntity) {
                if (listBaseRespEntity.getMsg()!=null){
                    List<RecommendNewsBean> data = listBaseRespEntity.getData();
                    ReCommentAdapter adapter = new ReCommentAdapter(getContext(), data);
                    rv_recommend.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv_recommend.setAdapter(adapter);
                }else {
                    ToastUtils.showMsg(getContext(),"失败");
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        rv_recommend = view.findViewById(R.id.home_rv_recommend);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

}
