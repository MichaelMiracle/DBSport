package com.miracle.sport.community.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.miracle.R;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.util.CommonUtils;
import com.miracle.sport.community.bean.CircleBean;

/**
 * Created by Michael on 2018/10/30 16:27 (星期二)
 */
public class CircleChildAdapter extends RecyclerViewAdapter<CircleBean.ChildBean> {

    private String selectOn, selectOff;

    public CircleChildAdapter() {
        super(R.layout.item_circle);
        selectOn = CommonUtils.getString(R.string.icon_select_on);
        selectOff = CommonUtils.getString(R.string.icon_select_off);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleBean.ChildBean item) {
        helper.addOnClickListener(R.id.tvCheck);
        helper.setText(R.id.tvName, item.getName());
        helper.setText(R.id.tvCheck, item.getCoin() == 1 ? selectOn : selectOff);
    }
}
