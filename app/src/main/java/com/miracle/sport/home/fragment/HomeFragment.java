package com.miracle.sport.home.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.Constant;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.databinding.FragmentHomeBinding;
import com.miracle.sport.SportService;
import com.miracle.sport.home.ServiceHome;
import com.miracle.sport.home.adapter.ChannelPagerAdapter;
import com.miracle.sport.home.bean.Channel;
import com.miracle.sport.home.bean.ChannerlKey;
import com.miracle.sport.home.listener.OnChannelListener;
import com.miracle.sport.home.util.PreUtils;
import com.miracle.sport.home.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;



public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements OnChannelListener {

    private List<Channel> mSelectedChannels = new ArrayList<>();
    private List<Channel> mUnSelectedChannels = new ArrayList<Channel>();
    private List<ChannerlKey> mNetChannels = new ArrayList<>();
    private List<ChannelHomeFragment> mChannelFragments = new ArrayList<>();
    private ChannelPagerAdapter mChannelPagerAdapter;
    private Gson mGson = new Gson();
    private String[] mChannelCodes;

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        reqData();
//        initChannelData();
//        initChannelFragments();

    }

    private void reqData() {
        ZClient.getService(SportService.class).getSearchKeys().enqueue(new ZCallback<ZResponse<List<ChannerlKey>>>() {
            @Override
            public void onSuccess(ZResponse<List<ChannerlKey>> data) {
                mNetChannels = data.getData();
                initChannelData();
                initChannelFragments();
                setListener();
            }

            @Override
            public void onFailure(Call<ZResponse<List<ChannerlKey>>> call, Throwable t) {
                super.onFailure(call, t);
                initChannelData();
                initChannelFragments();
                setListener();
            }
        });
    }

    private void setListener() {

        binding.ivOperation.setOnClickListener(this);

        mChannelPagerAdapter = new ChannelPagerAdapter(mChannelFragments, mSelectedChannels,getChildFragmentManager());
        mChannelPagerAdapter.notifyDataSetChanged();
        binding.vpContent.setAdapter(mChannelPagerAdapter);
        binding.vpContent.setOffscreenPageLimit(mSelectedChannels.size());

        binding.tabChannel.setTabPaddingLeftAndRight(UIUtils.dip2Px(10), UIUtils.dip2Px(10));
        binding.tabChannel.setupWithViewPager(binding.vpContent);
        binding.tabChannel.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) binding.tabChannel.getChildAt(0);
                slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + binding.ivOperation.getMeasuredWidth());
            }
        });
        //隐藏指示器
        binding.tabChannel.setSelectedTabIndicatorHeight(0);

        binding.vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当页签切换的时候，如果有播放视频，则释放资源
//                JCVideoPlayer.releaseAllVideos();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initListener() {
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:

                break;
            case R.id.iv_operation:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedChannels, mUnSelectedChannels);
                dialogFragment.setOnChannelListener(this);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mChannelPagerAdapter.notifyDataSetChanged();
                        binding.vpContent.setOffscreenPageLimit(mSelectedChannels.size());
                        binding.tabChannel.setCurrentItem(binding.tabChannel.getSelectedTabPosition());
                        ViewGroup slidingTabStrip = (ViewGroup) binding.tabChannel.getChildAt(0);
                        //注意：因为最开始设置了最小宽度，所以重新测量宽度的时候一定要先将最小宽度设置为0
                        slidingTabStrip.setMinimumWidth(0);
                        slidingTabStrip.measure(0, 0);
                        slidingTabStrip.setMinimumWidth(slidingTabStrip.getMeasuredWidth() + binding.ivOperation.getMeasuredWidth());

                        //保存选中和未选中的channel
                        PreUtils.putString(Constant.SELECTED_CHANNEL_JSON, mGson.toJson(mSelectedChannels));
                        PreUtils.putString(Constant.UNSELECTED_CHANNEL_JSON, mGson.toJson(mUnSelectedChannels));
                    }
                });
                break;
        }

    }


    /**
     * 初始化已选频道和未选频道的数据
     */
    private void initChannelData() {
        String selectedChannelJson = PreUtils.getString(Constant.SELECTED_CHANNEL_JSON, "");
        String unselectChannel = PreUtils.getString(Constant.UNSELECTED_CHANNEL_JSON, "");
        if (TextUtils.isEmpty(selectedChannelJson) || TextUtils.isEmpty(unselectChannel)) {
//        if (TextUtils.isEmpty(selectedChannelJson)) {
            //本地没有title
//            String[] channels = getResources().getStringArray(R.array.channel);
//            String[] channelCodes = getResources().getStringArray(R.array.channel_code);
            //默认添加了全部频道
            for (int i = 0; i < mNetChannels.size(); i++) {
                String title = mNetChannels.get(i).getName();
                String code = mNetChannels.get(i).getId()+"";
                mSelectedChannels.add(new Channel(title, code));
            }

            selectedChannelJson = mGson.toJson(mSelectedChannels);//将集合转换成json字符串
//            KLog.i("selectedChannelJson:" + selectedChannelJson);
            PreUtils.putString(Constant.SELECTED_CHANNEL_JSON, selectedChannelJson);//保存到sp
        } else {
            //之前添加过
            List<Channel> selectedChannel = mGson.fromJson(selectedChannelJson, new TypeToken<List<Channel>>() {
            }.getType());
            List<Channel> unselectedChannel = mGson.fromJson(unselectChannel, new TypeToken<List<Channel>>() {
            }.getType());
            mSelectedChannels.clear();
            mUnSelectedChannels.clear();
            mSelectedChannels.addAll(selectedChannel);
            if(null != unselectChannel){
                mUnSelectedChannels.addAll(unselectedChannel);
            }
        }
    }

    /**
     * 初始化已选频道的fragment的集合
     */
    private void initChannelFragments() {
//        KLog.e("initChannelFragments");
//        mChannelCodes = m;
        for (Channel channel : mSelectedChannels) {
            ChannelHomeFragment newsFragment = new ChannelHomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE, channel.id);
//            bundle.putBoolean(Constant.IS_VIDEO_LIST, channel.id.equals(mChannelCodes[1]));//是否是视频列表页面,根据判断频道号是否是视频
            newsFragment.setArguments(bundle);
            mChannelFragments.add(newsFragment);//添加到集合中
        }
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        listMove(mSelectedChannels, starPos, endPos);
        listMove(mChannelFragments, starPos, endPos);
    }


    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        //移动到我的频道
        Channel channel = mUnSelectedChannels.remove(starPos);
        mSelectedChannels.add(endPos, channel);

        mChannelPagerAdapter.notifyDataSetChanged();

        ChannelHomeFragment newsFragment = new ChannelHomeFragment();
//        newsFragment.setReqKey();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CHANNEL_CODE, channel.id);
//        bundle.putBoolean(Constant.IS_VIDEO_LIST, channel.id.equals(mChannelCodes[1]));
        newsFragment.setArguments(bundle);
        mChannelFragments.add(newsFragment);
        mChannelPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        mUnSelectedChannels.add(endPos, mSelectedChannels.remove(starPos));
        mChannelFragments.remove(starPos);
        mChannelPagerAdapter.notifyDataSetChanged();
    }

    private void listMove(List datas, int starPos, int endPos) {
        Object o = datas.get(starPos);
        //先删除之前的位置
        datas.remove(starPos);
        //添加到现在的位置
        datas.add(endPos, o);
    }

}
