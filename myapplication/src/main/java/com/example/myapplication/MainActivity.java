package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction fTrans;
    com.example.myapplication.frag2 frag2;
    fragment_dohod fragment_dohod;
    new_dkr New_dkr;
    public  static ArrayList<Product> dkr_kart = new ArrayList<Product>();
  public  static ArrayList<Product> products_doh = new ArrayList<Product>();
    public  static ArrayList<Product> products_rash = new ArrayList<Product>();
    public  static ArrayList<Product> products_zel = new ArrayList<Product>();
    DBHelper dbHelper;
    BoxAdapter boxAdapter;





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fTrans = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fTrans.replace(R.id.frgmCont, New_dkr);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);
                    fTrans.replace(R.id.frgmCont, frag2);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);




                    fTrans.replace(R.id.frgmCont, fragment_dohod);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fTrans = getFragmentManager().beginTransaction();
            frag2 = new frag2();
            fragment_dohod = new fragment_dohod();
            New_dkr = new new_dkr();
dbHelper = new DBHelper(this);



        fTrans.replace(R.id.frgmCont, New_dkr);
        fTrans.addToBackStack(null);
       fTrans.commit();




    }




    public static void loadcart(Context context){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        products_doh.clear();
        products_rash.clear();
        products_zel.clear();

        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("an_dohod", null, "visible == ?", new String[] { "0" }, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int summa_dohod = c.getColumnIndex("summa_dohod");
            int summa_fakt = c.getColumnIndex("summa_fakt");
            int komment = c.getColumnIndex("komment");
            int name_dohod = c.getColumnIndex("name_dohod");

            do {

switch (c.getInt(name_dohod)){
    case 1:
        products_doh.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0));
        break;
    case 2:
        products_rash.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0));
        break;
    case 3:
        products_zel.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0));
        break;
}

                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }

        products_doh.add(new Product("Создать+", 0,
                0, 0, 1, 1));
        products_rash.add(new Product("Создать+", 0,
                0, 0, 2, 1));
        products_zel.add(new Product("Создать+", 0,
                0, 0, 3, 1));
        c.close();



    }


    public static void load_dkr_cart(Context context){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dkr_kart.clear();


        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("an_dohod", null, "visible == ?", new String[] { "0" }, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("id");
            int summa_dohod = c.getColumnIndex("summa_dohod");
            int summa_fakt = c.getColumnIndex("summa_fakt");
            int komment = c.getColumnIndex("komment");
            int name_dohod = c.getColumnIndex("name_dohod");

            do {


                        dkr_kart.add(new Product(c.getString(komment), c.getInt(summa_dohod),
                                c.getInt(summa_fakt), c.getInt(id), c.getInt(name_dohod), 0));


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
        fragment_dohod = new fragment_dohod();
             Toast.makeText(getApplicationContext(),
                   "Пора покормить кота!", Toast.LENGTH_SHORT).show();
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
                    "  `id_clienta` int(11) NOT NULL,\n" +
                    "  `name_dohod` int(11) NOT NULL ,\n" +
                    "  `summa_dohod` decimal(11,0) NOT NULL,\n" +
                    "  `summa_fakt` decimal(10,0) NOT NULL,\n" +
                    "  `komment` varchar(255) NOT NULL,\n" +
                    "  `visible` int(11) NOT NULL ,\n" +
                    "  `postoyan` int(11) NOT NULL\n" +
                    ");" +
                    "CREATE TABLE `an_dkr_hist` (\n" +
                    "  `id` int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "  `id_clienta` int(11) NOT NULL,\n" +
                    "  `otkuda` int(11) NOT NULL,\n" +
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

            Log.d("myLogs", "--- onCreate database ---");
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

