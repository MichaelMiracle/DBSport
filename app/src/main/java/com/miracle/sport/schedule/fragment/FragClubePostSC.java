package com.miracle.sport.schedule.fragment;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.miracle.R;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.ZClient;
import com.miracle.databinding.FragClubePostBinding;
import com.miracle.sport.schedule.adapter.ClubePostSCAdapter;
import com.miracle.sport.schedule.bean.ClubeItem;
import com.miracle.sport.schedule.bean.ClubeType;
import com.miracle.sport.schedule.net.FootClubServer;

//赛事比分内容
public class FragClubePostSC extends HandleFragment<FragClubePostBinding> {
    ClubeItem req;
    ClubeType parentType;
    PageLoadCallback callback;
    ClubePostSCAdapter adapter;

    public ClubeType getParentType() {
        return parentType;
    }

    public void setParentType(ClubeType parentType) {
        this.parentType = parentType;
    }

    public ClubeItem getReq() {
        return req;
    }

    public void setReq(ClubeItem req) {
        this.req = req;
    }

    @Override
    public void onHandleMessage(Message msg) {
        if(msg.what == 1)
            callback.onRefresh();
    }

    @Override
    public int getLayout() {
        return R.layout.frag_clube_post;
    }

    @Override
    public void initView() {
        adapter = new ClubePostSCAdapter(mContext);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerView.setHasFixedSize(true);

        callback = new PageLoadCallback(adapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
                ZClient.getService(FootClubServer.class).getFootClubPostSC(parentType.getId(),req.getType(),page,limit).enqueue(this);
            }
        };
        callback.initSwipeRefreshLayout(binding.swipeRefreshLayout);

        callback.onRefresh();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void reqData(){
        uiHandler.sendEmptyMessage(1);
    }


}
