package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

public class new_dkr_crea extends AppCompatActivity {

    Calendar dateAndTime= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dkr_crea);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String date= getIntent().getStringExtra("date");

        MainActivity.load_dkr_cart(this);




        dkr_konv_adapter  boxAdapter1 = new dkr_konv_adapter(this, MainActivity.dkr_kart, this, date);
        // настраиваем список
        GridView dkr_view = (GridView) findViewById(R.id.dkr_view);
        Button del_dkr = (Button) findViewById(R.id.del_dkr);
        del_dkr.setVisibility(View.GONE);
        dkr_view.setAdapter(boxAdapter1);






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
