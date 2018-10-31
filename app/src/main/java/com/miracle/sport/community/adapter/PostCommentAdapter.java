package com.miracle.sport.community.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.network.GlideApp;
import com.miracle.base.util.ContextHolder;
import com.miracle.sport.community.bean.PostCommentBean;

/**
 * Created by Michael on 2018/10/30 22:16 (星期二)
 */
public class PostCommentAdapter extends RecyclerViewAdapter<PostCommentBean> {

    public PostCommentAdapter() {
        super(R.layout.item_post_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostCommentBean item) {
        helper.setText(R.id.tvName, item.getNickname());
        helper.setText(R.id.tvContent, item.getContent());
        helper.setText(R.id.tvTime, item.getAdd_time());
        helper.setText(R.id.tvLikeCount, item.getClick_num() + "");

        GlideApp.with(ContextHolder.getContext())
                .load(item.getImg())
                .placeholder(R.mipmap.default_head)
                .into((ImageView) helper.getView(R.id.ivHead));
    }
}
