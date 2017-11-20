package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

public class frag2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=  inflater.inflate(R.layout.grag3, null);



        Button button2 = (Button) v.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}