package com.example.myapplication;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nikita on 19.11.2017.
 */

public class dohod_red extends Fragment {
    View v;
    int id;
    EditText doh_red_komment, doh_red_summa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v =inflater.inflate(R.layout.dohod_red, null);

         doh_red_komment=(EditText) v.findViewById(R.id.doh_red_komment);
         doh_red_summa=(EditText) v.findViewById(R.id.doh_red_summa);
        Button doh_red_btn=(Button) v.findViewById(R.id.doh_red_btn);
        Button doh_del_btn=(Button) v.findViewById(R.id.doh_del_btn);
         id=getArguments().getInt("id");

       doh_red_komment.setText(getArguments().getString("komment")); //NULL POINTER EXCEPTION
        doh_red_summa.setText(Integer.toString(getArguments().getInt("summa"))); //NULL POINTER EXCEPTION

     //   Bundle bundle = this.getArguments();
   //     doh_red_komment.setText(bundle.getString("komment"));


        doh_red_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                // подготовим значения для обновления
                cv.put("komment", doh_red_komment.getText().toString());
                cv.put("summa_dohod", Integer.parseInt(doh_red_summa.getText().toString()));
                // обновляем по id
                int updCount = db.update("an_dohod", cv, "id = ?",
                        new String[] { Integer.toString(id) });

                MainActivity.loadcart(getActivity());
                getActivity().onBackPressed();// возврат на предыдущий activity


            }
        });

        doh_del_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {


                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                // подготовим значения для обновления
                cv.put("visible", 1);

                // обновляем по id
                int updCount = db.update("an_dohod", cv, "id = ?",
                        new String[] { Integer.toString(id) });

                MainActivity.loadcart(getActivity());
                getActivity().onBackPressed();// возврат на предыдущий activity




            }
        });
        return v;
    }
}