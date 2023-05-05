package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;
import com.example.projetandroid.SharedViewModel;

import service.DatabaseHelper;
import service.PetsServices;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetDetailFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get  used service
        PetsServices petsServices = new PetsServices(this.getContext());

        //db
        databaseHelper = new DatabaseHelper(this.getContext());


        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        int id = sp1.getInt("Id", 0);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_pet_details, container, false);

        // Retrieve the data from the arguments bundle
        Bundle bundle = getArguments();
        String value = bundle.getString("0");
        Log.i("value argument wsel",value);

        return view;
    }

}