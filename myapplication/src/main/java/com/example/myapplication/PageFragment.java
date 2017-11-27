package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader=(TextView)result.findViewById(R.id.displayText);
        pageHeader.setText("Фрагмент " + String.valueOf(pageNumber));
        return result;
    }
}