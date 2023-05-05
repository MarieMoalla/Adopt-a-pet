package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;

import java.util.List;

import adapter.RequestAdapter;
import model.AdoptionRequest;
import service.DatabaseHelper;

public class MyRequestFragment extends Fragment {

    int current_id;
    ListView list;
    private DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //db
        databaseHelper = new DatabaseHelper(this.getContext());

        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        current_id = sp1.getInt("Id", 0);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_my_request, container, false);

        //configure pet list
        list = view.findViewById(R.id.list);
        Log.i("requ list fragment",String.valueOf(current_id));
        List<AdoptionRequest> reqs = databaseHelper.getMyRequests(current_id);
        for (AdoptionRequest req : reqs)
            Log.i("request info ",String.valueOf(req.getId()));
        RequestAdapter adapter = new RequestAdapter(this.getContext(), reqs);
        list.setAdapter(adapter);


        return view;
    }

}