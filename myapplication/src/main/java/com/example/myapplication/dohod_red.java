package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nikita on 19.11.2017.
 */

public class dohod_red extends AppCompatActivity {
  Activity tecactivity;
    int id;
    EditText doh_red_komment, doh_red_summa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dohod_red);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity=this;
         doh_red_komment=(EditText) findViewById(R.id.doh_red_komment);
         doh_red_summa=(EditText) findViewById(R.id.doh_red_summa);
        Button doh_red_btn=(Button) findViewById(R.id.doh_red_btn);
        Button doh_del_btn=(Button) findViewById(R.id.doh_del_btn);



         id= getIntent().getIntExtra("id", 0);

       doh_red_komment.setText(getIntent().getStringExtra("komment"));
        doh_red_summa.setText(Integer.toString(getIntent().getIntExtra("summa",0))); //NULL POINTER EXCEPTION

     //   Bundle bundle = this.getArguments();
   //     doh_red_komment.setText(bundle.getString("komment"));


        doh_red_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(tecactivity);
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                // подготовим значения для обновления
                cv.put("komment", doh_red_komment.getText().toString());
                cv.put("summa_dohod", Integer.parseInt(doh_red_summa.getText().toString()));
                // обновляем по id
                int updCount = db.update("an_dohod", cv, "id = ?",
                        new String[] { Integer.toString(id) });


              // onBackPressed();// возврат на предыдущий activity
               finish();

            }
        });

        doh_del_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {


                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(tecactivity);
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                // подготовим значения для обновления
                cv.put("visible", 1);

                // обновляем по id
                int updCount = db.update("an_dohod", cv, "id = ?",
                        new String[] { Integer.toString(id) });



                finish();
               // onBackPressed();// возврат на предыдущий activity




            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id =item.getItemId();

        if(id==android.R.id.home){

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}