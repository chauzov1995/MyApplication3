package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by chauzov_n_g on 17.11.2017.
 */

public class new_dkr extends Fragment {

    TextView  textView3;
    View v;
    Calendar dateAndTime= Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.new_dkr, null);

          textView3  =((TextView) v.findViewById(R.id.textView3));
      // Button button  =((Button) v.findViewById(R.id.button));


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

        textView3.setText(DateUtils.formatDateTime(getActivity(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }


    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };




    @Override
    public void onResume()
    {
        super.onResume();


        MainActivity.load_dkr_cart(getActivity());



        dkr_konv_adapter  boxAdapter1 = new dkr_konv_adapter(getActivity(), MainActivity.dkr_kart, getActivity());
     // настраиваем список
        GridView dkr_view = (GridView) v.findViewById(R.id.dkr_view);
        dkr_view.setAdapter(boxAdapter1);


    }



}
