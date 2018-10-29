package com.miracle.sport.schedule.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.ZClient;
import com.miracle.databinding.FragClubetypeListBinding;
import com.miracle.sport.schedule.activity.ClubeItemVPAct;
import com.miracle.sport.schedule.adapter.ClubeTypeAdapter;
import com.miracle.sport.schedule.net.FootClubServer;

//賽事  FragClubetypeListBinding
public class FragClubeTypeList extends BaseFragment<FragClubetypeListBinding> {
    PageLoadCallback callback;
    ClubeTypeAdapter clubTypeAdapter;

    @Override
    public int getLayout() {
        return R.layout.frag_clubetype_list;
    }

    @Override
    public void initView() {
        clubTypeAdapter = new ClubeTypeAdapter(mContext);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.recyclerView.setAdapter(clubTypeAdapter);
        clubTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                clubTypeAdapter.getData().get(position).getId();
                Intent intent = new Intent(mContext, ClubeItemVPAct.class);
                intent.putExtra(ClubeItemVPAct.EXTRA_ID, clubTypeAdapter.getData().get(position).getId());
                intent.putExtra(ClubeItemVPAct.EXTRA_NAME, clubTypeAdapter.getData().get(position).getName());
                startActivity(intent);
            }
        });

        callback = new PageLoadCallback(clubTypeAdapter ,binding.recyclerView) {
            @Override
            public void requestAction(int page, int limit) {
                ZClient.getService(FootClubServer.class).getFootClubTypes(page, limit).enqueue(this);
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
}
