package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by nikita on 19.11.2017.
 */

public class new_dohod extends AppCompatActivity {

    int name_dohod;
    Activity tecactivity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dohod);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity=this;
        name_dohod= getIntent().getIntExtra("name_dohod", 0);



        Button button5=(Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(tecactivity);
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                EditText editText4=(EditText) findViewById(R.id.editText4);
                EditText editText5=(EditText) findViewById(R.id.editText5);

                cv.put("id_clienta", 1);
                cv.put("name_dohod", name_dohod);
                cv.put("summa_dohod", Integer.parseInt(editText5.getText().toString()));
                cv.put("summa_fakt", 0);
                cv.put("komment", editText4.getText().toString());
                cv.put("visible", 0);
                cv.put("postoyan", 0);

                // вставляем запись и получаем ее ID
                long rowID = db.insert("an_dohod", null, cv);


                cv.clear();

                String vstavka="";
                switch(name_dohod){
                    case 1:
                        vstavka="`dohod`=dohod+'"+editText5.getText()+"'";
                        break;
                    case 2:
                       // if($postoyan_doh==1){
                            vstavka="`rashod`=rashod+'"+editText5.getText()+"'";
                      //  }
                        break;
                    case 3:
                        vstavka="`zel`=zel+'"+editText5.getText()+"'";
                        break;
                }


                db.execSQL("UPDATE `an_users` SET "+vstavka);

            //    MainActivity.loadcart(tecactivity);
             //   tecactivity.onBackPressed();// возврат на предыдущий activity
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
