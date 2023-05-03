package com.example.projetandroid;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import adapter.PetListAdapter;
import model.Pet;
import service.DatabaseHelper;
import service.PetsServices;



public class AdoptionFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //get  used service
        PetsServices petsServices = new PetsServices(this.getContext());

        //db$
        databaseHelper = new DatabaseHelper(this.getContext());


        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        int id = sp1.getInt("Id", 0);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_adoption, container, false);

        //configure pet list
        ListView petList = view.findViewById(R.id.list);
        Log.i("fragment",String.valueOf(id));
        List<Pet> pets = databaseHelper.getMyPets(id);
        Log.i("Current logged in user ", String.valueOf(id));
        for (Pet p : pets)
            Log.i("petinfo ",p.getName());
        PetListAdapter adapter = new PetListAdapter(this.getContext(), pets);
        petList.setAdapter(adapter);
        return view;
    }
}