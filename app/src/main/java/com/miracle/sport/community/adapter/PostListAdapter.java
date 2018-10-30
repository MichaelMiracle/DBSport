package com.miracle.sport.community.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.network.GlideApp;
import com.miracle.base.util.ContextHolder;
import com.miracle.sport.community.bean.PostBean;

import java.util.List;

/**
 * Created by Michael on 2018/10/29 15:26 (星期一)
 */
public class PostListAdapter extends RecyclerViewAdapter<PostBean> {

    private LinearLayout.LayoutParams params;

    public PostListAdapter() {
        super(R.layout.item_post);
        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostBean item) {
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvTime, item.getAdd_time());
        helper.setText(R.id.tvLike, item.getClick_num() + "");
        helper.setText(R.id.tvComment, item.getComment_num() + "");
        helper.setText(R.id.tvCircleName, item.getName());
        helper.setText(R.id.tvAuthorName, item.getNickname());

        List<String> thumbs = item.getThumb();
        if (thumbs != null && !thumbs.isEmpty()) {
            helper.setGone(R.id.llPics, true);
            LinearLayout container = helper.getView(R.id.llPics);
            for (String url : thumbs) {
                ImageView imageView = new ImageView(ContextHolder.getContext());
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                GlideApp.with(ContextHolder.getContext()).load(url)
                        .placeholder(R.mipmap.defaule_img)
                        .error(R.mipmap.empty)
                        .into(imageView);
                container.addView(imageView);
            }
        } else {
            helper.setGone(R.id.llPics, false);
        }
    }
}
