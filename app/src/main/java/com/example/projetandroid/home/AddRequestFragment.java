package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;

import java.util.ArrayList;

import model.AdoptionRequest;
import model.Pet;
import service.DatabaseHelper;
import service.PetsServices;

public class AddRequestFragment extends Fragment {

    Pet p;
    Spinner dropdown;
    int id;
    public Button add_req_btn;
    public DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_add_adaptionreq, container, false);

        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        id = sp1.getInt("Id", 0);


        //db
        databaseHelper = new DatabaseHelper(this.getContext());

        dropdown = view.findViewById(R.id.pet_ids);
        //create a list of items for the spinner.
        ArrayList<Integer> items = getForAdaptionPetID();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,items);
        //set the spinners adapter to the previously created one.
        dropdown.setSelection(0); //set default selection to 0
        dropdown.setAdapter(adapter);

        // Set an item selected listener on the spinner
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Do something when an item is selected
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(Integer.parseInt(selectedItem) > 0)
                {
                    p = databaseHelper.getpetDetail(Integer.parseInt(selectedItem));
                    Toast.makeText(getActivity(), "Selected pet id: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "No pet to adapt",  Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "You must select a pet",  Toast.LENGTH_SHORT).show();
            }
        });

        //get  used service
        PetsServices petsServices = new PetsServices(this.getContext());


        //configure pet list
        add_req_btn = view.findViewById(R.id.add_req_btn);

        add_req_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p == null)
                    Toast.makeText(getActivity(), "No pet to adapt",  Toast.LENGTH_SHORT).show();
                else{
                    AdoptionRequest req = new AdoptionRequest();
                    req.setPetId(p.getId());
                    req.setOwnerId(p.getOwnerId());
                    req.setAdapterId(id);

                    databaseHelper.addRequest(req);
                }

            }});

        return view;
    }

    public ArrayList<Integer> getForAdaptionPetID()
    {
        //owner id must be different from curretn id
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets = databaseHelper.getAllPets();

        ArrayList<Integer> petsId = new ArrayList<Integer>();
        for (Pet p:pets)
        {
            if(id != p.getOwnerId())
            {petsId.add(p.getOwnerId());
                Log.i("added",String.valueOf(p.getOwnerId())+" "+String.valueOf(id));
            }
        }
        return petsId;
    }
}
