package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.adapter.ClothesAdapter;
import com.example.project.database.Database;
import com.example.project.model.Clothes;
import com.example.project.model.Clothes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listClothes;
    ArrayList<Clothes> arrayClothes;
    ClothesAdapter adapter;
    private Button btOut;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product_clothes);
        btOut = (Button) findViewById(R.id.button);

        listClothes = (ListView) findViewById(R.id.listClothes);
        arrayClothes = new ArrayList<>();
        adapter = new ClothesAdapter(this,R.layout.item, arrayClothes);
        listClothes.setAdapter(adapter);

        // Initialize the database helper
        database = new Database(this, "User.db", null, 1);
        // Drop the Clothes table if it exists
        database.QueryData("DROP TABLE IF EXISTS Clothes");
//
        database.QueryData("DROP TABLE IF EXISTS Cart");


        database.QueryData("CREATE TABLE IF NOT EXISTS Clothes (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name NVARCHAR(200), Details NVARCHAR(200), Image NVARCHAR(200), Quantity INTEGER, Price REAL ) ");

        database.QueryData("CREATE TABLE IF NOT EXISTS Cart (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name NVARCHAR(200), Price REAL,  Quantity INTEGER, Image NVARCHAR(200) ) ");

        database.QueryData("CREATE TABLE IF NOT EXISTS Orders (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "PriceTotal REAL, DateBill NVARCHAR(200), NameUser NVARCHAR(200), IdMasterCard NVARCHAR(200)  ) ");

// Thực hiện truy vấn để kiểm tra dữ liệu trong bảng "Cart"
        String cartQuery = "SELECT * FROM Cart";
        Cursor cartData = database.GetData(cartQuery);

// Kiểm tra xem có dữ liệu trong bảng "Cart" hay không
        if (cartData.getCount() > 0) {
            // Có dữ liệu trong giỏ hàng
            // Hiển thị thông báo hoặc thực hiện các thao tác cần thiết
            // Ví dụ: Hiển thị thông báo
            Toast.makeText(MainActivity.this, "Có đồ trong giỏ", Toast.LENGTH_SHORT).show();
        } else {
            // Không có dữ liệu trong giỏ hàng
            // Thực hiện các thao tác khác (nếu cần)
            // Ví dụ: Không hiển thị thông báo
        }


        //inset
        database.QueryData("Insert into Clothes values(null, 'hello', 'quanaadasdadasdsadoao', " + R.drawable.aothunomachi +  ", 12, 22.2)");
        database.QueryData("Insert into Clothes values(null, 'Alibaba', 'quanadasdadadaoao', " + R.drawable.nhanvienfsoft +  ", 222, 44.0)");
        database.QueryData("Insert into Clothes values(null, 'Dữ z sao', 'quanaádadasdsadoao', " + R.drawable.chinsu +  ", 11, 10.0)");
        database.QueryData("Insert into Clothes values(null, 'Adidaphat', 'quanaádasdadasdoao', " + R.drawable.haohao1 +  ", 1, 44.4)");
        database.QueryData("Insert into Clothes values(null, 'nhanvienfsoft', 'quasadsadadsadadanaoao', " + R.drawable.haohao +  ", 40, 20.0)");
        database.QueryData("Insert into Clothes values(null, 'duudud', 'quanasadasdasdoao', " + R.drawable.haohao2 +  ", 22, 33.33)");
        database.QueryData("Insert into Clothes values(null, 'shiba', 'quanaádasdsadoao', " + R.drawable.omachi +  ", 25, 50.0)");
        //

        String query = "SELECT * FROM Clothes ";
        Cursor dataClothes = database.GetData(query);
        while (dataClothes.moveToNext()){
            int id = dataClothes.getInt(0);
            String name = dataClothes.getString(1);
            String details = dataClothes.getString(2);
            int image = dataClothes.getInt(3);
            int quantity = dataClothes.getInt(4);
            double price = dataClothes.getDouble(5);
            arrayClothes.add(new Clothes(id,name,details,image,quantity,price ));
        }
        adapter.notifyDataSetChanged();

        btOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
                //finish();
            }
        });


//        ImageView homeListClothes = findViewById(R.id.home);
        ImageView cartClothes = findViewById(R.id.cart);

//        homeListClothes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Xử lý sự kiện khi nhấp vào biểu tượng imageClothes (quay lại trang trước)
//                onBackPressed(); // Quay lại trang trước
//            }
//        });

        cartClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấp vào biểu tượng shoppingCartIcon (chuyển đến trang Cart)
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        ImageView mapView = findViewById(R.id.map);

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấp vào biểu tượng shoppingCartIcon (chuyển đến trang Cart)
                Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                startActivity(intent);
            }
        });

    }
}