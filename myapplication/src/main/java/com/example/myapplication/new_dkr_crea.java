package com.example.myapplication;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
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


        String date= getIntent().getStringExtra("date");

        MainActivity.load_dkr_cart(this);




        dkr_konv_adapter  boxAdapter1 = new dkr_konv_adapter(this, MainActivity.dkr_kart, this, date);
        // настраиваем список
        GridView dkr_view = (GridView) findViewById(R.id.dkr_view);
        dkr_view.setAdapter(boxAdapter1);






    }




}
