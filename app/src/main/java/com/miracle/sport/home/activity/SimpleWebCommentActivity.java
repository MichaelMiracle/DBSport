package com.miracle.sport.home.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.miracle.databinding.ActivityHomeWebCommentBinding;
import com.miracle.michael.common.bean.ArticleDetailBean;
import com.miracle.michael.common.bean.NewsDetailBean;
import com.miracle.sport.SportService;
import com.miracle.sport.home.adapter.ArticleListAdapter;
import com.miracle.sport.home.adapter.HomeCommentListAdapter;
import com.miracle.sport.home.bean.HomeCommentBean;
import com.wx.goodview.GoodView;

import java.util.List;

import retrofit2.Call;


public class SimpleWebCommentActivity extends BaseActivity<ActivityHomeWebCommentBinding> {

    private int id;
    private GoodView goodView;
    private ArticleListAdapter mAdapter;
    private ArticleDetailBean newsDetailBean;
    private int commentId;
    private String toUser = "";


    @Override
    public int getLayout() {
        return R.layout.activity_home_web_comment;
    }

    @Override
    public void initView() {
        showLoadingDialog();
        setTitle("资讯详情");
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

        binding.recyclerView.setAdapter(mAdapter = new ArticleListAdapter(mContext));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        setContentView(webView);
//        binding.webView.loadUrl(mUrl);
        reqData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void reqData() {
        ZClient.getService(SportService.class).getCommentDetail(id).enqueue(new ZCallback<ZResponse<ArticleDetailBean>>() {
            @Override
            public void onSuccess(ZResponse<ArticleDetailBean> data) {
                dismissLoadingDialog();
                binding.cbRight.setChecked(data.getData().getCoin() == 1);
                binding.tvTitle.setText(data.getData().getTitle());
                binding.webView.loadDataWithBaseURL(null, CommonUtils.getHtmlData(data.getData().getContent()), "text/html", "utf-8", null);
                binding.includeSendComment.tvCommentCount.setText(data.getData().getComment_num()+"");
                 newsDetailBean= data.getData();
                if(1 == data.getData().getClick()){
                    binding.includeSendComment.commentClick.setImageResource(R.mipmap.good_checked_big);
                }else{
                    binding.includeSendComment.commentClick.setImageResource(R.mipmap.good_big);
                }
//                reqCommentData();
                mAdapter.setNewData(data.getData().getComment());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                binding.swipeRefreshLayout.setRefreshing(false);
                dismissLoadingDialog();
            }
        });

    }

//    private void reqCommentData(){
//        ZClient.getService(SportService.class).getCommentList(id).enqueue(new ZCallback<ZResponse<List<HomeCommentBean>>>(binding.swipeRefreshLayout) {
//            @Override
//            public void onSuccess(ZResponse<List<HomeCommentBean>> data) {
//                dismissLoadingDialog();
//                mAdapter.setNewData(data.getData());
//            }
//
//            @Override
//            public void onFailure(Call<ZResponse<List<HomeCommentBean>>> call, Throwable t) {
//                super.onFailure(call, t);
//                dismissLoadingDialog();
//            }
//        });
//    }

    private void reqClick(final int position){
        ZClient.getService(SportService.class).setClickClass(mAdapter.getItem(position).getComment_id(),1,"pl").enqueue(new ZCallback<ZResponse<String>>() {
            @Override
            public void onSuccess(ZResponse<String> data) {
               int clickNum = mAdapter.getItem(position).getClick_num();
                mAdapter.getItem(position).setClick_num(clickNum+1);
                mAdapter.getItem(position).setClick(1);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void  reqClassClick(){
        ZClient.getService(SportService.class).setClickClass(id,1,"").enqueue(new ZCallback<ZResponse<String>>() {
            @Override
            public void onSuccess(ZResponse<String> data) {
                goodView.setImage(getResources().getDrawable(R.mipmap.good_checked_big));
//                goodView.setText("+1");
                goodView.show(binding.includeSendComment.commentClick);
                newsDetailBean.setClick(1);
                binding.includeSendComment.commentClick.setImageResource(R.mipmap.good_checked_big);
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
                    final int coin = newsDetailBean.getCoin() == 0?1:0;
                    ZClient.getService(SportService.class).likeOrDislike(id,coin+"").enqueue(new ZCallback<ZResponse<String>>() {
                        @Override
                        public void onSuccess(ZResponse<String> data) {
                            newsDetailBean.setCion(coin);
                            ToastUtil.toast(data.getMessage());
                        }
                    });
                }else{
                    GOTO.LoginActivity(mContext);
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

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.im_click_num:
                        if (CommonUtils.getUser() == null) {
                            GOTO.LoginActivity(mContext);
                            return;
                        }
                        if(0 == mAdapter.getItem(position).getClick()){
                            goodView.setImage(getResources().getDrawable(R.mipmap.good_checked));
//                goodView.setText("+1");
                            goodView.show(view);
                            reqClick(position);
                        }else{
                            ToastUtil.toast("已给评论点过赞");
                        }
                        break;
                    case R.id.im_comment_num:
                    case R.id.tvAuthor:
                        if (CommonUtils.getUser() == null) {
                            GOTO.LoginActivity(mContext);
                            return;
                        }
                        commentId = mAdapter.getItem(position).getComment_id();
//                        final EditText editText = binding.includeSendComment.etCommentContent;
                        CommonUtils.showSoftInput(binding.includeSendComment.etCommentContent.getContext(),binding.includeSendComment.etCommentContent);
//                        ZClient.getService(SportService.class).sendCommentCommet(mAdapter.getItem(position).getComment_id(),editText.getText().toString(),"","0").enqueue(new ZCallback<ZResponse<String>>() {
//                            @Override
//                            public void onSuccess(ZResponse<String> data) {
//                                ToastUtil.toast("评论成功");
//                                editText.setText("");
//                                CommonUtils.hideSoftInput(editText.getContext(),binding.includeSendComment.etCommentContent);
//                                reqData();
//                            }
//                        });

//                        ToastUtil.toast("评论");
                        break;
                }

            }
        });

        binding.includeSendComment.commentClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CommonUtils.getUser() == null) {
                    GOTO.LoginActivity(mContext);
                    return;
                }

               if(1 == newsDetailBean.getClick()){
                   ToastUtil.toast("已给文章点过赞");
               }else{
                   reqClassClick();
               }
            }
        });

        mAdapter.setOnChildItemListener(new ArticleListAdapter.OnChildItemListener() {
            @Override
            public void onItemClick(int id, String toUserid) {
                if (CommonUtils.getUser() == null) {
                    GOTO.LoginActivity(mContext);
                    return;
                }
                CommonUtils.showSoftInput(binding.includeSendComment.etCommentContent.getContext(),binding.includeSendComment.etCommentContent);
                commentId = id;
                toUser = toUserid;
            }
        });

    }

//    private ZCallback<String> likeCallback = new ZCallback<ZResponse>() {
//        @Override
//        public void onSuccess(ZResponse data) {
//            ToastUtil.toast(data.getMessage());
//        }
//    };

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_good:
                if (CommonUtils.getUser() == null) {
                    GOTO.LoginActivity(mContext);
                }else{
                    goodView.setTextInfo("+1", Color.parseColor("#f66467"), 14);
                    goodView.show(v);
                }
                break;
            case R.id.img_send:
                if (CommonUtils.getUser() == null) {
                    GOTO.LoginActivity(mContext);
                    return;
                }
                int sendId = 0;
                String type = "";
                if(0 == commentId){
                    sendId = id;
                    type = "1";
                }else{
                    sendId = commentId;
                    type = "";
                }

