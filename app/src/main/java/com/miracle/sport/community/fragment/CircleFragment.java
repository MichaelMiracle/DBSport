package com.miracle.sport.community.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.databinding.RecyclerBinding;
import com.miracle.sport.community.adapter.CircleChildAdapter;
import com.miracle.sport.community.bean.CircleBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class CircleFragment extends BaseFragment<RecyclerBinding> {

    private CircleChildAdapter mAdapter;

    @Override
    public int getLayout() {
        return R.layout.recycler;
    }

    @Override
    public void initView() {
        binding.recyclerView.setAdapter(mAdapter = new CircleChildAdapter());
    }

    @Override
    public void initListener() {

    }


    @Override
    public void onClick(View v) {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CircleBean.ChildBean item = mAdapter.getItem(position);
            }
        });
    }

    public void setData(List<CircleBean.ChildBean> data) {
        mAdapter.setNewData(data);
    }
}
