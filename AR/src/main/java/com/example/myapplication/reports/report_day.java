package com.example.myapplication.reports;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapplication.DB_sql;
import com.example.myapplication.history.PageFragment;
import com.example.myapplication.R;
import com.example.myapplication.dohod.dodod_activity;

import java.util.ArrayList;

public class report_day extends AppCompatActivity {


    ArrayList<String> datamassiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_day);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DB_sql dbHelper = new DB_sql(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT DISTINCT data_fakt FROM `an_dkr_hist` WHERE visible=0 order by data_fakt asc", null);
        //число записей  c.getCount()
        datamassiv = new ArrayList<String>();
        if (c.moveToFirst()) {

            do {
                datamassiv.add(c.getString(c.getColumnIndex("data_fakt")));

            } while (c.moveToNext());
        }


        c.close();
        dodod_activity.loadhist(this);


        Log.d("asdas", "asdasd" + c.getCount());

//
        //   ViewPager pager=(ViewPager) findViewById(R.id.pager);
        //     pager.setAdapter(new (this, getSupportFragmentManager(), datamassiv));
        //    pager.setCurrentItem(datamassiv.size()-1);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), datamassiv);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(datamassiv.size() - 1);
        Log.d("asdad", "Восстановлен таб" + (datamassiv.size() - 1));
    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<String> datamassiv;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<String> _datamassiv) {
            super(fm);
            datamassiv = _datamassiv;
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
