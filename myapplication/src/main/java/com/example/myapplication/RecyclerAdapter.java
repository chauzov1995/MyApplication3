package com.example.myapplication;


        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.ColorStateList;
        import android.graphics.PorterDuff;
        import android.graphics.drawable.Drawable;
        import android.os.Build;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.graphics.drawable.DrawableCompat;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<Product> objects;
    Activity getactivity;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvText, tvText3, textView4,textView5, textView6;
        public Button button4;
        public  LinearLayout llnp,llitem;
        public  ImageView imageView;


        public ViewHolder(View v) {
            super(v);


            imageView = (ImageView) v.findViewById(R.id.imageView);
            tvText = (TextView) v.findViewById(R.id.tvText);
            textView5 = (TextView) v.findViewById(R.id.textView5);
            tvText3 = (TextView) v.findViewById(R.id.tvText3);
            textView4 = (TextView) v.findViewById(R.id.textView4);
            textView5 = (TextView) v.findViewById(R.id.textView5);
            textView6 = (TextView) v.findViewById(R.id.textView6);
            button4 = (Button) v.findViewById(R.id.button4);
            llnp = (LinearLayout) v.findViewById(R.id.llnp);
            llitem = (LinearLayout) v.findViewById(R.id.llitem);
        }
    }

    // Конструктор
    public RecyclerAdapter(ArrayList<Product> _objects, Activity _getactivity) {
        objects = _objects;
        getactivity=_getactivity;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        ViewHolder  vh;
        View  v;

        switch (viewType) {
            case 0:
                  v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_plus, parent, false);
                  vh = new ViewHolder(v);
                break;
default:
      v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_green, parent, false);
      vh = new ViewHolder(v);
    break;

        }

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)


        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product p = objects.get(position);


        switch (this.getItemViewType(position))
        {
            case 0:

                holder.tvText3.setText(p.komment);
                holder.llnp.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View r) {

                        Intent intent = new Intent(getactivity, new_dohod.class);
                        intent.putExtra("name_dohod", p.name_doh);
                        getactivity.startActivity(intent);

                    }

                });
                break;
            case 1:


        switch(p.name_doh){
            case 1:
                RecyclerAdapter.TintIcons.tintImageView((ImageView) holder.imageView, R.color.ri_green);
                holder.tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                holder.textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                holder.textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_green));
                holder.button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_green_item));
                break;
            case 2:

                if(p.postoyan==1) {
                    RecyclerAdapter.TintIcons.tintImageView((ImageView) holder.imageView, R.color.ri_brown);
                    holder.tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                    holder.textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                    holder.textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_brown));
                    holder.button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_brown_item));
                }else{
                    RecyclerAdapter.TintIcons.tintImageView((ImageView) holder.imageView, R.color.ri_orage);
                    holder.tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                    holder.textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                    holder.textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_orage));
                    holder.button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_orange_item));
                }
                break;
            case 3:
                RecyclerAdapter.TintIcons.tintImageView((ImageView) holder.imageView, R.color.ri_blue);
                holder.tvText.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                holder.textView4.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                holder.textView6.setTextColor(getactivity.getResources().getColor(R.color.ri_blue));
                holder.button4.setBackground( getactivity.getResources().getDrawable(R.drawable.btn_blue_item));
                break;


        }

        holder.tvText.setText(Integer.toString(p.suuma_fakt));
        holder.textView4.setText(p.komment);
        holder.textView5.setText(p.suuma_doh + " р.");
        holder.button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View r) {


                click_cart(p);

            }
        });

                holder.llitem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View r) {


                        click_cart(p);

                    }
                });

                holder.button4.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View r) {
                        lonh_click_cart();

                        return true;
                }
        });
                holder.llitem.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View r) {
                        lonh_click_cart();

                        return true;
                    }
                });

break;
        }

    }



    void click_cart(Product p){




            Intent intent = new Intent(getactivity, new_dkr_crea.class);

            intent.putExtra("id", p.id);
            intent.putExtra("summa", p.suuma_doh);
            intent.putExtra("komment", p.komment);
            intent.putExtra("postoyan", p.postoyan);
            intent.putExtra("name_doh", p.name_doh);
            getactivity.startActivity(intent);



    }

    void lonh_click_cart(){


        Toast.makeText(getactivity,
                "А теперь выберите карточку для редактировния", Toast.LENGTH_SHORT).show();
        //fragment_dohod.lonpower=true;

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public int getItemViewType(int position)
    {

        return (objects.get(position).new_plus == 1) ? 0 : 1;

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