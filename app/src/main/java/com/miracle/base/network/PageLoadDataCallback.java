package com.miracle.base.network;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.miracle.R;
import com.miracle.base.App;
import com.miracle.base.adapter.RecyclerViewAdapter;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.NetStateUtils;
import com.miracle.base.util.ToastUtil;
import com.miracle.sport.home.bean.Football;
import com.miracle.sport.home.bean.HomeBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NaOH on 2018/8/3 15:11 (星期五).
 */
public abstract class PageLoadDataCallback<T> implements Callback<T>, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerViewAdapter mAdapter;
    private int page = 1;
    private int pageSize = 10;
    private boolean isLoadMore;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public PageLoadDataCallback(RecyclerViewAdapter adapter, RecyclerView recyclerView) {
        adapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter = adapter;
    }

    public void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout = swipeRefreshLayout;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body != null && body instanceof ZResponse) {
            ZResponse data = (ZResponse) body;
            switch (data.getCode()) {
                case 200:
                    List<Football> data1 = ((HomeBean) data.getData()).getData();
                    page++;
                    if (isLoadMore) {
                        mAdapter.addData(data1);
                    } else {
                        mAdapter.setNewData(data1);
                    }
                    if (mAdapter.getData().size() == data.getTotal()) {
                        mAdapter.loadMoreEnd();
                    } else {
                        mAdapter.loadMoreComplete();
                    }
                    onFinish(call);
                    break;

                case 0:
                    mAdapter.loadMoreEnd();
                    onFinish(call);
                    break;

                default:
                    onFailure(call, new Throwable(response.message()));
                    break;
            }
        } else {
            onFailure(call, new Throwable(response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mAdapter.loadMoreFail();
        onFinish(call);
    }

    public void onFinish(Call<T> call) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        call.cancel();
    }

    @Override
    public void onRefresh() {
        if (!NetStateUtils.isNetworkConnected(App.getApp())) {
            ToastUtil.toast(App.getApp(), CommonUtils.getString(R.string.no_net));
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        isLoadMore = false;
        page = 1;
        requestAction(page, pageSize);
    }

    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        requestAction(page, pageSize);
    }

    public abstract void requestAction(int page, int limit);
}
