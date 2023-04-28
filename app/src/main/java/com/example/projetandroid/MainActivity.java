package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.PetListAdapter;
import model.Pet;
import service.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView listView;
    Button madd_pet_btn;
    TextView pet_id;
    TextView pet_name;
    TextView pet_breed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        //madd_pet_btn = (Button) findViewById(R.id.add_pet);

        pet_id = findViewById(R.id.pet_id_textview);
        pet_name = findViewById(R.id.pet_name_textview);
        pet_breed = findViewById(R.id.pet_breed_textview);

        listView = findViewById(R.id.list);

        displayPets();

        //region add pet button function
        /*madd_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEditText = findViewById(R.id.pet_name);
                EditText breedEditText = findViewById(R.id.pet_breed);

                String name = nameEditText.getText().toString();
                String breed = breedEditText.getText().toString();

                // Check if name and breed are not empty
                if (name.trim().isEmpty() || breed.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Name and breed cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                // Add pet to database
                Pet p = new Pet();
                p.setName(name);
                p.setBreed(breed);
                try {
                    databaseHelper.addPet(p);
                    databaseHelper.close();
                    Intent intent = new Intent(MainActivity.this , EditPetActivity.class);
                    intent.putExtra("Pet created.", madd_pet_btn.getText().toString());
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });
        */

        //endregion
    }

    private void displayPets() {
        List<Pet> pets = databaseHelper.getMyPets();
        for (Pet p : pets) {
            Log.i("petinfo ",p.getName());

        }
        PetListAdapter adapter = new PetListAdapter(this, pets);
        listView.setAdapter(adapter);
    }
}
