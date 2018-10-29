package com.miracle.sport;

import com.miracle.base.network.ZResponse;
import com.miracle.michael.common.bean.NewsDetailBean;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Michael on 2018/10/27 19:50 (星期六)
 */
public interface SportService {

    /**
     * 我的圈子
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/myCircle")
    Call<ZResponse> getMyCircle(@Query("id") int id);


    /**
     * 帖子列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/circleList")
    Call<ZResponse> getPostList(@Query("page") int page, @Query("pageSzie") int pageSzie);

    /**
     * 帖子列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/circleDetail")
    Call<ZResponse> getPostDetail(@Query("id") int id);


    /**
     * 发帖
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sendCircle")
    Call<ZResponse> publishPost(@Query("class_id") int class_id);

}
