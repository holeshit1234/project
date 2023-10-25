package com.example.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.ProductDetail;
import com.example.project.R;
import com.example.project.model.Clothes;

import java.text.DecimalFormat;
import java.util.List;

public class ClothesAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Clothes> clothesList;

    public ClothesAdapter(Context context, int layout, List<Clothes> clothesList) {
        this.context = context;
        this.layout = layout;
        this.clothesList = clothesList;
    }

    @Override
    public int getCount() {
        return clothesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageClothes;
        TextView nameClothes;
        TextView price;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.nameClothes= (TextView) view.findViewById(R.id.nameClothes);
            holder.price= (TextView) view.findViewById(R.id.priceClothes);
            holder.imageClothes = (ImageView) view.findViewById(R.id.imageClothes) ;
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Clothes clothes = clothesList.get(i);
        holder.nameClothes.setText(clothes.getNameClothes());
        // Định dạng giá tiền
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedPrice = "$" + decimalFormat.format(clothes.getPriceClothes());
        holder.price.setText("Price : " + formattedPrice);
        holder.imageClothes.setImageResource(clothes.getImageClothes());



        // Lắng nghe sự kiện click trên mỗi mục sản phẩm
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy sản phẩm tại vị trí i
                Clothes clothes = clothesList.get(i);

                // Tạo Intent để chuyển đến ProductDetailActivity
                Intent intent = new Intent(context, ProductDetail.class);

                // Truyền dữ liệu sản phẩm (ví dụ: ID sản phẩm) vào Intent
                intent.putExtra("product_id", clothes.getIdClothes());

                // Chuyển đến ProductDetailActivity
                context.startActivity(intent);
            }
        });

        return view;
    }
}
