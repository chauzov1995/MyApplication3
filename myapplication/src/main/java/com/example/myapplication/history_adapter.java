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
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;



    public class history_adapter extends BaseAdapter {
        Context ctx;
        LayoutInflater lInflater;
        ArrayList<History> objects;
        Activity getactivity;

        com.example.myapplication.new_dohod new_Dohod;


        DB_sql dbHelper;

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

            TextView textView7=  (TextView) view.findViewById(R.id.textView7);
            TextView textView8=  (TextView) view.findViewById(R.id.textView8);
            TextView textView9=  (TextView) view.findViewById(R.id.textView9);
            LinearLayout histitem=  (LinearLayout) view.findViewById(R.id.histitem);

           // Button button=  (Button) view.findViewById(R.id.button);
            textView8.setText(p.komment);
            textView7.setText(p.nazv_doh);

            switch (p.name_doh){
                case 1:
                    textView9.setTextColor(0xFF88bb55);
                textView9.setText("+"+p.suuma + " р.");
            break;
                case 2:
                    textView9.setTextColor(0xFFff5555);
                    textView9.setText("-"+p.suuma + " р.");
                    break;
            }

            histitem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View r) {


                    Intent intent = new Intent(getactivity, red_dkr.class);


                    intent.putExtra("suuma", p.suuma);
                    intent.putExtra("komment", p.komment);
                    intent.putExtra("id", p.id);
                    intent.putExtra("data_fact", p.data_fact);


                    getactivity.startActivity(intent);

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