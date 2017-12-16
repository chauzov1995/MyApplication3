package com.example.myapplication;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Внимание";
        String message = "Вы подтверждаете удаление конверта?";
        String button1String = "Удалить";
        String button2String = "Отмена";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id3) {


                //удаление

                int id= new_dkr_crea.kuda_intent;

                MainActivity.DBHelper dbHelper = new MainActivity.DBHelper(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();







                db.execSQL("UPDATE `an_dohod` SET" +
                        " `visible`='1'" +
                        " WHERE id="+Integer.toString(id)+"");



                Cursor c = db.rawQuery("select * from `an_dohod` Where id='"+String.valueOf(id)+"'", null);
                c.moveToFirst();


                int name_dohod = c.getInt(c.getColumnIndex("name_dohod"));
                int summa_dohod = c.getInt(c.getColumnIndex("summa_dohod"));
                int postoyan = c.getInt(c.getColumnIndex("postoyan"));

                String vstavka="";
                switch(name_dohod){
                    case 1:
                        vstavka="`dohod`=dohod-'"+summa_dohod+"'";
                        break;
                    case 2:
                        if(postoyan==1){
                            vstavka="`rashod`=rashod-'"+summa_dohod+"'";
                        }else{

                            vstavka="`rashod`=rashod";
                        }
                        break;
                    case 3:
                        vstavka="`zel`=zel-'"+summa_dohod+"'";
                        break;
                }


                db.execSQL("UPDATE `an_users` SET "+vstavka);
                c.close();


                getActivity().finish();
                // onBackPressed();// возврат на предыдущий activity





                Toast.makeText(getActivity(), "Конверт успешно удалён",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              //  Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG)
             //           .show();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}