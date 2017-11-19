package com.example.myapplication;

        import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
        import android.graphics.Color;
        import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainActivity.DBHelper;

import java.util.ArrayList;



public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;
    Activity getactivity;

    com.example.myapplication.new_dohod new_Dohod;


    DBHelper dbHelper;

    BoxAdapter(Context context, ArrayList<Product> products, Activity _getactivity) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getactivity=_getactivity;
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        dbHelper = new DBHelper(getactivity);

        int type = getItemViewType(position);
        final Product p = getProduct(position);


        if (type == 0) {
            //если добавить новую
           // Toast.makeText(ctx, "asdasd", Toast.LENGTH_LONG).show();
            new_Dohod = new new_dohod();

            if (view == null) {
                view = lInflater.inflate(R.layout.item_plus, parent, false);
            }
            TextView tvText3=  (TextView) view.findViewById(R.id.tvText3);
            tvText3.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View r) {


                     Fragment f = new new_dohod();
                     Bundle bundle = new Bundle();
                                       bundle.putInt("name_dohod", p.name_doh);
                                         f.setArguments(bundle);


                     FragmentTransaction fTrans = getactivity.getFragmentManager().beginTransaction();
                     fTrans.replace(R.id.frgmCont, f);
                     fTrans.addToBackStack(null);
                 //    fTrans.setRetainInstance(true);
                     fTrans.commit();

/*

                     ContentValues cv = new ContentValues();
                     SQLiteDatabase db = dbHelper.getWritableDatabase();

                     cv.put("id_clienta", 1);
                     cv.put("name_dohod", 1);
                     cv.put("summa_dohod", 344);
                     cv.put("summa_fakt", 0);
                     cv.put("komment", "бензин");
                     cv.put("visible", 0);
                     cv.put("postoyan", 0);

                     // вставляем запись и получаем ее ID
                     long rowID = db.insert("an_dohod", null, cv);

                     Log.d("myLogs", "--- onCreate database ---");
*/
                 }

            });

            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка

        }else{


            if (view == null) {
                view = lInflater.inflate(R.layout.item, parent, false);
            }


            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка

            Button  button4=(Button) view.findViewById(R.id.button4);

            switch(p.name_doh){
                case 1:
                    button4.setBackgroundColor(Color.parseColor("#99cc55"));
                        break;
                case 2:
                    button4.setBackgroundColor(Color.parseColor("#ff9966"));
                    break;
                case 3:
                    button4.setBackgroundColor(Color.parseColor("#55bbbb"));
                    break;


            }


            ((TextView) view.findViewById(R.id.tvText)).setText(p.suuma_fakt + " р.");
            ((TextView) view.findViewById(R.id.textView4)).setText(p.komment);
            ((TextView) view.findViewById(R.id.textView5)).setText(p.suuma_doh + " р.");


            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View r) {



                    Fragment f = new dohod_red();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", p.id);
                    bundle.putInt("summa", p.suuma_doh);
                    bundle.putString("komment", p.komment);
                    f.setArguments(bundle);

                    FragmentTransaction fTrans = getactivity.getFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, f);
                    fTrans.addToBackStack(null);
                    fTrans.commit();

                }
            });

        }


        return view;
    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {



        return (getProduct(position).new_plus == 1) ? 0 : 1;
    }

    // содержимое корзины
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects) {
            // если в корзине
         //   if (p.box)
           //     box.add(p);
        }
        return box;
    }


}