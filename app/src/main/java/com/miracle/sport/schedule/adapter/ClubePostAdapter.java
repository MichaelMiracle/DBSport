package com.miracle.sport.schedule.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.sport.schedule.bean.post.ClubePostJF;

public class ClubePostAdapter extends RecyclerViewAdapter<ClubePostJF> {
    Context context;

    public ClubePostAdapter(Context context) {
        super(R.layout.item_clube_post);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClubePostJF item) {
//        GlideApp.with(context).load(item.get);
        helper.setText(R.id.item_clube_time1, item.getAmount());
        helper.setText(R.id.tvIndex, item.getClub_name()+"XXXX");
        helper.setText(R.id.tvIndex, item.getClub_name()+"XXXX");
        helper.setText(R.id.tvIndex, item.getClub_name()+"XXXX");
        helper.setText(R.id.tvIndex, item.getClub_name()+"XXXX");
        helper.setText(R.id.tvIndex, item.getClub_name()+"XXXX");
    }
}
