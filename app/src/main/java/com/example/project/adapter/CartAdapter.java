package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Cart;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<Cart> cartItemList;

    public CartAdapter(Context context, List<Cart> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        ImageView productImage = convertView.findViewById(R.id.cartItemImage);
        TextView productName = convertView.findViewById(R.id.cartItemName);
        TextView productPrice = convertView.findViewById(R.id.cartItemPrice);
        TextView productQuantity = convertView.findViewById(R.id.cartItemQuantity);

        Cart cartItem = cartItemList.get(position);

        productImage.setImageResource(cartItem.getImageProduct());
        productName.setText(cartItem.getNameProduct());
        productPrice.setText("Price: $" + cartItem.getPriceProduct());
        productQuantity.setText( String.valueOf(cartItem.getQuantityProduct()));

        return convertView;
    }
}