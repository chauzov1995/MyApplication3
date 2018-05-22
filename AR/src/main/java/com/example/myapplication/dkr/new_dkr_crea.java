package com.example.myapplication.dkr;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DB_sql;
import com.example.myapplication.MyDialogFragment;
import com.example.myapplication.R;
import com.example.myapplication.dohod.dohod_redak;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class new_dkr_crea extends AppCompatActivity {

    Calendar dateAndTime = Calendar.getInstance();
    public static int kuda_intent;
    int name_doh_intent, postoyan_intent, summa_intent;
    String date, komment_intent;
    SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dkr_crea);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //      getSupportActionBar().setDisplayShowHomeEnabled(true);


        kuda_intent = getIntent().getIntExtra("id", 0);
        name_doh_intent = getIntent().getIntExtra("name_doh", 0);
        postoyan_intent = getIntent().getIntExtra("postoyan", 0);
        summa_intent = getIntent().getIntExtra("summa", 0);
        komment_intent = getIntent().getStringExtra("komment");


        setInitialDateTime();
        // MainActivity.load_dkr_cart(this);


        // dkr_konv_adapter  boxAdapter1 = new dkr_konv_adapter(this, MainActivity.dkr_kart, this, date);
        // настраиваем список
        //  GridView dkr_view = (GridView) findViewById(R.id.dkr_view);

        Button crea_dkr = (Button) findViewById(R.id.crea_dkr);
        Button crea_dkr_to = (Button) findViewById(R.id.crea_dkr_to);
        Button crea_dkr_kal = (Button) findViewById(R.id.crea_dkr_kal);
        Button del_dkr = (Button) findViewById(R.id.del_dkr);
        Button crea_dkr_save = (Button) findViewById(R.id.crea_dkr_save);


        crea_dkr_save.setVisibility(View.GONE);
        del_dkr.setVisibility(View.GONE);
        // dkr_view.setAdapter(boxAdapter1);


        crea_dkr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                //    date= dateAndTime.get(Calendar.YEAR)+"."+(dateAndTime.get(Calendar.MONTH)+1)+"."+(dateAndTime.get(Calendar.DAY_OF_MONTH)-1);

                create_dkr();
            }
        });
        crea_dkr_to.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                dateAndTime.add(Calendar.DATE, -1);
                setInitialDateTime();
                //   date= dateAndTime.get(Calendar.YEAR)+"."+(dateAndTime.get(Calendar.MONTH)+1)+"."+(dateAndTime.get(Calendar.DAY_OF_MONTH)-1);
                create_dkr();

            }
        });
        crea_dkr_kal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {


                new DatePickerDialog(new_dkr_crea.this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();


            }
        });


    }


    void create_dkr() {

        DB_sql dbHelper = new DB_sql(new_dkr_crea.this);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);

//внимание потом тут искать чтобы по календарю

// до сюда


        int kuda = kuda_intent;
        int summa = Integer.parseInt(editText.getText().toString());
        String komment = editText2.getText().toString();
        String data_fakt = date;
        int name_doh = name_doh_intent;
        int postoyan = postoyan_intent;

        db.execSQL("INSERT INTO `an_dkr_hist`" +
                " ( `kuda`, `summa`, `komment`, `data_fakt`, `visible`, `postoyan`, name_dohod)" +
                " VALUES" +
                " ( '" + kuda + "', '" + summa + "', '" + komment + "', '" + data_fakt + "', 0, '" + postoyan + "', '" + name_doh + "')");


        db.execSQL("UPDATE `an_dohod` SET" +
                " `summa_fakt`=summa_fakt+'" + summa + "'" +
                " WHERE id='" + kuda + "'");

//определим текущий ли месяц
        boolean istekmes = false;
        String[] tekmesprov = data_fakt.split("\\.");

        String now = ft.format(Calendar.getInstance().getTime());//.get(Calendar.YEAR)+"."+(Calendar.getInstance().get(Calendar.MONTH)+1);
        if ((tekmesprov[0] + "." + tekmesprov[1]) == now) istekmes = true;
        String vstavka = "";
        switch (name_doh) {
            case 1:
                vstavka = "`balance`=balance+'" + summa + "'";
                if (istekmes) vstavka += ", vsego_mes_zarab=vsego_mes_zarab+'" + summa + "'";
                break;
            case 2:
                vstavka = "`balance`=balance-'" + summa + "'";
                if (istekmes) vstavka += ", vsego_mes_potr=vsego_mes_potr+'" + summa + "'";
                break;
        }

        db.execSQL("UPDATE `an_users` SET " + vstavka);


        finish();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
        }
        if (id == R.id.action_delete) {
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            FragmentManager manager = getSupportFragmentManager();
            //myDialogFragment.show(manager, "dialog");

            FragmentTransaction transaction = manager.beginTransaction();
            myDialogFragment.show(transaction, "dialog");


        }
        if (id == R.id.action_settings) {


            finish();


            Toast.makeText(getApplicationContext(),
                    "Пора покормить кота!" + getIntent().getStringExtra("komment"), Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(this, dohod_redak.class);
            intent.putExtra("id", getIntent().getIntExtra("id", 0));
            intent.putExtra("summa", getIntent().getIntExtra("summa", 0));
            intent.putExtra("komment", getIntent().getStringExtra("komment"));
            intent.putExtra("postoyan", getIntent().getIntExtra("postoyan", 0));
            intent.putExtra("name_doh", getIntent().getIntExtra("name_doh", 0));
            startActivity(intent);


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setInitialDateTime() {

        //  textView3.setText( DateUtils.formatDateTime(getActivity(),
        //          dateAndTime.getTimeInMillis(),
        //         DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        // int seconds = dateAndTime.get(Calendar.SECOND);
        // date=DateUtils.formatDateTime(getActivity(), dateAndTime.getTimeInMillis(),
        //    DateUtils.FORMAT_NUMERIC_DATE|DateUtils.FORMAT_SHOW_YEAR);
        // date= dateAndTime.get(Calendar.YEAR)+"."+(dateAndTime.get(Calendar.MONTH)+1)+"."+dateAndTime.get(Calendar.DAY_OF_MONTH);

        date = ft.format(dateAndTime.getTime());
    }


    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();

            create_dkr();
        }
    };


}
