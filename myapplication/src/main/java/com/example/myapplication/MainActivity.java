package com.example.myapplication;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction fTrans;
    statistics frag2;
    fragment_dohod fragment_dohod;
    new_dkr New_dkr;

    public  static ArrayList<Product> dkr_kart = new ArrayList<Product>();
  public  static ArrayList<Product> products_doh = new ArrayList<Product>();
    public  static ArrayList<Product> products_rash = new ArrayList<Product>();
    public  static ArrayList<Product> products_zel = new ArrayList<Product>();
    public  static ArrayList<History> history = new ArrayList<History>();
    DBHelper dbHelper;
    BoxAdapter boxAdapter;
 static Activity getactivity;

    public static FragmentManager fm;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fTrans = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                  //  Fragment_tab.onResume();
                    setTitle("Отчёт по дням");

                    fTrans.replace(R.id.frgmCont, new fragment_tab());
                    fTrans.commit();

                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);



                    setTitle("Конверты");
                    fTrans.replace(R.id.frgmCont, fragment_dohod);
                 //   fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);

                    setTitle("Статистика");
                    fTrans.replace(R.id.frgmCont, frag2);
                  //  fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;

            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getSupportFragmentManager();
        getactivity=this;
        setContentView(R.layout.activity_main);

      //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fTrans = getFragmentManager().beginTransaction();
            frag2 = new statistics();
            fragment_dohod = new fragment_dohod();
            New_dkr = new new_dkr();
      //  Fragment_tab = new fragment_tab();
dbHelper = new DBHelper(this);


        setTitle("Отчёт по дням");

        fTrans.replace(R.id.frgmCont, new fragment_tab());
        fTrans.commit();


        //fragment_tab = new fragment_tab();


     //   Intent intent = new Intent(getactivity, TABBED.class);

     //   getactivity.startActivity(intent);

    }







    public static void loadcart(Context context){

        DBHelper dbHelper = new DBHelper(context);
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

        DBHelper dbHelper = new DBHelper(context);
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

        DBHelper dbHelper = new DBHelper(context);
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






    @Override
    protected void onResume() {
        super.onResume();

      //  loadcart(this);
     //   fragment_dohod = new fragment_dohod();
           //  Toast.makeText(getApplicationContext(),
          //         "Пора покормить кота!", Toast.LENGTH_SHORT).show();
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

/*

    public class Product {

        String komment;
        int suuma_doh;
        int suuma_fakt;
        int id;
        int  name_doh;
        int  new_plus;


        Product(String _komment, int _suuma_doh, int _suuma_fakt, int _id, int _name_doh, int _new_plus) {
            komment = _komment;
            suuma_doh = _suuma_doh;
            suuma_fakt = _suuma_fakt;
            id = _id;
            name_doh = _name_doh;
            new_plus=_new_plus;
        }
    }

*/


}

