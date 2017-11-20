package com.example.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

public class frag3 extends Fragment {


    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.frag4, null);








        return v;
    }




    @Override
    public void onResume()
    {
        super.onResume();
        MainActivity.loadcart(getActivity());



        BoxAdapter  boxAdapter1 = new BoxAdapter(getActivity(), MainActivity.products_doh, getActivity());
        BoxAdapter  boxAdapter2 = new BoxAdapter(getActivity(), MainActivity.products_rash, getActivity());
        BoxAdapter  boxAdapter3 = new BoxAdapter(getActivity(), MainActivity.products_zel, getActivity());
        // настраиваем список
        GridView gvdoh = (GridView) v.findViewById(R.id.gvdoh);
        GridView gvrash = (GridView) v.findViewById(R.id.gvrash);
        GridView gvzel = (GridView) v.findViewById(R.id.gvzel);


        final float scale = getActivity().getResources().getDisplayMetrics().density;
        int size_gv_doh=(int)Math.ceil(((double)MainActivity.products_doh.size())/3);
        int size_gv_rash= (int)Math.ceil(((double)MainActivity.products_rash.size())/3);
        int size_gv_zel=(int)Math.ceil(((double)MainActivity.products_zel.size())/3);
        int vsegoheight=(size_gv_zel+size_gv_doh+size_gv_rash)*135+5;
        LinearLayout llheight = (LinearLayout) v.findViewById(R.id.llheight);
        ViewGroup.LayoutParams params = llheight.getLayoutParams();
        int pixels = (int) (vsegoheight * scale + 0.5f);
        params.height = pixels; // или в пикселях
        llheight.setLayoutParams(params);



        gvdoh.setAdapter(boxAdapter1);
        gvrash.setAdapter(boxAdapter2);
        gvzel.setAdapter(boxAdapter3);

    }





}