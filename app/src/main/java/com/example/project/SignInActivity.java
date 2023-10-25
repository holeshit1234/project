package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.database.Database;

public class SignInActivity extends AppCompatActivity {
    // Views
    private Button btSignIn;
    private EditText username;
    private EditText password;
    private TextView notAccountYet;

    // Notify
    private final String REQUIRE = "Required";

    // Database
     Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Reference views
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        notAccountYet = findViewById(R.id.textViewSignUp);
        btSignIn = findViewById(R.id.buttonLogin);

        // Initialize the database helper
        database = new Database(this, "User.db", null, 1);

        //tao table Congviec
        database.QueryData("Create table if not exists User(id Integer Primary Key Autoincrement ," +
                "Username nvarchar(200), Password nvarchar(200) )" );

        notAccountYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkInput()) {
                    return;
                }

                // Check login credentials
                if (checkLogin(username.getText().toString(), password.getText().toString())) {
                    // Login successful, start the main activity
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optionally finish this activity to prevent going back to the login screen.
                } else {
                    // Login failed, show an error message
                    Toast.makeText(SignInActivity.this, "Login failed. Check your username and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError(REQUIRE);
            return false;
        }
        return true;
    }

    // Function to check login credentials
    private boolean checkLogin(String username, String password) {
        // Trim the input data
        username = username.trim();
        password = password.trim();

        // Query the database to check if the username and password match
        String query = "SELECT * FROM User WHERE Username = '" + username + "' AND Password = '" + password + "'";
        Cursor cursor = database.GetData(query);

        // If a record is found, login is successful
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }
}
