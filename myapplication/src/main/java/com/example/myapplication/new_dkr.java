package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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

        setInitialDateTime();








        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {

                new DatePickerDialog(getActivity(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
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
