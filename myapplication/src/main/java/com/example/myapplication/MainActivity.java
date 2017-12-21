package com.example.myapplication;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView mTextMessage;
    FragmentTransaction fTrans;
    statistics frag2;

    new_dkr New_dkr;

    public  static ArrayList<Product> dkr_kart = new ArrayList<Product>();
    public  static ArrayList<Product> products_doh = new ArrayList<Product>();
    public  static ArrayList<Product> products_rash = new ArrayList<Product>();
    public  static ArrayList<Product> products_zel = new ArrayList<Product>();
    public  static ArrayList<History> history = new ArrayList<History>();
    MainActivity3.DBHelper dbHelper;





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








        //  Fragment_tab = new fragment_tab();
        dbHelper = new MainActivity3.DBHelper(this);


        setTitle("Отчёт по дням");

        //fTrans.replace(R.id.frgntm, new fragment_dohod());
      //  fTrans.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.loadcart(this);

        RecyclerAdapter  boxAdapter1 = new RecyclerAdapter(MainActivity.products_doh, this);
     RecyclerView gvdoh = (RecyclerView) findViewById(R.id.gvdoh);
        GridLayoutManager glm1=new GridLayoutManager(this,3);
        RecyclerView.LayoutManager  mLayoutManager1 = glm1;

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
        if (id == R.id.action_settings) {
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static void loadcart(Context context){

        MainActivity3.DBHelper dbHelper = new MainActivity3.DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        products_doh.clear();
        products_rash.clear();
        products_zel.clear();

        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.rawQuery("select * from `an_dohod` Where visible=0", null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int summa_dohod = c.getColumnIndex("summa_dohod");
            int summa_fakt = c.getColumnIndex("summa_fakt");
            int komment = c.getColumnIndex("komment");
            int name_dohod = c.getColumnIndex("name_dohod");
            int postoyan = c.getColumnIndex("postoyan");

            do {
                products_doh.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                        c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));


                /*
switch (c.getInt(name_dohod)){
    case 1:
        products_doh.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));
        break;
    case 2:
        products_rash.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));
        break;
    case 3:
        products_zel.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));
        break;
}
*/
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }

        products_doh.add(new Product("Добавить доход", 0,
                0, 0, 1, 1, 0));
        //   products_rash.add(new Product("Добавить расход", 0,
        //          0, 0, 2, 1, 0));
        //   products_zel.add(new Product("Добавить цель", 0,
        //        0, 0, 3, 1,0));
        c.close();



    }


    public static void load_dkr_cart(Context context){

        MainActivity3.DBHelper dbHelper = new MainActivity3.DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dkr_kart.clear();


        Cursor c = db.rawQuery("SELECT * FROM `an_dohod` Where visible=0 AND (name_dohod = 1 OR name_dohod = 2) order by name_dohod DESC ", null);




        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int summa_dohod = c.getColumnIndex("summa_dohod");
            int summa_fakt = c.getColumnIndex("summa_fakt");
            int komment = c.getColumnIndex("komment");
            int name_dohod = c.getColumnIndex("name_dohod");
            int postoyan = c.getColumnIndex("postoyan");

            do {


                dkr_kart.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                        c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0, c.getInt(postoyan)));


                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }


        c.close();



    }


    public static void loadhist(Context context){

        MainActivity3.DBHelper dbHelper = new MainActivity3.DBHelper(context);
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


                history.add(new History(c.getInt(id), c.getString(komment), c.getInt(summa),
                        c.getString(data_fakt), c.getInt(kuda), c.getInt(name_dohod), c.getString(nazv_doh)));


                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }

        c.close();



    }





    public static class  DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // создаем таблицу с полями
//ЕСЛИ




            db.execSQL("CREATE TABLE `an_dohod` (\n" +
                    "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `name_dohod` int(11) NOT NULL ,\n" +
                    "  `summa_dohod` decimal(11,0) NOT NULL,\n" +
                    "  `summa_fakt` decimal(10,0) NOT NULL,\n" +
                    "  `komment` varchar(255) NOT NULL,\n" +
                    "  `visible` int(11) NOT NULL ,\n" +
                    "  `postoyan` int(11) NOT NULL\n" +
                    ");");
            db.execSQL(" CREATE TABLE `an_dkr_hist` (\n" +
                    "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `kuda` int(11) NOT NULL,\n" +
                    "  `summa` decimal(10,0) NOT NULL,\n" +
                    "  `komment` varchar(255) NOT NULL,\n" +
                    "  `data_fakt` varchar(255) NOT NULL,\n" +
                    "  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  `visible` int(11) NOT NULL,\n" +
                    "  `postoyan` int(11) NOT NULL,\n" +
                    "  `name_dohod` int(11) NOT NULL\n" +
                    ");"


            );
            db.execSQL("CREATE TABLE `an_users` (\n" +
                    "  `datareg` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  `dohod` decimal(10,0) NOT NULL,\n" +
                    "  `rashod` decimal(10,0) NOT NULL,\n" +
                    "  `zel` decimal(10,0) NOT NULL,\n" +
                    "  `vsego_mes_potr` decimal(10,0) NOT NULL,\n" +
                    "  `vsego_mes_zarab` int(11) NOT NULL,\n" +
                    "  `balance` decimal(10,0) NOT NULL\n" +
                    ");");



            db.execSQL("INSERT INTO `an_users`" +
                    "( `dohod`, `rashod`, `zel`, `vsego_mes_potr`, `vsego_mes_zarab`, `balance`) VALUES" +
                    " (0,0,0,0,0,0)");





            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (1,0,0,'Доход',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Продукты',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Еда вне дома',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Транспорт',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Покупки',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Дом. хоз-во',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Развлечения',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (2,0,0,'Услуги',0,0)");
            db.execSQL("INSERT INTO `an_dohod`" +
                    "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                    " (3,0,0,'Цель',0,0)");


            //   Toast.makeText(getactivity,
            //          Long.toString(rowID), Toast.LENGTH_SHORT).show();
        }





        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


