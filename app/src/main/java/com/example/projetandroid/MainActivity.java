package com.example.projetandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import service.DatabaseHelper;
import service.PetsServices;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private PetsServices petsServices;
    private ListView listView;
    private Button add_pet_btn;

    public int id;

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get current user id
        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);
        id = sp1.getInt("Id", 0);

        //instanciate pet service
        petsServices = new PetsServices(this);
        //instanciate database helper
        databaseHelper = new DatabaseHelper(this);



        //region display list
        /*listView = findViewById(R.id.list);
        petsServices.displayPets(id,listView);
        petsServices.displayMyPets(id,listView);*/
        // Set the content view
        //endregion

        /* // navigate to other activity
        my_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mng","inside my pets");
                Toast.makeText(MainActivity.this, "To my pets page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this , MyPetsActivity.class);
                intent.putExtra("Pet created.", my_pet_btn.getText().toString());
                startActivity(intent);
            }});

*/

        //region add pet button function
        /*
        add_pet_btn = findViewById(R.id.add_pet_btn);
        add_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pet p = new Pet();

                EditText nameEditText = findViewById(R.id.pet_name);
                p.setName(nameEditText.getText().toString());

                EditText breedEditText = findViewById(R.id.pet_breed);
                p.setBreed(breedEditText.getText().toString());

                EditText genderEditText = findViewById(R.id.pet_gender);
                p.setGender(genderEditText.getText().toString());

                EditText weightEditText = findViewById(R.id.pet_weight);
                String inputText = weightEditText.getText().toString();
                float weight = Float.parseFloat(inputText);
                p.setWeight(weight);

                EditText ageEditText = findViewById(R.id.pet_age);
                String inputAge = ageEditText.getText().toString();
                int age = Integer.parseInt(inputAge);
                p.setAge(age);

                p.setOwnerId(id);

                CheckBox yes_foradaption = findViewById(R.id.yes_foradaption);
                CheckBox yes_hasreport= findViewById(R.id.yes_hasreport);

                if (yes_foradaption.isChecked()) {
                    p.setForAdaption(1);
                } else {
                    p.setForAdaption(0);
                }

                if (yes_hasreport.isChecked()) {
                    p.setHasReport(1);
                } else {
                    p.setHasReport(0);
                }

                // Add pet to database
                try {
                    petsServices.createPet(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });*/
        //endregion
    }
}
