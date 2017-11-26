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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nikita on 19.11.2017.
 */

public class new_dohod extends AppCompatActivity {

    int name_dohod;
    Activity tecactivity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dohod_red);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity=this;
        name_dohod= getIntent().getIntExtra("name_dohod", 0);


        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBox);
        TextView tb_red_name=(TextView) findViewById(R.id.tb_red_name);
        TextView tb_red_summa=(TextView) findViewById(R.id.tb_red_summa);
        Button doh_del_btn=(Button) findViewById(R.id.doh_del_btn);
        Button button5=(Button) findViewById(R.id.doh_red_btn);

        button5.setText("Создать");
        doh_del_btn.setVisibility(View.GONE);
        checkBox.setVisibility(View.GONE);
        switch(name_dohod) {
            case 1:
                tb_red_name.setText("Название дохода");
                tb_red_summa.setText("Сколько планируете получать?");
                break;
            case 2:
                tb_red_name.setText("На что планируете тратить?");
                tb_red_summa.setText("Сколько планируете тратить?");





                break;
            case 3:
                tb_red_name.setText("На что копим?");
                tb_red_summa.setText("Сколько планируете откладывать средств ежемесячно?");
                break;


        }



        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(tecactivity);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


                EditText editText4=(EditText) findViewById(R.id.doh_red_komment);
                EditText editText5=(EditText) findViewById(R.id.doh_red_summa);

                int postoyan_post=0;
                if(((CheckBox) findViewById(R.id.checkBox)).isChecked()) postoyan_post=1;



                db.execSQL("INSERT INTO `an_dohod`" +
                        "( `name_dohod`, `summa_dohod`, `summa_fakt`, `komment`, `visible`, `postoyan`) VALUES" +
                        " ('"+name_dohod+"','"+Integer.parseInt(editText5.getText().toString())+"',0,'"+editText4.getText().toString()+"',0,'"+postoyan_post+"')");






                String vstavka="";
                switch(name_dohod){
                    case 1:
                        vstavka="`dohod`=dohod+'"+editText5.getText()+"'";
                        break;
                    case 2:
                        if(postoyan_post==1){
                            vstavka="`rashod`=rashod+'"+editText5.getText()+"'";
                       }else {
                            vstavka="`rashod`=rashod";
                                            }
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
