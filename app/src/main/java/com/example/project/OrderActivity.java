package com.example.project;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.project.application.MyApplicaiton;
import com.example.project.database.Database;

import java.util.Date;

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
        double pricetotal = intent.getDoubleExtra("price", 0.0);
        String datebill = intent.getStringExtra("date");

        TextView username = findViewById(R.id.usernameTextView);
        TextView idcard = findViewById(R.id.idMasterCardTextView);
        TextView dateTextView = findViewById(R.id.dateBillTextView);
        TextView total = findViewById(R.id.priceTotalTextView);
        Button buttonOK = findViewById(R.id.buttonOK);

        username.setText("User: " + name);
        idcard.setText("Address: " + idCard);
        dateTextView.setText("Date: " + datebill);
        total.setText("Total Price: $" + pricetotal  );
// Trong phương thức onCreate của OrderActivity

    database = new Database(this, "User.db", null, 1);

    //tao table Congviec
    database.QueryData("CREATE TABLE IF NOT EXISTS OrderDetail (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NameUser NVARCHAR(200), Adrress NVARCHAR(200), PriceTotal REAL, DateBill NVARCHAR(200) ) ");


    buttonOK = findViewById(R.id.buttonOK);

    buttonOK.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Tạo một Intent để chuyển hướng trở về MainActivity

            boolean Inserted = insertOrderData(name, idCard, pricetotal, datebill);
            if(Inserted){
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);

                startActivity(intent);
                // Đóng OrderActivity

                finish();
            }
        }
    });
        // Nếu bạn muốn thêm sự kiện cho buttonOK, bạn có thể thực hiện điều đó ở đây.
    }
    private boolean insertOrderData(String username, String idcard,  double price, String date ) {
        try {
            // Open the database for writing
            SQLiteDatabase db = database.getWritableDatabase();

            // Check if the username already exists in the database
            String insertQuery = "INSERT INTO OrderDetail (NameUser, Adrress, PriceTotal, DateBill) VALUES (?, ?, ?, ?)";
            database.QueryData(insertQuery);

            // Insert the user data into the database using parameterized query
            SQLiteStatement statement = db.compileStatement(insertQuery);

            // Bind the data to the statement
            statement.bindString(1, username); // Bind username
            statement.bindString(2, idcard);   // Bind idcard
            statement.bindDouble(3, price);    // Bind price as double
            statement.bindString(4, date);     // Bind date

            // Execute the insert statement
            long rowId = statement.executeInsert();

            // Check if the insert was successful
            if (rowId != -1) {
                // Insert successful
               sendNotification();
            } else {
                // Insert failed
                Toast.makeText(this, "Failed to insert order data.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return true;
    }
    private void sendNotification() {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            Notification notification = new NotificationCompat.Builder(this, MyApplicaiton.CHANNEL_ID)
                    .setContentTitle("Shop Bán Quần áo")
                    .setContentText("Order data inserted successfully.")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(bitmap)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.notify(getNotificationId(), notification);
            }
        }catch(NullPointerException e){
            e.getMessage();
        }
    }
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}
