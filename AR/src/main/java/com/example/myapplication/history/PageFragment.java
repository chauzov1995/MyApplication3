package com.example.myapplication.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.dohod.dodod_activity;

import java.util.ArrayList;

public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private static ArrayList<String> datamassiv;

    int pageNumber;

    View v;


    public static PageFragment newInstance(int page, ArrayList<String> _datamassiv) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        datamassiv = _datamassiv;
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


        //  MainActivity.history.
        dodod_activity.loadhist(getActivity());
        ArrayList<history_class> poisk = new ArrayList<history_class>();
        int vsego_port = 0;
        for (history_class data : dodod_activity.history
                ) {
            if (data.data_fact.equals(datamassiv.get(pageNumber))) {
                poisk.add(data);
                if (data.name_doh == 2) {
                    vsego_port += data.suuma;
                }
                Log.d("asdad", data.data_fact + " " + datamassiv);

            }

        }
        textView3.setText(Integer.toString(vsego_port) + " р.");

        history_adapter boxAdapter2 = new history_adapter(getActivity(), poisk, getActivity());
        // настраиваем список
        ListView history = (ListView) v.findViewById(R.id.history);
        history.setAdapter(boxAdapter2);
    }
}


