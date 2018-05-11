package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.Calendar;


public class dkr_konv_adapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Dohod> objects;
    Activity getactivity;
String date;
    com.example.myapplication.new_dohod new_Dohod;


    DB_sql dbHelper;

    dkr_konv_adapter(Context context, ArrayList<Dohod> products, Activity _getactivity, String _date) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getactivity=_getactivity;
        date=_date;
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

        final Dohod p = getProduct(position);


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

        dkr_item_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                DB_sql dbHelper = new DB_sql(getactivity);
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                EditText editText=(EditText) getactivity.findViewById(R.id.editText);
                EditText editText2=(EditText) getactivity.findViewById(R.id.editText2);


                int kuda=p.id;
                int summa=Integer.parseInt(editText.getText().toString());
                String komment=editText2.getText().toString();
                String data_fakt=date;
                int name_doh=p.name_doh;
                int postoyan=p.postoyan;

                db.execSQL("INSERT INTO `an_dkr_hist`" +
                    " ( `kuda`, `summa`, `komment`, `data_fakt`, `visible`, `postoyan`, name_dohod)" +
                    " VALUES" +
                    " ( '"+kuda+"', '"+summa+"', '"+komment+"', '"+data_fakt+"', 0, '"+postoyan+"', '"+name_doh+"')");



                db.execSQL("UPDATE `an_dohod` SET" +
                        " `summa_fakt`=summa_fakt+'"+summa+"'" +
                        " WHERE id='"+kuda+"'");

//определим текущий ли месяц
               boolean istekmes=false;
               String[] tekmesprov=data_fakt.split("\\.");

                String now=Calendar.getInstance().get(Calendar.YEAR)+"."+(Calendar.getInstance().get(Calendar.MONTH)+1);
                if((tekmesprov[0]+"."+tekmesprov[1])==now)istekmes=true;
                String vstavka="";
                switch(name_doh){
                    case 1:
                        vstavka="`balance`=balance+'"+summa+"'";
                        if(istekmes) vstavka+=", vsego_mes_zarab=vsego_mes_zarab+'"+summa+"'";
                        break;
                    case 2:
                        vstavka="`balance`=balance-'"+summa+"'";
                        if(istekmes) vstavka+=", vsego_mes_potr=vsego_mes_potr+'"+summa+"'";
                        break;
                }

                db.execSQL("UPDATE `an_users` SET "+vstavka);





                getactivity.finish();
            }
        });






        return view;
    }

    // товар по позиции
    Dohod getProduct(int position) {
        return ((Dohod) getItem(position));
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