package com.miracle.sport.community.activity;

import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.databinding.ActivityPublishPostBinding;

/**
 * Created by Michael on 2018/10/29 21:57 (星期一)
 */
public class PublishPostActivity extends BaseActivity<ActivityPublishPostBinding> {
    @Override
    public int getLayout() {
        return R.layout.activity_publish_post;
    }

    @Override
    public void initView() {
        setTitle("发帖");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
