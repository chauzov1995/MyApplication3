package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Product   {

    String komment;
     int suuma_doh;
     int suuma_fakt;
      int id;
      int  name_doh;
      int  new_plus;
    int postoyan;

    Product(String _komment, int _suuma_doh, int _suuma_fakt, int _id, int _name_doh, int _new_plus, int _postoyan) {
        komment = _komment;
        suuma_doh = _suuma_doh;
        suuma_fakt = _suuma_fakt;
        id = _id;
        name_doh = _name_doh;
        new_plus=_new_plus;
        postoyan=_postoyan;
    }



    }



