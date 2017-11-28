package com.example.myapplication;

/**
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PageFragment extends Fragment {

    private String pageNumber;

    public static PageFragment newInstance(String page) {
        PageFragment fragment = new PageFragment();
        Bundle args=new Bundle();
        args.putString("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber =  getArguments().getString("num") ;
    }
    static String getTitle(Context context, String position) {
        return "Дата: " + position;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.new_dkr, container, false);


        TextView textView3=(TextView)result.findViewById(R.id.textView3);
        textView3.setText("Число " + String.valueOf(pageNumber));



      //  MainActivity.history.
        ArrayList<History> poisk= new ArrayList<History>();

        for (History data:MainActivity.history
             ) {
            if(data.data_fact.equals(pageNumber)){poisk.add(data);
         //   Log.d("asdad", data.data_fact+" "+pageNumber);

            }
           // Log.d("asdad", data.data_fact+" "+pageNumber);
        }




        history_adapter  boxAdapter2 = new history_adapter(getActivity(), poisk, getActivity());
        // настраиваем список
        ListView history = (ListView) result.findViewById(R.id.history);
        history.setAdapter(boxAdapter2);
        return result;
    }
}

 */

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private static ArrayList<String> datamassiv;

    int pageNumber;

    View v;


    static PageFragment newInstance(int page, ArrayList<String> _datamassiv) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        datamassiv=_datamassiv;
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.new_dkr, null);



            return v;
        }

    @Override
    public void onResume() {
        super.onResume();


        TextView textView3 = (TextView) v.findViewById(R.id.textView3);
        textView3.setText("Число " + datamassiv.get(pageNumber));



        //  MainActivity.history.
        MainActivity.loadhist(getActivity());
        ArrayList<History> poisk = new ArrayList<History>();

        for (History data : MainActivity.history
                ) {
            if (data.data_fact.equals(datamassiv.get(pageNumber))) {
                poisk.add(data);
                Log.d("asdad", data.data_fact+" "+datamassiv);

            }

        }


        history_adapter boxAdapter2 = new history_adapter(getActivity(), poisk, getActivity());
        // настраиваем список
        ListView history = (ListView) v.findViewById(R.id.history);
        history.setAdapter(boxAdapter2);
    }
}


