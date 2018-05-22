package com.example.myapplication.dohod;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.DB_sql;
import com.example.myapplication.R;

/**
 * Created by nikita on 19.11.2017.
 */

public class dohod_red extends AppCompatActivity {
    Activity tecactivity;
    int id, postoyan_intent, name_dohod_intent;
    EditText doh_red_komment, doh_red_summa;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dohod_red);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //  mToolbar.setIcon(R.drawable.ic_launcher);
        //   mToolbar.setLogo(R.drawable.ic_dashboard_black_24dp);


        //  TabLayout tabs = (TabLayout) findViewById(R./id.tabs);
        //  tabs.addTab(tabs.newTab().setText("Tab 1"));
        //  tabs.addTab(tabs.newTab().setText("Tab 2"));
        // /  tabs.addTab(tabs.newTab().setText("Tab 3"));
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        tecactivity = this;
        doh_red_komment = (EditText) findViewById(R.id.doh_red_komment);
        doh_red_summa = (EditText) findViewById(R.id.doh_red_summa);
        Button doh_red_btn = (Button) findViewById(R.id.doh_red_btn);
        //    Button doh_del_btn=(Button) findViewById(R.id.doh_del_btn);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        TextView tb_red_name = (TextView) findViewById(R.id.tb_red_name);
        TextView tb_red_summa = (TextView) findViewById(R.id.tb_red_summa);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        id = getIntent().getIntExtra("id", 0);
        postoyan_intent = getIntent().getIntExtra("postoyan", 0);
        name_dohod_intent = getIntent().getIntExtra("name_doh", 0);

        doh_red_komment.setText(getIntent().getStringExtra("komment"));
        doh_red_summa.setText(Integer.toString(getIntent().getIntExtra("summa", 0))); //NULL POINTER EXCEPTION


        if (postoyan_intent == 1) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        //   Bundle bundle = this.getArguments();
        //     doh_red_komment.setText(bundle.getString("komment"));

        checkBox.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        switch (name_dohod_intent) {
            case 1:
                tb_red_name.setText("Название дохода");
                tb_red_summa.setText("Сколько планируете получать?");
                break;
            case 2:
                tb_red_name.setText("На что планируете тратить?");
                tb_red_summa.setText("Сколько планируете тратить?");
                checkBox.setVisibility(View.VISIBLE);


                break;
            case 3:
                tb_red_name.setText("На что копим?");
                tb_red_summa.setText("Сколько планируете откладывать средств ежемесячно?");
                break;


        }

        doh_red_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
//обвление
                DB_sql dbHelper = new DB_sql(tecactivity);
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                Cursor c = db.rawQuery("select * from `an_dohod` Where id='" + String.valueOf(id) + "'", null);

                c.moveToFirst();

                // определяем номера столбцов по имени в выборке
                int postoyan_post = 0;
                if (((CheckBox) findViewById(R.id.checkBox)).isChecked()) postoyan_post = 1;

                int summa_dohod_post = Integer.parseInt(doh_red_summa.getText().toString());
                String komment_post = doh_red_komment.getText().toString();
                int name_dohod = c.getInt(c.getColumnIndex("name_dohod"));
                int summa_dohod = c.getInt(c.getColumnIndex("summa_dohod"));
                int postoyan = c.getInt(c.getColumnIndex("postoyan"));
                int korr = summa_dohod - summa_dohod_post;
                Log.d("mylog", korr + " " + summa_dohod + " " + summa_dohod_post);

                String vstavka = "";
                switch (name_dohod) {
                    case 1:
                        vstavka = "`dohod`=dohod-'" + korr + "'";
                        break;
                    case 2:
                        Log.d("mylog", "postoyan_post " + postoyan_post + " postoyan " + postoyan);
                        if (postoyan_post == 1) {
                            if (postoyan == 1) {
                                vstavka = "`rashod`=rashod-'" + korr + "'";
                            } else {
                                vstavka = "`rashod`=rashod+'" + summa_dohod_post + "'";
                            }
                        } else {
                            if (postoyan == 1) {
                                vstavka = "`rashod`=rashod-'" + summa_dohod + "'";
                            } else {

                                vstavka = "`rashod`=rashod";
                            }
                        }
                        break;
                    case 3:
                        vstavka = "`zel`=zel-'" + korr + "'";
                        break;
                }


                db.execSQL("UPDATE `an_users` SET " + vstavka);


                c.close();


                db.execSQL("UPDATE `an_dohod` SET" +
                        " `summa_dohod`='" + summa_dohod_post + "'," +
                        " `komment`='" + komment_post + "'," +
                        " `postoyan`='" + postoyan_post + "'" +
                        " WHERE id=" + id + "");


                finish();

            }
        });


    }

    // @Override
    //  public boolean onCreateOptionsMenu(Menu menu) {
    //     getMenuInflater().inflate(R.menu.menu_main, menu);
    //      return true;
    // }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
        }
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}