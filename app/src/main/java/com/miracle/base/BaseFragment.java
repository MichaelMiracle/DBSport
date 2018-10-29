package com.miracle.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miracle.R;
import com.miracle.databinding.FragmentBaseBinding;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment implements View.OnClickListener {
    protected Context mContext;
    protected B binding;
    private FragmentBaseBinding mBaseBinding;

    public enum ShowStat{
        LOADING,
        NORMAL,
        NODATA,
        ERR
    }
    private ShowStat showStat = ShowStat.NORMAL;

    public void setUIStatus(ShowStat status){
        switch (status){
            case LOADING:
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_loading).setVisibility(View.VISIBLE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_nodata).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_err).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.baseFragContainer).setVisibility(View.GONE);
                break;
            case NORMAL:
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_loading).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_nodata).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_err).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.baseFragContainer).setVisibility(View.VISIBLE);
                break;
            case NODATA:
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_loading).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_nodata).setVisibility(View.VISIBLE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_err).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.baseFragContainer).setVisibility(View.GONE);
                break;
            case ERR:
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_loading).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_nodata).setVisibility(View.GONE);
                mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_err).setVisibility(View.VISIBLE);
                mBaseBinding.getRoot().findViewById(R.id.baseFragContainer).setVisibility(View.GONE);
                break;
        }
    }

    protected void onNodataClick(){

    }

    protected void onErrClick(){

    }

    protected void onLoadingClick(){

    }

    protected void initUIStatus(){
        mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadingClick();
            }
        });
        mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_nodata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNodataClick();
            }
        });
        mBaseBinding.getRoot().findViewById(R.id.base_frag_ui_status_err).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrClick();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBaseBinding == null) {
            mBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, null, false);
            binding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), getLayout(), null, false);
            mBaseBinding.baseFragContainer.addView(binding.getRoot());

            initUIStatus();

            initView();
            initListener();
        }
        return mBaseBinding.getRoot();
    }


    public abstract int getLayout();

    public abstract void initView();

    public abstract void initListener();
}
