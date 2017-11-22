package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by chauzov_n_g on 17.11.2017.
 */

public class new_dkr extends Fragment {

    TextView  textView3;
    View v;
    String date;
    Calendar dateAndTime= Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.new_dkr, null);

          textView3  =((TextView) v.findViewById(R.id.textView3));
       Button button3  =((Button) v.findViewById(R.id.button3));
        Button button6  =((Button) v.findViewById(R.id.button6));

        setInitialDateTime();



        //Toast.makeText(getActivity(),
         //       date, Toast.LENGTH_SHORT).show();
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                dateAndTime.add(Calendar.DATE, -1);
                setInitialDateTime();
                onResume();

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                dateAndTime.add(Calendar.DATE, +1);
                setInitialDateTime();
                onResume();
            }
        });


        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                new DatePickerDialog(getActivity(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
            });


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Intent intent = new Intent(getActivity(), new_dkr_crea.class);

               intent.putExtra("date", date);

                startActivity(intent);
            }
        });

return v;
    }

    private void setInitialDateTime() {

        textView3.setText( DateUtils.formatDateTime(getActivity(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
       // int seconds = dateAndTime.get(Calendar.SECOND);
       // date=DateUtils.formatDateTime(getActivity(), dateAndTime.getTimeInMillis(),
            //    DateUtils.FORMAT_NUMERIC_DATE|DateUtils.FORMAT_SHOW_YEAR);
        date= dateAndTime.get(Calendar.YEAR)+"."+(dateAndTime.get(Calendar.MONTH)+1)+"."+dateAndTime.get(Calendar.DAY_OF_MONTH);

    }


    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
            onResume();
        }
    };




    @Override
    public void onResume()
    {
        super.onResume();




      MainActivity.loadhist(getActivity(),date);





       history_adapter  boxAdapter2 = new history_adapter(getActivity(), MainActivity.history, getActivity());
        // настраиваем список
        ListView history = (ListView) v.findViewById(R.id.history);
       history.setAdapter(boxAdapter2);


    }



}
