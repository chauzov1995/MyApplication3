package com.example.myapplication;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nikita on 27.11.2017.
 */

public class fragment_tab extends Fragment {


    View v;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    static final int PAGE_COUNT = 30;
    ArrayList<String> datamassiv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main333, null);






        MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT DISTINCT data_fakt FROM `an_dkr_hist` WHERE visible=0 order by data_fakt asc", null);
        //число записей  c.getCount()
        datamassiv = new ArrayList<String>();
        if(c.moveToFirst()){

            do {
                datamassiv.add(c.getString(c.getColumnIndex("data_fakt")));

            } while (c.moveToNext());
        }


        c.close();
        MainActivity.loadhist(getActivity());


        Log.d("asdas","asdasd"+ c.getCount());

        //   pager=(ViewPager) v.findViewById(R.id.pager);
        //  pager.setAdapter(new (getActivity(), MainActivity.fm, datamassiv));
        // pager.setCurrentItem(datamassiv.size()-1);


        pager=(ViewPager) v.findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(MainActivity.fm, datamassiv);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(datamassiv.size()-1);
        Log.d("asdad","Восстановлен таб"+(datamassiv.size()-1));






return v;
    }



    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<String>  datamassiv;
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<String> _datamassiv) {
            super(fm);
           datamassiv=_datamassiv;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return PageFragment.newInstance(position, datamassiv);
        }

        @Override
        public int getCount() {
            return datamassiv.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Дата " + datamassiv.get(position);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}