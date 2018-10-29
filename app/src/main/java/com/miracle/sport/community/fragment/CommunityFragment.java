package com.miracle.sport.community.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.miracle.R;
import com.miracle.base.BaseFragment;
import com.miracle.base.network.GlideApp;
import com.miracle.base.util.ContextHolder;
import com.miracle.databinding.BannerLayoutBinding;
import com.miracle.databinding.FragmentCommunityBinding;
import com.miracle.databinding.SwipeRecyclerBinding;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 2018/10/29 11:23 (星期一)
 */
public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {
    private List<String> images;

    private HotPostFragment hotPostFragment;
    private LatestPostFragment latestPostFragment;

    @Override
    public int getLayout() {
        return R.layout.fragment_community;
    }

    @Override
    public void initView() {
        binding.zRadiogroup.setUp(getChildFragmentManager(), R.id.containerCommunity, hotPostFragment = new HotPostFragment(), latestPostFragment = new LatestPostFragment());
        initBanner();
    }

    private void initBanner() {
        images = new ArrayList<>();
        images.add("file:///android_asset/football/16.jpg");
        images.add("file:///android_asset/football/17.jpg");
        images.add("file:///android_asset/football/18.jpg");
        images.add("file:///android_asset/football/19.jpg");
        images.add("file:///android_asset/football/20.jpg");
        binding.banner.setImages(images).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideApp.with(ContextHolder.getContext()).load(path).placeholder(R.mipmap.defaule_img).into(imageView);

            }
        }).start();

        binding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
    }

    @Override
    public void initListener() {

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hotPostFragment.refresh();
                latestPostFragment.refresh();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
