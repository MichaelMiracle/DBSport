package com.miracle.sport.community.activity;

import android.view.View;
import android.widget.AdapterView;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.databinding.ActivityCircleBinding;
import com.miracle.sport.SportService;
import com.miracle.sport.community.adapter.CircleIndexAdapter;
import com.miracle.sport.community.bean.CircleBean;
import com.miracle.sport.community.fragment.CircleFragment;

import java.util.List;

/**
 * Created by Michael on 2018/10/30 15:38 (星期二)
 */
public class CircleActivity extends BaseActivity<ActivityCircleBinding> {

    private CircleIndexAdapter mAdapter;
    private CircleFragment circleFragment;

    @Override
    public int getLayout() {
        setTitle("圈子");
        return R.layout.activity_circle;
    }

    @Override
    public void initView() {
        mAdapter = new CircleIndexAdapter(mContext);
        binding.indexListView.setAdapter(mAdapter);
        circleFragment = (CircleFragment) getSupportFragmentManager().findFragmentById(R.id.circleFragment);
        reqData();
    }

    private void reqData() {
        ZClient.getService(SportService.class).getCircleList().enqueue(new ZCallback<ZResponse<List<CircleBean>>>() {
            @Override
            public void onSuccess(ZResponse<List<CircleBean>> data) {
                mAdapter.update(data.getData());
                mAdapter.setSelectPosition(0);
                circleFragment.setData(mAdapter.getItem(0).getChild());
            }
        });
    }

    @Override
    public void initListener() {
        binding.indexListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                mAdapter.setSelectPosition(position);
                circleFragment.setData(mAdapter.getItem(position).getChild());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
