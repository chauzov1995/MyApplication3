package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;

public class statistics extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=  inflater.inflate(R.layout.statistics, null);






        return v;
    }


    @Override
    public void onResume()
    {
        super.onResume();


        DB_sql dbHelper = new DB_sql(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();





        Cursor c = db.rawQuery("select * from `an_users`", null);


        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        c.moveToFirst();

        Calendar calendar =  Calendar.getInstance();
        int maxDays = calendar.getActualMaximum( Calendar.DAY_OF_MONTH );

        // определяем номера столбцов по имени в выборке
        int dohod = c.getColumnIndex("dohod");
        int rashod = c.getColumnIndex("rashod");
        int zel = c.getColumnIndex("zel");
        int vsego_mes_potr = c.getColumnIndex("vsego_mes_potr");
        int vsego_mes_zarab = c.getColumnIndex("vsego_mes_zarab");
        int balance = c.getColumnIndex("balance");
        int limitmes=c.getInt(dohod)-c.getInt(rashod)-c.getInt(zel);
        int limitned=limitmes/maxDays*7;

        String[] names = {
                    "Баланс: "+c.getInt(balance),
                    "Доходы: "+c.getInt(dohod),
                    "Постоянные расходы: "+c.getInt(rashod),
                    "Откладываем на цель: "+c.getInt(zel),
                    "Потрачено мес: "+c.getInt(vsego_mes_potr),
                    "Заработано мес: "+c.getInt(vsego_mes_zarab),
                    "Лимит на месяц: "+limitmes,
                    "Лимит на неделю: "+limitned

                    };





        c.close();




        ListView listsettings = (ListView) getActivity().findViewById(R.id.listsettings);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        listsettings.setAdapter(adapter);




    }
}