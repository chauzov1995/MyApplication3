package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.MainActivity.DBHelper;

import java.util.ArrayList;



    public class history_adapter extends BaseAdapter {
        Context ctx;
        LayoutInflater lInflater;
        ArrayList<History> objects;
        Activity getactivity;

        com.example.myapplication.new_dohod new_Dohod;


        MainActivity.DBHelper dbHelper;

        history_adapter(Context context, ArrayList<History> products, Activity _getactivity) {
            ctx = context;
            objects = products;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            getactivity=_getactivity;
        }

        // кол-во элементов
        @Override
        public int getCount() {
            return objects.size();
        }

        // элемент по позиции
        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        // id по позиции
        @Override
        public long getItemId(int position) {
            return position;
        }

        // пункт списка
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // используем созданные, но не используемые view
            View view = convertView;

            final History p = getProduct(position);


            if (view == null) {
                view = lInflater.inflate(R.layout.hist_item, parent, false);
            }
            TextView textView8=  (TextView) view.findViewById(R.id.textView8);
            TextView textView9=  (TextView) view.findViewById(R.id.textView9);
            Button button=  (Button) view.findViewById(R.id.button);
            textView8.setText(p.komment);
            textView9.setText(p.suuma+ " р.");

            /*
            switch (p.) {
                case 1:
                    dkr_item_btn.setBackgroundColor(Color.parseColor("#99cc55")) ;
                    break;
                case 2:
                    dkr_item_btn.setBackgroundColor(Color.parseColor("#ff9966"));
                    break;
            }
&8*&^*&*/
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View r) {

                    MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getactivity);
                    ContentValues cv = new ContentValues();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();


                //    EditText editText=(EditText) getactivity.findViewById(R.id.editText);
             //       EditText editText2=(EditText) getactivity.findViewById(R.id.editText2);




                    // подготовим значения для обновления
                    cv.put("visible", 1);

                    // обновляем по id
                    int updCount = db.update("an_dkr_hist", cv, "id = ?",
                            new String[] { Integer.toString(p.id) });

                    //onResume();


                }
            });






            return view;
        }

        // товар по позиции
        History getProduct(int position) {
            return ((History) getItem(position));
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }





    }