package com.example.myapplication;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.myapplication.MainActivity.DBHelper;

import java.util.ArrayList;

public class frag3 extends Fragment {

    TextView text4;
   Button button,     button2;
    DBHelper dbHelper;
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag4, null);

        //Context thist=MainActivity.context;
        dbHelper = new DBHelper(getActivity());
       text4 =  (TextView) v.findViewById(R.id.text4);
       text4.setText("промотер");


        button=(Button)  v.findViewById(R.id.button2);
        button2=(Button)  v.findViewById(R.id.button3);


     //   adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, R.id.tvText, data);
       // gvMain = (GridView) v.findViewById(R.id.gvMain);
     //   gvMain.setAdapter(adapter);



        // создаем адаптер
       fillData();


        //products.add(new Product("Создать+" , 0,
      //          0, 0));


        boxAdapter = new BoxAdapter(getActivity(), products);

        // настраиваем список
        GridView lvMain = (GridView) v.findViewById(R.id.gvMain);
        lvMain.setAdapter(boxAdapter);



        button.setOnClickListener(new OnClickListener() {
            public void onClick(View r) {


                // создаем объект для данных
                ContentValues cv = new ContentValues();

                // получаем данные из полей ввода
                String name = "asdasd";
                String email = "asdasda231312";

                // подключаемся к БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();



                // подготовим данные для вставки в виде пар: наименование столбца - значение

                cv.put("name", name);
                cv.put("email", email);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv);


            }
        });
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View r) {


                // создаем объект для данных
                ContentValues cv = new ContentValues();

                // получаем данные из полей ввода
                String name = "asdasd";
                String email = "asdasda231312";

                // подключаемся к БД
                SQLiteDatabase db = dbHelper.getWritableDatabase();




                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");

                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        text4.setText(
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex));
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                   // text4.setText( "0 rows");
                c.close();


            }
        });



        return v;
    }
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            products.add(new Product("Product " + i, i * 1000,
                    200, i));
        }
    }


    public class Product {

        String komment;
        int suuma_doh;
        int suuma_fakt;
        int id;


        Product(String _komment, int _suuma_doh, int _suuma_fakt, int _id) {
            komment = _komment;
            suuma_doh = _suuma_doh;
            suuma_fakt = _suuma_fakt;
            id = _id;
        }
    }


}