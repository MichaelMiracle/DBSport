package com.miracle.sport.schedule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.miracle.sport.schedule.bean.ClubeType;
import com.miracle.sport.schedule.bean.ClubeItem;
import com.miracle.sport.schedule.fragment.FragClubePost;

import java.util.ArrayList;
import java.util.List;

//频道横条
public class ClubeItemTabAdapter extends FragmentPagerAdapter {
    List<ClubeItem> datas = new ArrayList<>();
    public ClubeType parentType;

    public ClubeType getParentType() {
        return parentType;
    }

    public void setParentType(ClubeType parentType) {
        this.parentType = parentType;
    }

    public List<ClubeItem> getDatas() {
        return datas;
    }

    public void setDatas(List<ClubeItem> datas) {
        this.datas = datas;
    }

    public ClubeItemTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ClubeItem item = datas.get(position);

        FragClubePost frag = new FragClubePost();
        frag.setParentType(parentType);
        frag.setReq(item);
        return frag;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getName();
    }

    public List getData() {
        return datas;
    }
}
