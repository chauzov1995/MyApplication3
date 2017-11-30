package com.example.myapplication;

/**
 * Created by nikita on 21.11.2017.
 */

public class History   {

    int id;
    String komment;
    int suuma;
    String data_fact;
    int kuda;
    int name_doh;
    String nazv_doh;


    History(int _id, String _komment, int _suuma, String _data_fact, int _kuda,int _name_doh, String _nazv_doh) {
        id = _id;
        komment = _komment;
        suuma = _suuma;
        data_fact = _data_fact;
        kuda = _kuda;
        name_doh=_name_doh;
        nazv_doh=_nazv_doh;
    }



}
