package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainActivity.DBHelper;

import java.util.ArrayList;



public class dkr_konv_adapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    Activity getactivity;

    com.example.myapplication.new_dohod new_Dohod;


    DBHelper dbHelper;

    dkr_konv_adapter(Context context, ArrayList<Product> products, Activity _getactivity) {
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

        final Product p = getProduct(position);


            if (view == null) {
                view = lInflater.inflate(R.layout.item_dkr, parent, false);
            }
            Button dkr_item_btn=  (Button) view.findViewById(R.id.dkr_item_btn);
        dkr_item_btn.setText(p.komment);
switch (p.name_doh) {
        case 1:
        dkr_item_btn.setBackgroundColor(Color.parseColor("#99cc55")) ;
        break;
        case 2:
        dkr_item_btn.setBackgroundColor(Color.parseColor("#ff9966"));
        break;
}
        return view;
    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {



        return (getProduct(position).new_plus == 1) ? 0 : 1;
    }




}