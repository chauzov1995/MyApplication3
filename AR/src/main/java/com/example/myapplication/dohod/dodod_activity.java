package com.example.myapplication.dohod;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.DB_sql;
import com.example.myapplication.R;
import com.example.myapplication.history.history_class;

import com.example.myapplication.reports.report_day;
import com.example.myapplication.reports.statistics;

import java.util.ArrayList;

public class dodod_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView mTextMessage;
    FragmentTransaction fTrans;
    statistics frag2;



    public static ArrayList<dohod_class> dkr_kart = new ArrayList<dohod_class>();


    public static ArrayList<history_class> history = new ArrayList<history_class>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        DB_sql dbHelper = new DB_sql(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.rawQuery("select * from `an_dohod` Where visible=0", null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        ArrayList<dohod_class> products_doh = new ArrayList<dohod_class>();
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int summa_dohod = c.getColumnIndex("summa_dohod");
            int summa_fakt = c.getColumnIndex("summa_fakt");
            int komment = c.getColumnIndex("komment");
            int name_dohod = c.getColumnIndex("name_dohod");
            int postoyan = c.getColumnIndex("postoyan");


            do {
                products_doh.add(new dohod_class(c.getString(komment), c.getInt(summa_dohod),
                        c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }

        products_doh.add(new dohod_class("Добавить доход", 0,
                0, 0, 1, 1, 0));

        c.close();


        dohod_adapter boxAdapter1 = new dohod_adapter(products_doh, this);
        RecyclerView gvdoh = (RecyclerView) findViewById(R.id.gvdoh);
        GridLayoutManager glm1 = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager mLayoutManager1 = glm1;

        gvdoh.setLayoutManager(mLayoutManager1);

        gvdoh.setAdapter(boxAdapter1);
        gvdoh.setHasFixedSize(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_micro) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        fTrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_camera) {
            // Handle the camera action


            Intent intent = new Intent(this, report_day.class);

            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static void loadhist(Context context) {

        DB_sql dbHelper = new DB_sql(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        history.clear();


        Cursor c = db.rawQuery("SELECT an_dkr_hist.id," +
                " an_dkr_hist.summa," +
                " an_dkr_hist.komment as komment," +
                " an_dkr_hist.kuda," +
                " an_dkr_hist.data_fakt," +
                " an_dkr_hist.name_dohod," +
                " an_dohod.komment as nazv_doh FROM `an_dkr_hist`" +
                " LEFT JOIN an_dohod ON an_dkr_hist.kuda = an_dohod.id" +
                " Where an_dkr_hist.visible=0", null);


        if (c.moveToFirst()) {


            int id = c.getColumnIndex("id");
            int summa = c.getColumnIndex("summa");

            int komment = c.getColumnIndex("komment");
            int kuda = c.getColumnIndex("kuda");
            int data_fakt = c.getColumnIndex("data_fakt");
            int name_dohod = c.getColumnIndex("name_dohod");
            int nazv_doh = c.getColumnIndex("nazv_doh");

            do {
                history.add(new history_class(c.getInt(id), c.getString(komment), c.getInt(summa),
                        c.getString(data_fakt), c.getInt(kuda), c.getInt(name_dohod), c.getString(nazv_doh)));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }

        c.close();

        //  Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG).show();

    }


}


