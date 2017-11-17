package com.example.myapplication;

        import java.util.ArrayList;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.myapplication.frag3.Product;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;

        int type = getItemViewType(position);
        Product p = getProduct(position);
        if (type == 0) {
           // Toast.makeText(ctx, "asdasd", Toast.LENGTH_LONG).show();


            if (view == null) {
                view = lInflater.inflate(R.layout.item_plus, parent, false);
            }


            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка
            ((TextView) view.findViewById(R.id.tvText3)).setText(p.suuma_fakt + " р.");

        }else{

            if (view == null) {
                view = lInflater.inflate(R.layout.item, parent, false);
            }


            // заполняем View в пункте списка данными из товаров: наименование, цена
            // и картинка
            ((TextView) view.findViewById(R.id.tvText)).setText(p.suuma_fakt + " р.");
            ((TextView) view.findViewById(R.id.textView4)).setText(p.komment);
            ((TextView) view.findViewById(R.id.textView5)).setText(p.suuma_doh + " р.");

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
   // @Override
   // public int getItemViewType(int position) {



  //      return (contactList.get(position).getContactType() == ContactType.CONTACT_WITH_IMAGE) ? 0 : 1;
  //  }

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