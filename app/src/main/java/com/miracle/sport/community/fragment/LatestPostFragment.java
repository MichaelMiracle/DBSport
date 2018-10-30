package com.miracle.sport.community.fragment;

import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.network.PageLoadCallback;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.databinding.FragmentHotpostBinding;
import com.miracle.databinding.RecyclerBinding;
import com.miracle.sport.SportService;
import com.miracle.sport.community.adapter.PostListAdapter;

/**
 * Created by Michael on 2018/10/29 14:07 (星期一)
 */
public class LatestPostFragment extends BaseFragment<FragmentHotpostBinding> {


    private PostListAdapter mAdapter;
    private PageLoadCallback callBack;


    @Override
    public int getLayout() {
        return R.layout.fragment_hotpost;
    }

    @Override
    public void initView() {

        binding.recyclerView.setAdapter(mAdapter = new PostListAdapter());
        initCallback();


    }

    private void initCallback() {
        callBack = new PageLoadCallback(mAdapter, binding.recyclerView) {
            @Override
            public void requestAction(int page, int pageSize) {
                ZClient.getService(SportService.class).getPostList(null, null, page, pageSize).enqueue(callBack);
            }
        };
        callBack.setSwipeRefreshLayout(((CommunityFragment) getParentFragment()).getSwipeRefreshLayout());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        callBack.onRefresh();
    }

    public void refresh() {
        callBack.onRefresh();
    }
}
