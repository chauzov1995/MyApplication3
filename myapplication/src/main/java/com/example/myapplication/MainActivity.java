package com.example.myapplication;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.example.myapplication.frag3.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentTransaction fTrans;
    com.example.myapplication.frag2 frag2;
    com.example.myapplication.frag3 frag3;
    new_dkr New_dkr;
    DBHelper dbHelper;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            fTrans = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fTrans.replace(R.id.frgmCont, New_dkr);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);
                    fTrans.replace(R.id.frgmCont, frag2);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    fTrans.replace(R.id.frgmCont, frag3);
                    fTrans.addToBackStack(null);
                    fTrans.commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        frag2 = new frag2();
        frag3 = new frag3();
        New_dkr = new new_dkr();
        dbHelper = new DBHelper(this);




    }














   public static  class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
          //  Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("CREATE TABLE `an_dohod` (\n" +
                    "  `id` int(11) NOT NULL,\n" +
                    "  `id_clienta` int(11) NOT NULL,\n" +
                    "  `name_dohod` int(11) NOT NULL COMMENT '1-доходы, 2-расходы, 3-цели, 4-баланс, 5-кошелёк',\n" +
                    "  `summa_dohod` decimal(11,0) NOT NULL,\n" +
                    "  `summa_fakt` decimal(10,0) NOT NULL,\n" +
                    "  `komment` varchar(255) NOT NULL,\n" +
                    "  `asdsa` int(11) NOT NULL,\n" +
                    "  `visible` int(11) NOT NULL COMMENT '1-невидим',\n" +
                    "  `postoyan` int(11) NOT NULL,\n" +
                    "  `nompp` int(11) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
