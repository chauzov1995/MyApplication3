package com.example.myapplication;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.ColorStateList;
        import android.graphics.PorterDuff;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.graphics.drawable.DrawableCompat;
        import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

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

            TextView tvText3 =(TextView) view.findViewById(R.id.tvText3);
            LinearLayout llnp =(LinearLayout) view.findViewById(R.id.llnp);

            tvText3.setText(p.komment);


            llnp.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View r) {

/*
                     Fragment f = new new_dohod();
                     Bundle bundle = new Bundle();
                                       bundle.putInt("name_dohod", p.name_doh);
                                         f.setArguments(bundle);


                     FragmentTransaction fTrans = getactivity.getFragmentManager().beginTransaction();
                     fTrans.replace(R.id.frgmCont, f);
                     fTrans.addToBackStack(null);
                 //    fTrans.setRetainInstance(true);
                     fTrans.commit();


*/



                     Intent intent = new Intent(getactivity, new_dohod.class);
                                  intent.putExtra("name_dohod", p.name_doh);
                     getactivity.startActivity(intent);
/*

                     ContentValues cv = new ContentValues();
                     SQLiteDatabase db = dbHelper.getWritableDatabase();


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
                view = lInflater.inflate(R.layout.item_green, parent, false);
            }


            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка

            Button  button4=(Button) view.findViewById(R.id.button4);
            TextView tvText= (TextView) view.findViewById(R.id.tvText);
            TextView  textView4 =(TextView) view.findViewById(R.id.textView4);
            TextView textView5 =(TextView) view.findViewById(R.id.textView5);
            TextView textView6 =(TextView) view.findViewById(R.id.textView6);
            ImageView imageView =(ImageView) view.findViewById(R.id.imageView);



          //  LinearLayout llitem=(LinearLayout) view.findViewById(R.id.llitem);




            switch(p.name_doh){
                case 1:
                    TintIcons.tintImageView((ImageView) view.findViewById(R.id.imageView), R.color.ri_green);
                    tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                    textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                    textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                    button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_green_item));
                        break;
                case 2:

                    if(p.postoyan==1) {
                        TintIcons.tintImageView((ImageView) view.findViewById(R.id.imageView), R.color.ri_brown);
                        tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                        textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                        textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                        button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_brown_item));
                    }else{
                        TintIcons.tintImageView((ImageView) view.findViewById(R.id.imageView), R.color.ri_orage);
                        tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                        textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                        textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                        button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_orange_item));
                    }
                    break;
                case 3:
                    TintIcons.tintImageView((ImageView) view.findViewById(R.id.imageView), R.color.ri_blue);
                    tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                    textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                    textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                    button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_blue_item));
                    break;


            }


            tvText.setText(p.suuma_fakt + "");
            textView4.setText(p.komment);
            textView5.setText(p.suuma_doh + " р.");




            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View r) {

/*
if(fragment_dohod.lonpower){

    Intent intent = new Intent(getactivity, dohod_red.class);
    intent.putExtra("id", p.id);
    intent.putExtra("summa", p.suuma_doh);
    intent.putExtra("komment", p.komment);
    intent.putExtra("postoyan", p.postoyan);
    intent.putExtra("name_doh", p.name_doh);
    getactivity.startActivity(intent);
}else {
    */
                    Toast.makeText(getactivity.getApplicationContext(),
                            "Пора покормить кота!"+p.komment, Toast.LENGTH_SHORT).show();

    Intent intent = new Intent(getactivity, new_dkr_crea.class);




                    intent.putExtra("id", p.id);
    intent.putExtra("summa", p.suuma_doh);
    intent.putExtra("komment", p.komment);
    intent.putExtra("postoyan", p.postoyan);
    intent.putExtra("name_doh", p.name_doh);
    getactivity.startActivity(intent);

//}


                }
            });

/*
            button4.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View r) {




                    Intent intent = new Intent(getactivity, dohod_red.class);
                    intent.putExtra("id", p.id);
                    intent.putExtra("summa", p.suuma_doh);
                    intent.putExtra("komment", p.komment);
                    intent.putExtra("postoyan", p.postoyan);
                    intent.putExtra("name_doh", p.name_doh);
                    getactivity.startActivity(intent);


                    return true;
                }
            });
*/
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

    public static class TintIcons {

        public static Drawable tintIcon(Drawable icon, ColorStateList colorStateList) {
            if(icon!=null) {
                icon = DrawableCompat.wrap(icon).mutate();
                DrawableCompat.setTintList(icon, colorStateList);
                DrawableCompat.setTintMode(icon, PorterDuff.Mode.SRC_IN);
            }
            return icon;
        }

        public static void tintImageView(ImageView imageView, int colorStateListResId) {
            ColorStateList list = ContextCompat.getColorStateList(imageView.getContext(), colorStateListResId);
            if (list != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageTintList(list);
                } else {
                    imageView.setImageDrawable(tintIcon(imageView.getDrawable(), list));
                }
            }
        }
    }
}