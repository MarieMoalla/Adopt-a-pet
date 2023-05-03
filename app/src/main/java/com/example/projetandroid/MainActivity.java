package com.example.projetandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.projetandroid.databinding.ActivityMainBinding;

import model.Pet;
import service.DatabaseHelper;
import service.PetsServices;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private PetsServices petsServices;
    private ListView listView;
    private Button update_pet_btn;

    public int id;

    private AppBarConfiguration mAppBarConfiguration;

    //databainding
    private ActivityMainBinding binding;
    private PetViewModel petViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciate pet service
        petsServices = new PetsServices(this);
        //instanciate database helper
        databaseHelper = new DatabaseHelper(this);

        //get pet by id
        Pet pet = new Pet();
        //Log.i("pety id",String.valueOf(4));
        pet = petsServices.getPetById(4);
        final Pet pet1 = new Pet(4,pet.getName(), pet.getBreed(), pet.getGender(),pet.getWeight(), pet.getAge(),pet.getHasReport(), pet.getForAdaption(),pet.getOwnerId());
        //

        //two way binding data
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        petViewModel = new PetViewModel();
        binding.setViewModel(petViewModel);
        binding.setLifecycleOwner(this);

        //set view data with pet data
        petViewModel.setPet(pet);
        EditText petName = findViewById(R.id.pet_name);
        EditText petGender = findViewById(R.id.pet_gender);
        EditText petBreed = findViewById(R.id.pet_breed);
        EditText petWeight = findViewById(R.id.pet_weight);
        EditText petAge = findViewById(R.id.pet_age);

        //update pet button

        update_pet_btn = findViewById(R.id.update_pet_btn);
        update_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test update", petName.getText().toString());

                //update inputs in database
                //petViewModel.setPet(new Pet (petName.getText().toString(),petBreed.getText().toString(),petGender.getText().toString(),Integer.parseInt(petWeight.getText().toString()),Integer.parseInt(petAge.getText().toString())));
                try
                {
                    petsServices.updatePet(4,petName.getText().toString(),petBreed.getText().toString(),petGender.getText().toString(),Float.parseFloat(petWeight.getText().toString()),Integer.parseInt(petAge.getText().toString()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }});




        //petViewModel.getPet().observe(this,p -> {Log.i("change",p.getName());});
        //

        //get current user id
        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);
        id = sp1.getInt("Id", 0);





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