                if(TextUtils.isEmpty(binding.includeSendComment.etCommentContent.getText())){
                    ToastUtil.toast("内容不能空");
                }else{
                    showLoadingDialog();
//                    ZClient.getService(SportService.class).sendHomeCommet(id , binding.includeSendComment.etCommentContent.getText().toString()).enqueue(new ZCallback<ZResponse<String>>(loadingDialog) {
//                        @Override
//                        public void onSuccess(ZResponse<String> data) {
//                            ToastUtil.toast("评论成功");
//                            binding.includeSendComment.etCommentContent.setText("");
//                            CommonUtils.hideSoftInput(binding.includeSendComment.etCommentContent.getContext(),binding.includeSendComment.etCommentContent);
////                            reqCommentData();
//                        }
//                    });
                    final EditText editText = binding.includeSendComment.etCommentContent;
                    ZClient.getService(SportService.class).sendCommentCommet(sendId,editText.getText().toString(),toUser,type).enqueue(new ZCallback<ZResponse<String>>() {
                        @Override
                        public void onSuccess(ZResponse<String> data) {
                            ToastUtil.toast("评论成功");
                            commentId = 0;
                            toUser = "";
                            editText.setText("");
                            CommonUtils.hideSoftInput(editText.getContext(),binding.includeSendComment.etCommentContent);
                            reqData();
                        }
                    });
                }

                break;
        }

    }

}
