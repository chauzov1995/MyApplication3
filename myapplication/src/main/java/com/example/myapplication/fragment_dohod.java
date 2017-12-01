package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class fragment_dohod extends Fragment {

static boolean lonpower=false;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_dohod, null);








        return v;
    }




    @Override
    public void onResume()
    {
        super.onResume();
        MainActivity.loadcart(getActivity());



      //  BoxAdapter  boxAdapter1 = new BoxAdapter(getActivity(), MainActivity.products_doh, getActivity());
        //BoxAdapter  boxAdapter2 = new BoxAdapter(getActivity(), MainActivity.products_rash, getActivity());
        //BoxAdapter  boxAdapter3 = new BoxAdapter(getActivity(), MainActivity.products_zel, getActivity());
      //  String[] myDataset = getActivity().getApplication().getDataSet();
        RecyclerAdapter  boxAdapter1 = new RecyclerAdapter(MainActivity.products_zel);
     //   RecyclerAdapter  boxAdapter2 = new RecyclerAdapter(getDataSet());
    //    RecyclerAdapter  boxAdapter3 = new RecyclerAdapter(getDataSet());
        // настраиваем список
      RecyclerView gvdoh = (RecyclerView) v.findViewById(R.id.gvdoh);
       RecyclerView gvrash = (RecyclerView) v.findViewById(R.id.gvrash);
       RecyclerView gvzel = (RecyclerView) v.findViewById(R.id.gvzel);
        ScrollView scrview = (ScrollView) v.findViewById(R.id.scrview);




     //   final float scale = getActivity().getResources().getDisplayMetrics().density;
     //   int size_gv_doh=(int)Math.ceil(((double)MainActivity.products_doh.size())/3);
     //   int size_gv_rash= (int)Math.ceil(((double)MainActivity.products_rash.size())/3);
     //   int size_gv_zel=(int)Math.ceil(((double)MainActivity.products_zel.size())/3);
     //   int vsegoheight=(size_gv_zel+size_gv_doh+size_gv_rash)*134+4;
     //   LinearLayout llheight = (LinearLayout) v.findViewById(R.id.llheight);
     //   ViewGroup.LayoutParams params = llheight.getLayoutParams();
     //   int pixels = (int) (vsegoheight * scale + 0.5f);
     //   params.height = pixels; // или в пикселях
    //    llheight.setLayoutParams(params);

        gvdoh.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View r) {

                lonpower=true;

                  Toast.makeText(getActivity(),
                         "Пора покормить кота!", Toast.LENGTH_SHORT).show();

                return true;
            }
        });



        gvdoh.setAdapter(boxAdapter1);
     //   gvrash.setAdapter(boxAdapter2);
      //  gvzel.setAdapter(boxAdapter3);

    }





}