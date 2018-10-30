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

public class HomeListAdapter extends RecyclerViewAdapter<Football> {
    private Context context;

    public HomeListAdapter(Context context) {
        super(R.layout.item_home);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Football item) {
        helper.setText(R.id.tvTitle, item.getTitle().replace("微信",""));
        helper.setText(R.id.tvTime, item.getTime());
        helper.setText(R.id.tvAuthor,CommonUtils.getAppName(context));
//        Glide.with(context)
//                .load(item.getThumb())
//                .into((ImageView) helper.getView(R.id.iv));
        String thumb = item.getThumb();
         if(1 == item.getImages().length){
             String urlLoad = "";
             if(!TextUtils.isEmpty(thumb)){
                 urlLoad = thumb;
             }else{
                 urlLoad = item.getImages()[0];
             }
            GlideApp.with(context).load(urlLoad)
                    .placeholder(R.mipmap.defaule_img)
                    .error(R.mipmap.empty)
                    .into((ImageView) helper.getView(R.id.iv1_1));
            helper.setGone(R.id.iv1_1, true);
            helper.setGone(R.id.iv1_2, false);
            helper.setGone(R.id.iv2_2, false);
            helper.setGone(R.id.iv1, false);
            helper.setGone(R.id.iv2, false);
            helper.setGone(R.id.iv3, false);
        }else if(2 == item.getImages().length){
             String urlLoad = "";
             if(!TextUtils.isEmpty(thumb)){
                 urlLoad = thumb;
             }else{
                 urlLoad = item.getImages()[0];
             }
            GlideApp.with(context).load(urlLoad)
                    .placeholder(R.mipmap.defaule_img)
                    .error(R.mipmap.empty)
                    .into((ImageView) helper.getView(R.id.iv1_2));
             GlideApp.with(context).load(item.getImages()[1])
                     .placeholder(R.mipmap.defaule_img)
                     .error(R.mipmap.empty)
                     .into((ImageView) helper.getView(R.id.iv2_2));
            helper.setGone(R.id.iv1_1, false);
            helper.setGone(R.id.iv1_2, true);
            helper.setGone(R.id.iv2_2, true);
            helper.setGone(R.id.iv1, false);
            helper.setGone(R.id.iv2, false);
            helper.setGone(R.id.iv3, false);
        }else if(3 <= item.getImages().length){

             String urlLoad = "";
             if(!TextUtils.isEmpty(thumb)){
                 urlLoad = thumb;
             }else{
                 urlLoad = item.getImages()[0];
             }

            GlideApp.with(context).load(urlLoad)
                    .placeholder(R.mipmap.defaule_img)
                    .error(R.mipmap.empty)
                    .into((ImageView) helper.getView(R.id.iv1));
             GlideApp.with(context).load(item.getImages()[1])
                     .placeholder(R.mipmap.defaule_img)
                     .error(R.mipmap.empty)
                     .into((ImageView) helper.getView(R.id.iv2));
             GlideApp.with(context).load(item.getImages()[2])
                     .placeholder(R.mipmap.defaule_img)
                     .error(R.mipmap.empty)
                     .into((ImageView) helper.getView(R.id.iv3));
            helper.setGone(R.id.iv1_1, false);
            helper.setGone(R.id.iv1_2, false);
            helper.setGone(R.id.iv2_2, false);
            helper.setGone(R.id.iv1, true);
            helper.setGone(R.id.iv2, true);
            helper.setGone(R.id.iv3, true);
        }else if (0 == item.getImages().length && !TextUtils.isEmpty(item.getThumb())) {
             GlideApp.with(context).load(item.getThumb())
                     .placeholder(R.mipmap.defaule_img)
                     .error(R.mipmap.empty)
                     .into((ImageView) helper.getView(R.id.iv1_1));
             helper.setGone(R.id.iv1_1, true);
             helper.setGone(R.id.iv1_2, false);
             helper.setGone(R.id.iv2_2, false);
             helper.setGone(R.id.iv1, false);
             helper.setGone(R.id.iv2, false);
             helper.setGone(R.id.iv3, false);
        }else{
             helper.setGone(R.id.iv1_1, false);
             helper.setGone(R.id.iv1_2, false);
             helper.setGone(R.id.iv2_2, false);
            helper.setGone(R.id.iv1, false);
            helper.setGone(R.id.iv2, false);
            helper.setGone(R.id.iv3, false);
        }

    }
}
