package com.miracle.sport.community.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.network.GlideApp;
import com.miracle.base.util.ContextHolder;
import com.miracle.sport.community.bean.PostBean;

/**
 * Created by Michael on 2018/10/29 15:26 (星期一)
 */
public class PostListAdapter extends RecyclerViewAdapter<PostBean> {

    public PostListAdapter() {
        super(R.layout.item_post);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostBean item) {
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvTime, item.getAdd_time());
        GlideApp.with(ContextHolder.getContext()).load(item.getThumb())
                .placeholder(R.mipmap.defaule_img)
                .error(R.mipmap.empty)
                .into((ImageView) helper.getView(R.id.iv0));
    }
}