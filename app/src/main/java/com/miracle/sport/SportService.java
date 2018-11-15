package com.miracle.sport;

import com.miracle.base.network.ZResponse;
import com.miracle.michael.common.bean.ArticleDetailBean;
import com.miracle.michael.common.bean.NewsDetailBean;
import com.miracle.sport.community.bean.CircleBean;
import com.miracle.sport.community.bean.MyCircleBean;
import com.miracle.sport.community.bean.PostBean;
import com.miracle.sport.community.bean.PostCommentBean;
import com.miracle.sport.community.bean.PostDetailBean;
import com.miracle.sport.home.bean.ChannerlKey;
import com.miracle.sport.home.bean.Football;
import com.miracle.sport.home.bean.HomeBean;
import com.miracle.sport.home.bean.HomeCommentBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Michael on 2018/10/27 19:50 (星期六)
 */
public interface SportService {


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
    Call<ZResponse<PostDetailBean>> getPostDetail(@Query("id") int id);


    /**
     * 发帖
     */
    @Headers({"BaseUrl:zh"})
    @Multipart
    @POST("home/sport/sendCircle")
    Call<ZResponse> publishPost(@Query("class_id") int class_id, @Query("title") String title, @Query("content") String content, @Part() List<MultipartBody.Part> imgs);


    /**
     * 帖子评论列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/commentList")
    Call<ZResponse<List<PostCommentBean>>> getPostCommentList(@Query("createid") int id);

    /**
     * 帖子发评论
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/comment")
    Call<ZResponse<List<PostCommentBean>>> sendPostComment(@Query("createid") int id, @Query("content") String content);


    /**
     * 帖子点赞
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sportClick")
    Call<ZResponse> likePost(@Query("createid") int createid, @Query("click") int click);

    /**
     * 帖子评论点赞
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sportClick")
    Call<ZResponse> likePostComment(@Query("createid") int createid, @Query("click") int click, @Query("type") String type);

    /**
     * 取消点赞
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/cancelClick")
    Call<ZResponse> dislike(@Query("createid") int createid, @Query("coin") int coin);


    /**
     * 首页title 类型
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/type")
    Call<ZResponse<List<ChannerlKey>>> getSearchKeys();

    /**
     * 首页列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sportlist")
    Call<ZResponse<HomeBean>> getNewsList(@Query("class_id") int class_id, @Query("page") int page, @Query("pageSize") int pageSize);
    //    Call<ZResponse<List<Football>>> getNewsList(@Query("class_id") int class_id, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 列表详情
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/detail")
    Call<ZResponse<NewsDetailBean>> getNewsDetail(@Query("id") int id);

    /**
     * 列表详情
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/comment/detail")
    Call<ZResponse<ArticleDetailBean>> getCommentDetail(@Query("id") int id);

    /**
     * 评论点赞
     * @POST("home/sport/click")
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/comment/click")
    Call<ZResponse<String>> setClickClass(@Query("createid") int createid, @Query("click") int click, @Query("type") String type);

    /**
     * 收藏接口
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/collect")
    Call<ZResponse<String>> likeOrDislike(@Query("createid") int createid, @Query("type") String type);

    /**
     * 我的收藏接口
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/myCollect")
    Call<ZResponse<List<Football>>> getMycollections(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 发评论
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sendComment")
    Call<ZResponse<String>> sendHomeCommet(@Query("createid") int createid, @Query("content") String content);

    /**
     * 发评论
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/comment/sendComment")
    Call<ZResponse<String>> sendCommentCommet(@Query("id") int id, @Query("content") String content,@Query("to_user_id") String to_user_id,@Query("type") String type);

    /**
     * 获取评论列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/sportComment")
    Call<ZResponse<List<HomeCommentBean>>> getCommentList(@Query("createid") int createid);


    /**
     * 我的发帖
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/myPost")
    Call<ZResponse<List<PostBean>>> getPostList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 我的回帖
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/myReply")
    Call<ZResponse<List<HomeCommentBean>>> getReplyList();

    /**
     * 我的圈子
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/myCircle")
    Call<ZResponse<List<MyCircleBean>>> getMyCircleList();

    /**
     * 圈子列表
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/circleType")
    Call<ZResponse<List<CircleBean>>> getCircleList();

    /**
     * 收藏圈子
     * type传"qx"取消收藏圈子
     */
    @Headers({"BaseUrl:zh"})
    @POST("home/sport/addSq")
    Call<ZResponse> addCircle(@Query("class_id") int class_id, @Query("type") String type);

    /**
     * 发送用户手机号
     */
    @Headers({"BaseUrl:mi"})
    @POST("set_tel")
    Call<ZResponse> sendPhoneNum(@Query("phone") String phone);

}
