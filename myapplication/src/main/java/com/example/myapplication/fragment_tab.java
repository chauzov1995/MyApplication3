package com.example.myapplication;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 27.11.2017.
 */

public class fragment_tab extends Fragment {


    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main333, null);











return v;
    }



    @Override
    public void onResume() {
        super.onResume();





        MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT DISTINCT data_fakt FROM `an_dkr_hist` order by data_fakt asc", null);
        //число записей  c.getCount()
        ArrayList<String> datamassiv = new ArrayList<String>();
        if(c.moveToFirst()){

            do {
                datamassiv.add(c.getString(c.getColumnIndex("data_fakt")));

            } while (c.moveToNext());
        }


        c.close();
        MainActivity.loadhist(getActivity());


        Log.d("asdas","asdasd"+ c.getCount());

        ViewPager pager=(ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getActivity(), MainActivity.fm, datamassiv));
        pager.setCurrentItem(datamassiv.size()-1);

Log.d("asdad","Восстановлен таб");



    }




}