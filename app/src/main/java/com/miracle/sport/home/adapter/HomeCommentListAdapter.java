package com.miracle.sport.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.network.GlideApp;
import com.miracle.base.util.CommonUtils;
import com.miracle.sport.home.bean.Football;
import com.miracle.sport.home.bean.HomeCommentBean;

public class HomeCommentListAdapter extends RecyclerViewAdapter<HomeCommentBean> {
    private Context context;

    public HomeCommentListAdapter(Context context) {
        super(R.layout.item_home_comment);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCommentBean item) {
        helper.setText(R.id.tvTitle, item.getNickname());
        helper.setText(R.id.tvTime, item.getAdd_time());
        helper.setText(R.id.tvAuthor,item.getContent());
//        Glide.with(context)
//                .load(item.getThumb())
//                .into((ImageView) helper.getView(R.id.iv));
        String thumb = item.getImg();
             GlideApp.with(context).load(thumb)
                     .placeholder(R.mipmap.defaule_img)
                     .error(R.mipmap.empty)
                     .into((ImageView) helper.getView(R.id.iv));
    }
}
