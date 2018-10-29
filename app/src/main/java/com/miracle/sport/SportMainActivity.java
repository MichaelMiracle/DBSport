package com.miracle.sport;

import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.GOTO;
import com.miracle.base.network.ZClient;
import com.miracle.databinding.ActivitySportMainBinding;
import com.miracle.michael.doudizhu.fragment.DDZF1;
import com.miracle.michael.doudizhu.fragment.DDZF2;
import com.miracle.michael.doudizhu.fragment.DDZF4;
import com.miracle.michael.football.fragment.FootballF3;
import com.miracle.sport.community.fragment.CommunityFragment;
import com.miracle.sport.home.fragment.HomeFragment;
import com.miracle.sport.schedule.fragment.FragClubeTypeChannelVP;

/**
 * Created by Michael on 2018/10/27 13:32 (星期六)
 */
public class SportMainActivity extends BaseActivity<ActivitySportMainBinding> {
    @Override
    public int getLayout() {
        return R.layout.activity_sport_main;
    }

    @Override
    public void initView() {
        hideTitle();
        binding.zRadiogroup.setupWithContainerAndFragments(R.id.container, new HomeFragment(), new FragClubeTypeChannelVP(), new CommunityFragment(), new DDZF4());
    }

    @Override
    public void initListener() {
        binding.tvContactCustomerService.setOnClickListener(this);
        binding.rlGroupChat.setOnClickListener(this);
        ZClient.getService(SportService.class).dislike(2, 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvContactCustomerService:
                GOTO.CustomerServiceActivity();
                break;
            case R.id.rlGroupChat:
                GOTO.ChatActivity();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
