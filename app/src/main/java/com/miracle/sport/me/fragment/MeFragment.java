package com.miracle.sport.me.fragment;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.View;

import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.GOTO;
import com.miracle.base.bean.UserInfoBean;
import com.miracle.base.network.GlideApp;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.base.network.ZService;
import com.miracle.base.util.CommonUtils;
import com.miracle.databinding.F4Ddz2Binding;
import com.miracle.databinding.F4DdzBinding;
import com.miracle.sport.me.activity.DDZMyCircleActivity;
import com.miracle.sport.me.activity.DDZMyPostActivity;
import com.miracle.sport.me.activity.DDZMyReplyActivity;
import com.miracle.sport.me.activity.MyCollectionsActivity;
import com.wx.goodview.GoodView;

public class MeFragment extends BaseFragment<F4Ddz2Binding> {
    private UserInfoBean userInfo;
    private GoodView goodView;

    @Override
    public int getLayout() {
        return R.layout.f4_ddz2;
    }

    @Override
    public void initView() {
        binding.titleBar.showLeft(drawerLayout != null);
        goodView = new GoodView(mContext);
    }

    private void reqData() {
        if (CommonUtils.getUser() != null)
            ZClient.getService(ZService.class).getUserInfo().enqueue(new ZCallback<ZResponse<UserInfoBean>>(binding.swipeLayout) {
                @Override
                public void onSuccess(ZResponse<UserInfoBean> data) {
                    userInfo = data.getData();
                    binding.tvName.setText(userInfo.getNickname());
                    GlideApp.with(mContext).load(userInfo.getImg()).placeholder(R.mipmap.default_head).into(binding.ivHeadImg);
                }
            });
    }

    @Override
    public void initListener() {
        binding.llMe.setOnClickListener(this);
        binding.ibOrderManage.setOnClickListener(this);
        binding.ibmyCircle.setOnClickListener(this);
        binding.ibmyPost.setOnClickListener(this);
        binding.ibmyReply.setOnClickListener(this);
        binding.ibBailManage.setOnClickListener(this);
        binding.ibSettings.setOnClickListener(this);
        binding.ibGroupChat.setOnClickListener(this);
        binding.ibCustomerService.setOnClickListener(this);
        binding.ibShare.setOnClickListener(this);
        binding.ibAboutUs.setOnClickListener(this);
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reqData();
            }
        });

        binding.titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout != null)
                    drawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        reqData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llMe:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
                    GOTO.MeInfoActivity(mContext,userInfo);
                }
                break;
            case R.id.ibOrderManage:
                GOTO.FootballSaiShiFenXiActivity(mContext);
                break;
            case R.id.ibBailManage:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
//                    GOTO.LotteryMyCollectionsActivity();
                    startActivity(new Intent(mContext, MyCollectionsActivity.class));
                }
                break;
            case R.id.ibmyCircle:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
//                    GOTO.LotteryMyCollectionsActivity();
                    startActivity(new Intent(mContext, DDZMyCircleActivity.class));
                }
                break;
            case R.id.ibmyPost:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
//                    GOTO.LotteryMyCollectionsActivity();
                    startActivity(new Intent(mContext, DDZMyPostActivity.class));
                }
                break;
            case R.id.ibmyReply:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
//                    GOTO.LotteryMyCollectionsActivity();
                    startActivity(new Intent(mContext, DDZMyReplyActivity.class));
                }
                break;
            case R.id.ibSettings:
                GOTO.SettingActivity(mContext);
                break;
            case R.id.ibGroupChat:
                GOTO.ChatActivity(mContext);
                break;

            case R.id.ibCustomerService:
                GOTO.CustomerServiceActivity(mContext);
                break;
            case R.id.ibShare:
                if (userInfo == null) {
                    GOTO.LoginActivity(mContext);
                } else {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, userInfo.getNickname() + "邀请你加入" + CommonUtils.getAppName(mContext));
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent, "分享"));
                }
                break;

            case R.id.ibAboutUs:
                GOTO.AboutUsActivity(mContext);
                break;

        }
    }


    private DrawerLayout drawerLayout;

    public MeFragment setDrawer(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
        return this;
    }
}
