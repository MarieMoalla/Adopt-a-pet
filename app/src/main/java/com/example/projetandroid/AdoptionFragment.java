package com.example.projetandroid;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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

        //db
        databaseHelper = new DatabaseHelper(this.getContext());

        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        int id = sp1.getInt("Id", 0);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_adoption, container, false);

        //configure pet list
        ListView petList = view.findViewById(R.id.list);
        Log.i("fragment",String.valueOf(id));
        List<Pet> pets = databaseHelper.getAllPets();
        Log.i("Current logged in user ", String.valueOf(id));
        for (Pet p : pets)
            Log.i("petinfo ",p.getName());
        PetListAdapter adapter = new PetListAdapter(this.getContext(), pets);
        petList.setAdapter(adapter);

        //visit pet
        petList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                /*
                // Retrieve the selected Pet object
                Pet selectedPet = (Pet) adapterView.getItemAtPosition(position);

                // Get the ID of the selected pet
                int petId = selectedPet.getId();

                // Create a Bundle and set the data you want to send
                Bundle bundle = new Bundle();
                bundle.putString(String.valueOf(id), String.valueOf(petId));

                // Create an instance of Fragment B
                PetDetailFragment fragmentB = new PetDetailFragment();

                // Set the arguments to the fragment using setArguments()
                fragmentB.setArguments(bundle);
                */

                //navigate to other layout
                NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_hos_fragment_from_main);
                //log
                NavController navController = navHostFragment.getNavController();
                Navigation.findNavController(view).navigate(R.id.action_pet_list_to_pet_details);
            }
        });

        return view;
    }
}