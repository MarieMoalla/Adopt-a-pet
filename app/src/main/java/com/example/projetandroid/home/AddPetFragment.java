package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;

import model.Pet;
import service.PetsServices;

public class AddPetFragment extends Fragment {

    private Button add_pet_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_addpet, container, false);

        //get  used service
        PetsServices petsServices = new PetsServices(this.getContext());

        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        int id = sp1.getInt("Id", 0);

        //set new pet
        Pet p = new Pet();

        EditText petName = view.findViewById(R.id.pet_name);
        EditText breedEditText = view.findViewById(R.id.pet_breed);
        EditText genderEditText = view.findViewById(R.id.pet_gender);
        EditText weightEditText = view.findViewById(R.id.pet_weight);
        EditText ageEditText = view.findViewById(R.id.pet_age);

        CheckBox yes_foradaption = view.findViewById(R.id.yes_foradaption);
        CheckBox yes_hasreport= view.findViewById(R.id.yes_hasreport);

        //configure pet list
        add_pet_btn = view.findViewById(R.id.add_pet_btn);

        add_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add pet to database
                try {
                    String name = petName.getText().toString();
                    if (name.isEmpty())
                    {
                        petName.setError("This field is required");
                        petName.requestFocus();
                    } else p.setName(name);

                    Log.i("value name",String.valueOf(name));

                    String breed = breedEditText.getText().toString();
                    if (breed.isEmpty())
                    {
                        breedEditText.setError("This field is required");
                        breedEditText.requestFocus();
                    } else p.setBreed(breed);

                    String gender = genderEditText.getText().toString();
                    if (gender.isEmpty())
                    {
                        genderEditText.setError("This field is required");
                        genderEditText.requestFocus();
                    } else p.setGender(gender);

                    String weightString = weightEditText.getText().toString();
                    if (weightString.isEmpty())
                    {
                        weightEditText.setError("This field is required");
                        weightEditText.requestFocus();
                    } else
                    {
                        float weight = Float.parseFloat(weightString);
                        p.setWeight(weight);
                    }

                    String ageString = ageEditText.getText().toString();
                    if (ageString.isEmpty())
                    {
                        ageEditText.setError("This field is required");
                        ageEditText.requestFocus();
                    } else
                    {
                        int age = Integer.parseInt(ageString);
                        p.setAge(age);
                    }

                    if (yes_foradaption.isChecked()) p.setForAdaption(1);
                    else p.setForAdaption(0);

                    if (yes_hasreport.isChecked()) p.setHasReport(1);
                    else  p.setHasReport(0);

                    p.setOwnerId(id);

                    petsServices.createPet(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return view;
    }

}