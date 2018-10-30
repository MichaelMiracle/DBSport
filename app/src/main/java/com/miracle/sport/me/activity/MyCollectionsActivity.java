package com.miracle.sport.me.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.AppConfig;
import com.miracle.base.BaseActivity;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZService;
import com.miracle.databinding.SwipeRecyclerBinding;
import com.miracle.michael.doudizhu.activity.DDZNewsDetailActivity;
import com.miracle.michael.lottery.adapter.LotteryMyCollectionAdapter;
import com.miracle.sport.SportService;
import com.miracle.sport.home.activity.SimpleWebActivity;
import com.miracle.sport.home.adapter.HomeListAdapter;
import com.miracle.sport.me.adapter.CollectionsListAdapter;

public class MyCollectionsActivity extends BaseActivity<SwipeRecyclerBinding> {

    private CollectionsListAdapter mAdapter;
    private PageLoadCallback callBack;

    @Override
    public int getLayout() {
        return R.layout.swipe_recycler;
    }

    @Override
    public void initView() {
        setTitle("我的收藏");
        binding.recyclerView.setAdapter(mAdapter = new CollectionsListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initCallback();
    }

    private void initCallback() {
        callBack = new PageLoadCallback(mAdapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
                ZClient.getService(SportService.class).getMycollections(page,limit).enqueue(callBack);
            }
        };
        callBack.initSwipeRefreshLayout(binding.swipeRefreshLayout);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setNewData(null);
        callBack.onRefresh();
    }


    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, SimpleWebActivity.class).putExtra("id", mAdapter.getItem(position).getId()));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
