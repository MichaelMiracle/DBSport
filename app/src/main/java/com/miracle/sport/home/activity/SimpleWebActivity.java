package com.miracle.sport.home.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.miracle.R;
import com.miracle.base.BaseActivity;
import com.miracle.base.GOTO;
import com.miracle.base.network.ZCallback;
import com.miracle.base.network.ZClient;
import com.miracle.base.network.ZResponse;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.ToastUtil;
import com.miracle.base.util.sqlite.SQLiteKey;
import com.miracle.base.util.sqlite.SQLiteUtil;
import com.miracle.databinding.ActivityHomeWebBinding;
import com.miracle.michael.common.bean.NewsDetailBean;
import com.miracle.sport.SportService;
import com.miracle.sport.home.ServiceHome;
import com.miracle.sport.home.adapter.HomeCommentListAdapter;
import com.miracle.sport.home.adapter.HomeListAdapter;
import com.miracle.sport.home.bean.HomeBean;
import com.miracle.sport.home.bean.HomeCommentBean;
import com.wx.goodview.GoodView;

import java.util.List;


public class SimpleWebActivity extends BaseActivity<ActivityHomeWebBinding> {

    private int id;
    private GoodView goodView;
    private HomeCommentListAdapter mAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_home_web;
    }

    @Override
    public void initView() {
        hideTitle();
        binding.tvTitle.setText("详情");
        id = getIntent().getIntExtra("id", 0);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        goodView = new GoodView(this);


        binding.webView.setHorizontalScrollBarEnabled(false);//水平不显示
        binding.webView.setVerticalScrollBarEnabled(false); //垂直不显示
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        binding.recyclerView.setAdapter(mAdapter = new HomeCommentListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        setContentView(webView);
//        binding.webView.loadUrl(mUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reqData();
    }

    private void reqData() {
        ZClient.getService(SportService.class).getNewsDetail(id).enqueue(new ZCallback<ZResponse<NewsDetailBean>>(binding.swipeRefreshLayout) {
            @Override
            public void onSuccess(ZResponse<NewsDetailBean> data) {
                binding.cbRight.setChecked(data.getData().getCoin() == 1);
                binding.webView.loadDataWithBaseURL(null, CommonUtils.getHtmlData(data.getData().getContent()), "text/html", "utf-8", null);
            }
        });

        ZClient.getService(SportService.class).getCommentList(id).enqueue(new ZCallback<ZResponse<List<HomeCommentBean>>>() {
            @Override
            public void onSuccess(ZResponse<List<HomeCommentBean>> data) {
                mAdapter.setNewData(data.getData());
            }
        });
    }


    @Override
    public void initListener() {
        binding.rlLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.cbRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(SQLiteUtil.getString(SQLiteKey.USER))){
//                    ZClientFootBall.getService(Service1.class).likeOrDislike(id,Constant.TYPEAPP).enqueue(likeCallback);
                }else{
                    GOTO.LoginActivity();
                }

            }
        });
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reqData();
            }
        });

        binding.ivGood.setOnClickListener(this);
        binding.includeSendComment.imgSend.setOnClickListener(this);
    }

    private ZCallback<ZResponse> likeCallback = new ZCallback<ZResponse>() {
        @Override
        public void onSuccess(ZResponse data) {
            ToastUtil.toast(data.getMessage());
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_good:
                goodView.setTextInfo("+1", Color.parseColor("#f66467"), 14);
                goodView.show(v);
                break;
            case R.id.img_send:
                ZClient.getService(SportService.class).sendHomeCommet(id , binding.includeSendComment.etCommentContent.getText().toString()).enqueue(new ZCallback<ZResponse<String>>() {
                    @Override
                    public void onSuccess(ZResponse<String> data) {
                        ToastUtil.toast("评论成功");
                    }
                });
                break;
        }

    }

}
