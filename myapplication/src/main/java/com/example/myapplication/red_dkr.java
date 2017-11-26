package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.Calendar;

/**
 * Created by nikita on 25.11.2017.
 */

public class red_dkr extends AppCompatActivity {
    Activity tecactivity;
    int id,suuma_intent,id_intent;
    String komment_intent,data_fact_intent;
    EditText doh_red_komment, doh_red_summa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dkr_crea);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity = this;




        suuma_intent=getIntent().getIntExtra("suuma", 0);
          komment_intent=getIntent().getStringExtra("komment");
         id_intent=getIntent().getIntExtra("id", 0);
        data_fact_intent=getIntent().getStringExtra("data_fact");



        Button crea_dkr = (Button) findViewById(R.id.crea_dkr);
        Button crea_dkr_to = (Button) findViewById(R.id.crea_dkr_to);
        Button crea_dkr_kal = (Button) findViewById(R.id.crea_dkr_kal);
        Button del_dkr = (Button) findViewById(R.id.del_dkr);
            EditText editText=(EditText) findViewById(R.id.editText);
              EditText editText2=(EditText) findViewById(R.id.editText2);


        editText.setText(Integer.toString(suuma_intent));
        editText2.setText(komment_intent);

        crea_dkr.setVisibility(View.GONE);
        crea_dkr_to.setVisibility(View.GONE);
        crea_dkr_kal.setVisibility(View.GONE);


        del_dkr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

               MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(red_dkr.this);
               ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                //    EditText editText=(EditText) getactivity.findViewById(R.id.editText);
                //       EditText editText2=(EditText) getactivity.findViewById(R.id.editText2);
                db.execSQL("UPDATE `an_dkr_hist` SET `visible`=1 WHERE" +
                        " id='"+id_intent+"'");


                Cursor c = db.rawQuery("SELECT * FROM `an_dkr_hist` WHERE id='"+id_intent+"' ", null);

                c.moveToFirst();


                int summa = c.getInt(c.getColumnIndex("summa"));
                int kuda = c.getInt(c.getColumnIndex("kuda"));
                int name_dohod = c.getInt(c.getColumnIndex("name_dohod"));
                String data_fakt = c.getString(c.getColumnIndex("data_fakt"));


                db.execSQL("UPDATE `an_dohod` SET" +
                        " `summa_fakt`=summa_fakt-'"+summa+"'" +
                        " WHERE id='"+kuda+"'");


                String[] tekmesprov=data_fakt.split("\\.");

                boolean istekmes=false;
                String now= Calendar.getInstance().get(Calendar.YEAR)+"."+(Calendar.getInstance().get(Calendar.MONTH)+1);
                if((tekmesprov[0]+"."+tekmesprov[1])==now)istekmes=true;

String vstavka="";
                switch(name_dohod){
                    case 1:
                        vstavka="`balance`=balance-'"+summa+"'";
                        if(istekmes)vstavka+=", vsego_mes_zarab=vsego_mes_zarab-'"+summa+"'";
                        break;
                    case 2:
                        vstavka="`balance`=balance+'"+summa+"'";
                        if(istekmes)vstavka+=", vsego_mes_potr=vsego_mes_potr-'"+summa+"'";
                        break;
                }

                db.execSQL("UPDATE `an_users` SET "+vstavka);


                // подготовим значения для обновления
               // cv.put("visible", 1);

                // обновляем по id
                //     int updCount = db.update("an_dkr_hist", cv, "id = ?",
                //               new String[] { Integer.toString(p.id) });

                //onResume();

                finish();
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
