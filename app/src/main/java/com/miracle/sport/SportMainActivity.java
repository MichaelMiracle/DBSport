package com.miracle.sport;

import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.GOTO;
import com.miracle.databinding.ActivitySportMainBinding;
import com.miracle.michael.doudizhu.fragment.DDZF1;
import com.miracle.michael.doudizhu.fragment.DDZF2;
import com.miracle.michael.doudizhu.fragment.DDZF4;
import com.miracle.michael.football.fragment.FootballF3;

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
        binding.zRadiogroup.setupWithContainerAndFragments(R.id.container, new DDZF1(), new DDZF2(), new FootballF3(), new DDZF4());
    }

    @Override
    public void initListener() {
        binding.tvContactCustomerService.setOnClickListener(this);
        binding.rlGroupChat.setOnClickListener(this);
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
