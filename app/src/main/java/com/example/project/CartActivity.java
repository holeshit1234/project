package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.adapter.CartAdapter;
import com.example.project.database.Database;
import com.example.project.model.Cart;
import com.example.project.model.Order;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private CartAdapter cartAdapter;
    private List<Cart> cartItemList;

    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cart);

        database = new Database(this, "User.db", null, 1);

        cartListView = findViewById(R.id.cartListView);
        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartItemList);
        cartListView.setAdapter(cartAdapter);

        loadCartItems();

        ImageView homeListClothes = findViewById(R.id.home);

        homeListClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấp vào biểu tượng imageClothes (quay lại trang trước)
                onBackPressed(); // Quay lại trang trước
            }
        });



        // Tìm nút "Pay" theo ID
        Button payButton = findViewById(R.id.buttonPay);

        // Gán sự kiện click cho nút "Pay"
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItemList.isEmpty()) {
                    // Nếu giỏ hàng trống, không thực hiện gì cả
                    return;
                }else{
                    showPopup();
                }
            }
        });
    }



    private void loadCartItems() {
        // Query và thêm dữ liệu từ bảng Cart vào danh sách cartItemList
        Cursor data = database.GetData("SELECT * FROM Cart");
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            double price = data.getDouble(2);
            int quantity = data.getInt(3);
            int image = data.getInt(4);

            Cart cartItem = new Cart(id, name, price, quantity, image);
            cartItemList.add(cartItem);
        }

        // Cập nhật giao diện
        cartAdapter.notifyDataSetChanged();
    }

    private double calculateTotalPrice(List<Cart> cartItems) {
        double totalPrice = 0;
        for (Cart cartItem : cartItems) {
            totalPrice += cartItem.getPriceProduct() ;
        }
        return totalPrice;
    }
    private void showPopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_layout, null);
        dialogBuilder.setView(dialogView);

        double total = calculateTotalPrice(cartItemList);

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextIDCard = dialogView.findViewById(R.id.editTextIDCard);
        final TextView priceTotal = dialogView.findViewById(R.id.price);

        priceTotal.setText("Total Price: " + total + "$");

        DatePicker datePicker = dialogView.findViewById(R.id.datePickerPopup);







        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editTextName.getText().toString();
                String idCard = editTextIDCard.getText().toString();

                String priceText = priceTotal.getText().toString().replace("Total Price: ", "").replace("$", "");
                double price = Double.parseDouble(priceText);

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;

                // Tạo một Intent để chuyển dữ liệu tới OrderActivity
                Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("idCard", idCard);
                intent.putExtra("price", price);
                intent.putExtra("date", date);

                // Chuyển sang OrderActivity
                startActivity(intent);
                dialog.dismiss(); // Đóng popup sau khi xử lý
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Đóng popup khi bấm Cancel
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }


}
