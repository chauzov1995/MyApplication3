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

/**
 * Created by nikita on 19.11.2017.
 */

public class new_dohod extends Fragment {
    View v;
    int name_dohod;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.new_dohod, null);

         name_dohod=getArguments().getInt("name_dohod");


        Button button5=(Button) v.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                EditText editText4=(EditText) v.findViewById(R.id.editText4);
                EditText editText5=(EditText) v.findViewById(R.id.editText5);

                cv.put("id_clienta", 1);
                cv.put("name_dohod", name_dohod);
                cv.put("summa_dohod", Integer.parseInt(editText5.getText().toString()));
                cv.put("summa_fakt", 0);
                cv.put("komment", editText4.getText().toString());
                cv.put("visible", 0);
                cv.put("postoyan", 0);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("an_dohod", null, cv);

                MainActivity.loadcart(getActivity());
                getActivity().onBackPressed();// возврат на предыдущий activity

            }
        });
        return v;
    }




}
