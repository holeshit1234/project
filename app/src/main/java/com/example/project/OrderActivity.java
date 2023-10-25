package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.database.Database;

public class OrderActivity extends AppCompatActivity {


    private TextView username;
    private TextView idcard;
    private TextView date;
    private TextView total;
    private Button buttonOK;

Database database;
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String idCard = intent.getStringExtra("idCard");
        double price = intent.getDoubleExtra("price", 0.0);
        String date = intent.getStringExtra("date");

        TextView username = findViewById(R.id.usernameTextView);
        TextView idcard = findViewById(R.id.idMasterCardTextView);
        TextView dateTextView = findViewById(R.id.dateBillTextView);
        TextView total = findViewById(R.id.priceTotalTextView);
        Button buttonOK = findViewById(R.id.buttonOK);

        username.setText("User: " + name);
        idcard.setText("Card ID: " + idCard);
        dateTextView.setText("Date: " + date);
        total.setText("Total Price: $" + price  );
// Trong phương thức onCreate của OrderActivity

     buttonOK = findViewById(R.id.buttonOK);

    buttonOK.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Tạo một Intent để chuyển hướng trở về MainActivity
            Intent intent = new Intent(OrderActivity.this, MainActivity.class);
            startActivity(intent);
            // Đóng OrderActivity
            finish();
        }
    });
        // Nếu bạn muốn thêm sự kiện cho buttonOK, bạn có thể thực hiện điều đó ở đây.
    }



}
