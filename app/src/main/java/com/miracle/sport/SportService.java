package com.miracle.sport;

import com.miracle.base.network.ZResponse;
import com.miracle.michael.common.bean.NewsDetailBean;
import com.miracle.sport.community.bean.PostBean;

import java.util.List;

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
     *
     * @param type     rm 热门，传空 最新
     * @param class_id 圈子的帖子 ，传空 所有帖子，
     * @param page     当前页
     * @param pageSize 每页数量
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/circleList")
    Call<ZResponse<List<PostBean>>> getPostList(@Query("type") String type, @Query("class_id") Integer class_id, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 帖子详情
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


    /**
     * 点赞
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/click")
    Call<ZResponse> like(@Query("createid") int createid);

    /**
     * 取消点赞
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/cancelClick")
    Call<ZResponse> dislike(@Query("createid") int createid, @Query("coin") int coin);


}
