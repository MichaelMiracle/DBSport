package com.miracle.sport.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.AppConfig;
import com.miracle.base.BaseFragment;
import com.miracle.base.Constant;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.PageLoadDataCallback;
import com.miracle.base.network.ZClient;
import com.miracle.databinding.FragmentCategoryHomeBinding;
import com.miracle.michael.chess.adapter.ChessListAdapter;
import com.miracle.sport.SportService;
import com.miracle.sport.home.ServiceHome;
import com.miracle.sport.home.activity.SimpleWebActivity;
import com.miracle.sport.home.adapter.HomeListAdapter;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ChannelHomeFragment extends BaseFragment<FragmentCategoryHomeBinding> {


    private HomeListAdapter mAdapter;
    private PageLoadDataCallback callBack;

    private int reqKey = 1;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_category_home;
    }

    @Override
    public void initView() {

//        reqKey =  Integer.parseInt(getArguments().getString(Constant.CHANNEL_CODE));
        reqKey = Integer.parseInt(getArguments().getString(Constant.CHANNEL_CODE));

        binding.recyclerView.setAdapter(mAdapter = new HomeListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
//        binding.recyclerView.setHasFixedSize(true);
        binding.tvCategoryTitle.setText("社会");
        initCallback();
        callBack.onRefresh();
    }

    private void initCallback() {
        callBack = new PageLoadDataCallback(mAdapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
                ZClient.getService(SportService.class).getNewsList(reqKey, page, limit).enqueue(callBack);
            }
        };
        callBack.setSwipeRefreshLayout(binding.swipeRefreshLayout);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(new Intent(mContext, SimpleWebChessActivity.class).putExtra("id", mAdapter.getItem(position).getId()));
                startActivity(new Intent(mContext, SimpleWebActivity.class).putExtra("id", mAdapter.getItem(position).getId()));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {

    }

//    public void setReqKey(FootballKey footballKey) {
//        this.reqKey = footballKey.getId();
//        binding.tvCategoryTitle.setText(footballKey.getName());
//        callBack.onRefresh();
//    }
}
