package com.miracle.sport.community.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.ToastUtil;
import com.miracle.databinding.ActivityPostDetailBinding;
import com.miracle.sport.SportService;
import com.miracle.sport.common.view.CommentBar;
import com.miracle.sport.community.adapter.PostCommentAdapter;
import com.miracle.sport.community.adapter.PostDetailImagesAdapter;
import com.miracle.sport.community.bean.PostCommentBean;
import com.miracle.sport.community.bean.PostDetailBean;

import java.util.List;

/**
 * Created by Michael on 2018/10/30 21:22 (星期二)
 */
public class PostDetailActivity extends BaseActivity<ActivityPostDetailBinding> {
    private int id;

    private PostDetailImagesAdapter mAdater;

    private PostCommentAdapter pAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_post_detail;
    }

    @Override
    public void initView() {
        setTitle("详情");
        id = getIntent().getIntExtra("id", 0);
        binding.recyclerView.setAdapter(mAdater = new PostDetailImagesAdapter());
        binding.rvComment.setAdapter(pAdapter = new PostCommentAdapter());
        reqPostDetail();
        reqCommentList();
    }

    private void reqPostDetail() {
        ZClient.getService(SportService.class).getPostDetail(id).enqueue(new ZCallback<ZResponse<PostDetailBean>>() {
            @Override
            public void onSuccess(ZResponse<PostDetailBean> data) {
                setData(data.getData());
            }
        });
    }

    private void setData(PostDetailBean data) {
        binding.tvCircle.setText(data.getName());
        binding.tvTitle.setText(data.getTitle());
        binding.tvContent.setText(data.getContent());
        binding.commentBar.setCommentNum(data.getComment_num());
        binding.commentBar.setLikeNum(data.getClick_num());
        binding.commentBar.setLike(data.getClick() == 1);
        List<String> thumb = data.getThumb();
        if (thumb != null && !thumb.isEmpty()) {
            mAdater.setNewData(thumb);
        }
    }

    @Override
    public void initListener() {
        binding.commentBar.setCBListener(new CommentBar.CBListener() {
            @Override
            public void send(String content) {
                ZClient.getService(SportService.class).sendPostComment(id, content).enqueue(new ZCallback<ZResponse<List<PostCommentBean>>>() {
                    @Override
                    public void onSuccess(ZResponse<List<PostCommentBean>> data) {
                        ToastUtil.toast(data.getMessage());
                        binding.commentBar.clearContent();
                        reqPostDetail();
                        reqCommentList();
                        CommonUtils.hideSoftInput(mContext,binding.getRoot());
                    }
                });
            }

            @Override
            public void onLikeClick() {
                ZClient.getService(SportService.class).likePost(id, 1).enqueue(new ZCallback<ZResponse>() {
                    @Override
                    public void onSuccess(ZResponse data) {
                        ToastUtil.toast(data.getMessage());
                        reqPostDetail();
                    }
                });
            }

            @Override
            public void onCommentClick() {

            }
        });
        pAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tvLike) {
                    ZClient.getService(SportService.class).likePostComment(pAdapter.getItem(position).getComment_id(), 1, "pl").enqueue(likeCallback);
                }
            }
        });
    }

    private ZCallback<ZResponse> likeCallback = new ZCallback<ZResponse>() {
        @Override
        public void onSuccess(ZResponse data) {
            ToastUtil.toast(data.getMessage());
            reqCommentList();
        }
    };

    private void reqCommentList() {
        ZClient.getService(SportService.class).getPostCommentList(id).enqueue(new ZCallback<ZResponse<List<PostCommentBean>>>() {
            @Override
            public void onSuccess(ZResponse<List<PostCommentBean>> data) {
                pAdapter.setNewData(data.getData());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
