package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.database.Database;

public class SignUpActivity extends AppCompatActivity {

    private Button btSignUp;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private TextView alreadyAccount;
    private final String REQUIRE = "Required";

    // Database Helper
 Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Reference views
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        alreadyAccount = findViewById(R.id.textViewLogin);
        btSignUp = findViewById(R.id.buttonSignUp);

        // Initialize the database helper
        database = new Database(this, "User.db", null, 1);

        //tao table Congviec
        database.QueryData("Create table if not exists User(id Integer Primary Key Autoincrement ," +
                "Username nvarchar(200), Password nvarchar(200) )" );

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkInput()) {
                    return;
                }

                String newUser = username.getText().toString().trim();
                String newPassword = password.getText().toString().trim();

                // Insert user data into the database
                boolean isInserted = insertUserData(newUser, newPassword);

                if (isInserted) {
                    Toast.makeText(SignUpActivity.this, "Sign up successful. You can now log in.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
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
        if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
            confirmPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(password.getText().toString(), confirmPassword.getText().toString())) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean insertUserData(String username, String password) {
        try {
            // Open the database for writing
            SQLiteDatabase db = database.getWritableDatabase();

            // Check if the username already exists in the database
            String checkQuery = "SELECT * FROM User WHERE Username = ?";
            Cursor cursor = db.rawQuery(checkQuery, new String[]{username});

            if (cursor.getCount() > 0) {
                cursor.close();
                Toast.makeText(this, "Username already exists. Please choose a different one.", Toast.LENGTH_SHORT).show();
                return false;
            }

            cursor.close();

            // Insert the user data into the database using parameterized query
            String insertQuery = "INSERT INTO User (Username, Password) VALUES (?, ?)";
            SQLiteStatement statement = db.compileStatement(insertQuery);
            statement.bindString(1, username);
            statement.bindString(2, password);
            long rowId = statement.executeInsert();

            if (rowId == -1) {
                // Insert failed
                Toast.makeText(this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                return false;
            }

            // Insert successful
            Toast.makeText(this, "Sign up successful. You can now log in.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return true;
    }

}
