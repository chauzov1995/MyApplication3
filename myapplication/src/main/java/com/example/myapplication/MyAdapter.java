package com.example.myapplication;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapter extends FragmentStatePagerAdapter {
    private Context context = null;
    int itemscol;
    ArrayList<String> datearr;

    public MyAdapter(Context context, FragmentManager mgr, ArrayList<String> _datearr) {
        super(mgr);
        this.context = context;
        itemscol=_datearr.size();
        datearr=_datearr;

    }

    @Override
    public int getCount() {


        return (itemscol);
    }



    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(datearr.get(position)));
    }

    @Override
    public String getPageTitle(int position) {


        return (PageFragment.getTitle(context, datearr.get(position)));
    }
}