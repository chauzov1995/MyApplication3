package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by nikita on 19.11.2017.
 */

public class new_dohod extends AppCompatActivity {


    int name_dohod = 1;
    Activity tecactivity;
    Context thiscont;


    TextView tb_red_name;
    TextView tb_red_summa;
    CheckBox checkBox;
    Button button5;
    EditText editText4;
    EditText editText5;
    Spinner spinner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dohod_red);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //     getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity = this;
        thiscont=this;
        //  name_dohod= getIntent().getIntExtra("name_dohod", 0);


        tb_red_name = (TextView) findViewById(R.id.tb_red_name);
        tb_red_summa = (TextView) findViewById(R.id.tb_red_summa);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        button5 = (Button) findViewById(R.id.doh_red_btn);
        editText4 = (EditText) findViewById(R.id.doh_red_komment);
        editText5 = (EditText) findViewById(R.id.doh_red_summa);
        spinner = (Spinner) findViewById(R.id.spinner);


        button5.setText("Создать");




        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String) parent.getItemAtPosition(position);
                checkBox.setVisibility(View.GONE);
                if (item.equals( "Расход")) {
                   checkBox.setVisibility(View.VISIBLE);
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                DB_sql dbHelper = new DB_sql(tecactivity);
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                String selected = spinner.getSelectedItem().toString();
                switch (selected) {
                    case "Доход":
                        name_dohod = 1;
                        break;
                    case "Расход":
                        name_dohod = 2;
                        break;
                    case "Цель":
                        name_dohod = 3;
                        break;
                }


                int postoyan_post = 0;
                if (((CheckBox) findViewById(R.id.checkBox)).isChecked()) postoyan_post = 1;


                db.execSQL("INSERT INTO `an_dohod`" +
                        "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                        " ('" + name_dohod + "','" + Integer.parseInt(editText5.getText().toString()) + "',0,'" + editText4.getText().toString() + "',0,'" + postoyan_post + "')");


                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
