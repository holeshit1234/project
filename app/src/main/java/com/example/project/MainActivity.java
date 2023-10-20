package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.adapter.PokemonAdapter;
import com.example.project.database.Database;
import com.example.project.model.Pokemon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lvUser;
    ArrayList<Pokemon> arrayPokemon;
    PokemonAdapter adapter;
    private Button btOut;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btOut = (Button) findViewById(R.id.button);


        lvUser = (ListView) findViewById(R.id.lvUser);
        arrayPokemon = new ArrayList<>();
        adapter = new PokemonAdapter(this,R.layout.item, arrayPokemon);
        lvUser.setAdapter(adapter);
        //tao database ghichu
        // Initialize the database helper
        database = new Database(this, "User.db", null, 1);

        //tao table Congviec
        database.QueryData("Create table if not exists PokemonNew(id Integer Primary Key Autoincrement ," +
                "Name nvarchar(200), Element nvarchar(200), Image nvarchar(200) )" );

        //inset
//        database.QueryData("Insert into Pokemon values(null, 'blastoise', 'water', 'blastoise.jpg' )");
        database.QueryData("Insert into PokemonNew values(null, 'blastoise', 'water', " + R.drawable.blastoise + " )");
        database.QueryData("Insert into PokemonNew values(null, 'bulbasaur', 'grass/poison', " + R.drawable.bulbasaur + " )");
        database.QueryData("Insert into PokemonNew values(null, 'charmender', 'fire', " + R.drawable.charmander + " )");
        database.QueryData("Insert into PokemonNew values(null, 'croconaw', 'water', " + R.drawable.croconaw + " )");
        database.QueryData("Insert into PokemonNew values(null, 'groudon', 'fire/ground', " + R.drawable.groudon + " )");
        database.QueryData("Insert into PokemonNew values(null, 'kyogre', 'water', " + R.drawable.kyogre + " )");
        database.QueryData("Insert into PokemonNew values(null, 'pikachu', 'thunder', " + R.drawable.pikachu + " )");

        String query = "SELECT * FROM PokemonNew ";
        Cursor dataPokemon = database.GetData(query);
        while (dataPokemon.moveToNext()){
            String name = dataPokemon.getString(1);
            String element = dataPokemon.getString(2);
            int image = dataPokemon.getInt(3);
            int id = dataPokemon.getInt(0);
            arrayPokemon.add(new Pokemon(id, name,element, image));
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
    }
}