package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;

import java.util.List;

import adapter.RequestAdapter;
import model.AdoptionRequest;
import service.DatabaseHelper;

public class RequestFragment extends Fragment {

    int current_id;
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
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        //configure pet list
        ListView list = view.findViewById(R.id.list);
        Log.i("requ list fragment",String.valueOf(current_id));
        List<AdoptionRequest> reqs = databaseHelper.getRecievedRequests(current_id);
        for (AdoptionRequest req : reqs)
            Log.i("request info ",String.valueOf(req.getId()));
        RequestAdapter adapter = new RequestAdapter(this.getContext(), reqs);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item view
                View item = parent.getChildAt(position);

                // Retrieve the TextView value from the clicked item view
                TextView textView = item.findViewById(R.id.req_id_textview);
                String text = textView.getText().toString();

                showDialog(Integer.parseInt(text));
            }
        });
        return view;
    }
    public void showDialog(int reqid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Verification!")
                .setMessage("Are you sure you want to refuse this request?")
                .setPositiveButton("Refuse", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something when OK button is clicked
                        Toast.makeText(getActivity(), "request"+reqid+" deleted." , Toast.LENGTH_SHORT).show();
                        deleterequest(reqid);
                    }
                })
                .setNegativeButton("Request accept, this pet is no longer yours :3", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something when Cancel button is clicked
                        /*AdoptionRequest req = databaseHelper.getRequestDetail(reqid);
                        databaseHelper.acceptRequest(req,current_id);
                        databaseHelper.deleteRequById(req.getId());*/
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void deleterequest( int reqId)
    {
        databaseHelper.deleteRequById(reqId);
    }
}
