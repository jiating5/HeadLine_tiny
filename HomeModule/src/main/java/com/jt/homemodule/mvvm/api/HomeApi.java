package com.jt.homemodule.mvvm.api;

import androidx.lifecycle.LiveData;

import com.jt.basemodule.net.bean.BaseRespEntity;
import com.jt.homemodule.mvvm.bean.RecommendNewsBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author 贾婷
 * @date 2020/1/18.
 * GitHub：https://github.com/jiating5
 * description：
 */
public interface DataApi {
    @GET("getRecommendNews")
    LiveData<BaseRespEntity<List<RecommendNewsBean>>> getRecommendNews(@Query("pagesize") String pagesize);
}
